package tradinganalyzers.statistics

import tradinganalyzers.{TradingPositionAnalyzer, TradingOp}
import tradingideas.LongTrendCandles
import scala.io.Source
import scala.collection.mutable.ArrayBuffer
import scala.util.matching.Regex
import tradingsystems.{TradingData, YearProfit}
import org.joda.time.LocalDate

/**
 * @author alespuh
 * @since 11.07.13
 */
trait VolatileDaysCombinationStatisticalPrinter
{
    class SimplePrinter(val ticker: String, override val targetProfit: Double = 19) extends VolatileDaysStatisticalPrinter

    val strategiesFile = "trading_ideas.txt"
    val targetProfit: Double = 50

    lazy val data =
        Array("SBER", "GAZP", "GMKN", "NVTK", "ROSN", "RTKM", "LKOH").map(t => (t, new SimplePrinter(t).data)).toMap

    type Ticker = String

    case class CombinationElement(ticker: String, op1: TradingOp, op2: TradingOp, op1CheckDays: Int,
        op1PositionDays: Int, op2CheckDays: Int, op2PositionDays: Int)
    {
        def getPositionAnalyzer = new TradingPositionAnalyzer(
            (new LongTrendCandles(op1CheckDays, op1PositionDays, _.buyProfit > 0).filterInterestingDays(data(ticker)), op1),
            (new LongTrendCandles(op2CheckDays, op2PositionDays, _.sellProfit > 0).filterInterestingDays(data(ticker)), op2))

        def desc = ticker + " " + List(op1CheckDays, op1PositionDays, op1.opStr, op1.takeProfitPercent.formatted("%5.2f"),
            op1.stopPercent.formatted("%5.2f"), op2CheckDays, op2PositionDays, op2.opStr,
            op2.takeProfitPercent.formatted("%5.2f"), op2.stopPercent.formatted("%5.2f")).mkString(" | ", " | ", " | ")
    }

    def test2StrategiesCombination()
    {
        //parseFile and get groups of strategies, Map[Ticker, Vector[CombinationElement]]
        val strategies: Map[Ticker, Vector[CombinationElement]] =
            parseInput(Source.fromFile(strategiesFile).getLines())
        //analyze all combination of each pair strategies
        for(first <- strategies; second <- strategies if first != second;
            firstStrategy <- first._2; secondStrategy <- second._2)
        {
            val statistics = getYearProfitsStatistics(firstStrategy, secondStrategy)
            if(statistics != null) println(statistics)
        }
    }

    def test3StrategiesCombination()
    {
        //parseFile and get groups of strategies, Map[Ticker, Vector[CombinationElement]]
        val strategies: Map[Ticker, Vector[CombinationElement]] =
            parseInput(Source.fromFile(strategiesFile).getLines())
        //analyze all combination of each tripple strategies
        for(first <- strategies; second <- strategies; third <- strategies
            if first != second && second != third && first != third;
            firstStrategy <- first._2; secondStrategy <- second._2; thirdStrategy <- third._2)
        {
            val statistics = getYearProfitsStatistics(firstStrategy, secondStrategy, thirdStrategy)
            if(statistics != null) println(statistics)
        }
    }

    def getYearProfitsStatistics(strategy1: CombinationElement, strategy2: CombinationElement) =
    {
        val yps = getYearProfits(strategy1, strategy2)
        if(isUsefulOutput(yps))
        {
            strategy1.desc + " over " + strategy2.desc + " | " +
                yps.flatMap(yp => Vector(yp.year, yp.yearProfitPct.formatted("%7.2f"), yp.yearSlumpPct.formatted("%7.2f"),
                    yp.worstMonthSlumpPct.formatted("%7.2f"))).mkString(" | ")
        }
        else null
    }

