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

    lazy val data =
        Array("SBER", "GAZP", "GMKN", "NVTK", "ROSN", "RTKM").map(t => (t, new SimplePrinter(t).data)).toMap

    type Ticker = String

    case class CombinationElement(ticker: String, op1: TradingOp, op2: TradingOp, op1CheckDays: Int,
        op1PositionDays: Int, op2CheckDays: Int, op2PositionDays: Int)
    {
        def getPositionAnalyzer = new TradingPositionAnalyzer(data(ticker),
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
            parseInput(Source.fromFile("g:\\work\\trademachine\\trading_ideas.txt").getLines())
        //analyze all combination of each pair strategies
        for(first <- strategies; second <- strategies if first != second;
            firstStrategy <- first._2; secondStrategy <- second._2)
        {
            val statistics = getYearProfitsStatistics(firstStrategy, secondStrategy)
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

    def isUsefulOutput(yps: Vector[YearProfit]): Boolean = yps.forall(_.strictProfit(50))

    def getYearProfits(strategy1: CombinationElement, strategy2: CombinationElement): Vector[YearProfit] =
    {
        def dateIn(date: LocalDate, range: (LocalDate, LocalDate, _)) =
            date.compareTo(range._1) >= 0 && date.compareTo(range._2) <= 0

        def hasIntersection(left: (LocalDate, LocalDate, _), right: (LocalDate, LocalDate, _)) =
            dateIn(left._1, right) || dateIn(left._2, right) || dateIn(right._1, left) || dateIn(right._2, left)

        //get positions from 1 and 2 strategy
        val oneProfits = strategy1.getPositionAnalyzer.positionDatesProfit
        val twoProfits = strategy2.getPositionAnalyzer.positionDatesProfit
        //filter 2 strategy positions not to be included in 1 strategy
        val twoMinusOneProfits = twoProfits.filter(gp => oneProfits.exists(hasIntersection(gp, _)))
        //analyze positions set
        new TradingPositionAnalyzer(null, Vector())
        {
            override protected def avgPrice(year: Int) = 0.0
        }.getStatistics((oneProfits ++ twoMinusOneProfits).sortBy(_._1.toDate))
    }

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