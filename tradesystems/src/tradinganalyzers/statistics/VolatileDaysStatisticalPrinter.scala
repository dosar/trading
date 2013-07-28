package tradinganalyzers.statistics

import tradinganalyzers.{TradingPosition, TradingPositionAnalyzer, TradingOp}
import tradingideas.{TradingIdea, NegativeTrendCandles, PositiveTrendCandles, LongTrendCandles}
import tradingsystems.Candle
import scala.collection.{GenSeq, GenSeqLike, GenTraversableOnce, GenTraversable}
import scala.math._

trait VolatileDaysStatisticalPrinter extends AnalyticalStatisticsPrinter
{
    def checkDaysRange: Range = 2 to 7
    val oneStrategyDays = for(checkDays <- checkDaysRange; positionDays <- 2 to 7) yield (checkDays, positionDays)
    val twoStrategiesDays =
        for((op1CheckDays, op1PositionDays) <- oneStrategyDays; (op2CheckDays, op2PositionDays) <- oneStrategyDays)
            yield (op1CheckDays, op1PositionDays, op2CheckDays, op2PositionDays)

    def standardTest(stopMultiplierSteps: Int = 5, stopMultiplierStep: Double = 0.1, takeProfitStart: Int = 3)
    {
        println(ticker)

        val takeProfitRange = takeProfitStart to takeProfitStart + 5
        val stopRange = (1 to stopMultiplierSteps).map(_ * stopMultiplierStep)
        val statistics = twoStrategiesStatistics(stopRange, takeProfitRange) ++
            oneStrategyStatistics(stopRange, takeProfitRange, getOp1Idea, "роста", " " * 30) ++
            oneStrategyStatistics(stopRange, takeProfitRange, getOp2Idea, "падения", " " * 27)

        val result = statistics.flatten.toVector
//        result.sortBy(_.take(48)).foreach(println)
        for ((prefix, yps) <- result.sortBy{case (prefix, yps) => min(yps.take(3).map(_.yearProfitPct).min, yps(3).yearProfitPct * 2)})
            println(getStringStatistics(prefix, yps))
    }

    import TradingOp._

    def oneStrategyStatistics(stopRange: IndexedSeq[Double], takeProfitRange: Range,
        getOpIdea: (Int, Int) => TradingIdea, checkConditionDescription: String, opDescPostfix: String) =
    {
        for((checkDays, daysInPosition) <- optimize(oneStrategyDays)) yield
        {
            val opIdea = getOpIdea(checkDays, daysInPosition)
            val positionDays = opIdea.filterInterestingPositions(data)
            (for(opf <- Array(sell _, buy _); stop <- stopRange; takeProfit <- takeProfitRange) yield
            {
                val op = opf(stop, takeProfit)
                val yps = getYearProfits(positionDays.map((_, op)))
                if(isUsefulOutput(yps.toVector))
                    (opIdea.desc + op.desc + opDescPostfix, yps)
                else null
//                getStringStatistics(opIdea.desc + op.desc + opDescPostfix, getYearProfits(positionDays.map((_, op))))
            }).filter(_ != null).toArray
        }
    }

    def twoStrategiesStatistics(stopRange: IndexedSeq[Double], takeProfitRange: Range.Inclusive) =
    {
        for((op1CheckDays, op1PositionDays, op2CheckDays, op2PositionDays) <- optimize(twoStrategiesDays))
        yield
        {
            val op1Idea = getOp1Idea(op1CheckDays, op1PositionDays)
            val op2Idea = getOp2Idea(op2CheckDays, op2PositionDays)
            val daysOp1 = op1Idea.filterInterestingPositions(data).zipAll(Vector(), null, 1)
            val daysOp2 = op2Idea.filterInterestingPositions(data).zipAll(Vector(), null, 2)
            val allDaysOp = (daysOp1 ++ daysOp2).sortBy(_._1.positionDate.toDate)

            val result = (for((op1f, op2f) <- Array((sell _, buy _), (sell _, sell _), (buy _, buy _), (buy _, sell _));
                op1Stop <- stopRange; op1TakeProfit <- takeProfitRange;
                op2Stop <- stopRange; op2TakeProfit <- takeProfitRange) yield
            {
                val op1 = op1f(op1Stop, op1TakeProfit)
                val op2 = op2f(op2Stop, op2TakeProfit)
                val yps = getYearProfits(allDaysOp.map{case (position, opType) => (position, if(opType == 1) op1 else op2)})
                if(isUsefulOutput(yps.toVector))
                    ((op1Idea.desc + op1.desc) + " и " + (op2Idea.desc + op2.desc), yps)
                else null
            }).filter(_ != null)
            result
        }
    }

    def optimize[T](seq: IndexedSeq[T]): GenSeq[T] = seq.par

    def getOp1Idea(op1CheckDays: Int, op1PositionDays: Int): TradingIdea =
        new LongTrendCandles(op1CheckDays, op1PositionDays, _.buyProfit > 0, "роста")

    def getOp2Idea(op2CheckDays: Int, op2PositionDays: Int): TradingIdea =
        new LongTrendCandles(op2CheckDays, op2PositionDays, _.sellProfit > 0, "падения")

    def getYearProfits(tradingPositionOps: Vector[(TradingPosition, TradingOp)]) =
        new TradingPositionAnalyzer(tradingPositionOps.toArray).getStatistics
}