    def getYearProfitsStatistics(strategy1: CombinationElement, strategy2: CombinationElement, strategy3: CombinationElement) =
    {
        val yps = getYearProfits(strategy1, strategy2, strategy3)
        if(isUsefulOutput(yps))
        {
            strategy1.desc + " over " + strategy2.desc + " over " + strategy3.desc + " | " +
                yps.flatMap(yp => Vector(yp.year, yp.yearProfitPct.formatted("%7.2f"), yp.yearSlumpPct.formatted("%7.2f"),
                    yp.worstMonthSlumpPct.formatted("%7.2f"))).mkString(" | ")
        }
        else null
    }

    def isUsefulOutput(yps: Vector[YearProfit]): Boolean = yps.length == 4 &&
        yps(3).strictProfit(targetProfit / 2) &&
        yps(2).strictProfit(targetProfit) &&
        yps(1).strictProfit(targetProfit) &&
        yps(0).strictProfit(targetProfit)

    def getYearProfits(strategy1: CombinationElement, strategy2: CombinationElement): Vector[YearProfit] =
    {
        //get positions from 1 and 2 strategy
        val oneProfits = strategy1.getPositionAnalyzer.positionDatesProfit
        val twoProfits = strategy2.getPositionAnalyzer.positionDatesProfit
        //filter 2 strategy positions not to be included in 1 strategy
        val twoMinusOneProfits = twoProfits.filter(gp => oneProfits.exists(hasIntersection(gp, _)))
        //analyze positions set
        new TradingPositionAnalyzer(Vector()).getStatistics((oneProfits ++ twoMinusOneProfits).sortBy(_._1.toDate))
    }

    def getYearProfits(strategy1: CombinationElement, strategy2: CombinationElement, strategy3: CombinationElement): Vector[YearProfit] =
    {
        //get positions from 1 and 2 strategy
        val profits1 = strategy1.getPositionAnalyzer.positionDatesProfit
        val profits2 = strategy2.getPositionAnalyzer.positionDatesProfit
        val profits3 = strategy3.getPositionAnalyzer.positionDatesProfit
        //filter 2 and 3 strategy positions not to be included in each one and 1 strategy
        val twoMinusOneProfits = profits2.filter(gp => profits1.exists(hasIntersection(gp, _)))
        val thirdMinusTwoOneProfits = profits3.filter(gp =>
            profits1.exists(hasIntersection(gp, _)) || twoMinusOneProfits.exists(hasIntersection(gp, _)))
        //analyze positions set
        new TradingPositionAnalyzer(Vector()).getStatistics((profits1 ++ twoMinusOneProfits ++ thirdMinusTwoOneProfits).sortBy(_._1.toDate))
    }

    def dateIn(date: LocalDate, range: (LocalDate, LocalDate, _)) =
        date.compareTo(range._1) >= 0 && date.compareTo(range._2) <= 0

    def hasIntersection(left: (LocalDate, LocalDate, _), right: (LocalDate, LocalDate, _)) =
        dateIn(left._1, right) || dateIn(left._2, right) || dateIn(right._1, left) || dateIn(right._2, left)

    def parseInput(input: Iterator[String]): Map[Ticker, Vector[CombinationElement]] =
    {
        val regex: Regex = new Regex("""(\d) дня роста, (\d) дня в ([\w\s:,]+) и (\d) дня падения, (\d) дня в ([\w\s:,]+)""")
        input.filter(_.trim != "").foldLeft(("", ArrayBuffer[CombinationElement]()))
        {
            case ((currentTicker, result), line) =>
                regex.findFirstMatchIn(line) match
                {
                    case None => (line, result)
                    case Some(matchIn) => (currentTicker,
                        result :+ CombinationElement(currentTicker, TradingOp.fromDesc(matchIn.group(3)),
                            TradingOp.fromDesc(matchIn.group(6)), matchIn.group(1).toInt, matchIn.group(2).toInt,
                            matchIn.group(4).toInt, matchIn.group(5).toInt))
                }
        }._2.groupBy(_.ticker).map{case (parsedTicker, arrayBuffer) => (parsedTicker, arrayBuffer.toVector)}
    }
}