package tradinganalyzers.statistics

import tradingsystems.{YearProfit, TradingData}
import tradingideas.TradingIdea
import tradinganalyzers.TradingOp._
import tradinganalyzers.{TradingOp, TradingPositionAnalyzer}

/**
 * @author alespuh
 * @since 26.07.13
 */
case class StrategyIdeaData(data: TradingData, idea: TradingIdea)
{
    def filterInterestingPositions = idea.filterInterestingPositions(data)
    def desc = data.desc + "|" + idea.desc
}

case class StrategyElement(data: TradingData, ideas: TradingIdea*)

class CombinationBruteForces
{
    def rearrange[T](vector: Vector[T]): Vector[Vector[T]] =
    {
        if(vector.isEmpty) Vector(Vector.empty)
        else
        {
            val zippedInput = vector.zipWithIndex
            for((element, ind) <- zippedInput;
                smallerCombination <- rearrange(zippedInput.filter(_._2 != ind).map(_._1))) yield element +: smallerCombination
        }
    }

    //это ведь на самом деле cross join combLength одинаковых векторов vector
    def completeEnumeration[T](vector: Vector[T], combLength: Int): Vector[Vector[T]] =
    {
        if(combLength <= 0) return Vector(Vector.empty)
        for(element <- vector; smallerCombination <- completeEnumeration(vector, combLength - 1)) yield element +: smallerCombination
    }
}

trait StrategiesCombinator
{
    lazy val stopRange = 1 to 5
    lazy val profitRange = 8 to 13
    lazy val targetProfit = 50

    def rearrangeStrategies(strategies: (StrategyIdeaData, (Double, Double) => TradingOp)*)
    {
        val combinator = new CombinationBruteForces()
        val result = for(ideasCombination <- combinator.rearrange(strategies.toVector))
        yield
        {
            val positionTemplate = ideasCombination.map(_._1.filterInterestingPositions).zip(ideasCombination.map(_._2))
                .flatMap{case (positions, op) => positions.map(position => (position, op))}.sortBy(_._1.positionDate.toDate).toArray
            (for(opStop <- stopRange; opTakeProfit <- profitRange) yield
            {
                val positionsWithOp = positionTemplate.map{case (position, opf) => (position, opf(opStop, opTakeProfit): TradingOp)}
                //for different stop/profit run TradingPositionAnalyzer
                val yps = new TradingPositionAnalyzer(positionsWithOp).getStatistics
                if(isUsefulOutput(yps))
                {
                    val prefix = ideasCombination
                        .map{case (ideaData, opf) => (ideaData, opf(opStop, opTakeProfit))}
                        .map{case (ideaData, op) => ideaData.desc + op.desc}.mkString("|", "|", "|")
                    (prefix, yps)
                }
                else null
            }).filter(_ != null)
        }
        YearProfitStatistics.printStatistics(result.flatten.toVector)
    }

    def rearrangeStrategiesOps(strategyElements: StrategyElement*)
    {
        val strategies = strategyElements.flatMap(se => se.ideas.map(idea => StrategyIdeaData(se.data, idea)))
        val combinator = new CombinationBruteForces()
        val opCombinations = combinator.completeEnumeration(Vector(sell _, buy _), strategies.length)
        val result = for(ideasCombination <- combinator.rearrange(strategies.toVector).par; opCombination <- opCombinations)
        yield
        {
            //get positions with ops
            val positionTemplate = ideasCombination.map(_.filterInterestingPositions).zip(opCombination)
                .flatMap{case (positions, op) => positions.map(position => (position, op))}.sortBy(_._1.positionDate.toDate).toArray
            (for(opStop <- stopRange; opTakeProfit <- profitRange) yield
            {
                val positionsWithOp = positionTemplate.map{case (position, opf) => (position, opf(opStop, opTakeProfit): TradingOp)}
                //for different stop/profit run TradingPositionAnalyzer
                val yps = new TradingPositionAnalyzer(positionsWithOp).getStatistics
                if(isUsefulOutput(yps))
                {
                    val prefix = ideasCombination.zip(opCombination)
                        .map{case (ideaData, opf) => (ideaData, opf(opStop, opTakeProfit))}
                        .map{case (ideaData, op) => ideaData.desc + op.desc}.mkString("|", "|", "|")
                    (prefix, yps)//print results
                }
                else null
            }).filter(_ != null)
        }
        YearProfitStatistics.printStatistics(result.flatten.toVector)
    }

    def isUsefulOutput(yearProfits: Array[YearProfit]): Boolean = yearProfits.length == 4 &&
        yearProfits(3).strictProfit(targetProfit / 2) &&
        yearProfits(2).strictProfit(targetProfit) &&
        yearProfits(1).strictProfit(targetProfit) &&
        yearProfits(0).strictProfit(targetProfit)

    //сделать чтобы стопы и профиты для каждой идеи могли быть своими
}