package ideas

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import tradinganalyzers.{TradingPositionAnalyzer, TradingOp}
import tradingideas.VolatileCandles
import org.joda.time.LocalDate
import tradinganalyzers.TradingOp._
import tradingideas.VolatileCandles
import logic.{VolatileDaysStatisticalPrinter, AnalyticalStatisticsPrinter}
import tradingsystems.YearProfit

@RunWith(classOf[JUnitRunner])
class StocksCombinationDayCandlesVolatileDays_Test extends FunSuite
{
    test("sber + gazp combination")
    {
        testCombination(CombinationElement("SBER", sell(1, 5), buy(2, 3), 4, 2, 2, 5),
            CombinationElement("GAZP", sell(1, 5), sell(2, 3), 2, 5, 2, 3))
        testCombination(CombinationElement("GAZP", sell(1, 5), sell(2, 3), 2, 5, 2, 3),
            CombinationElement("SBER", sell(1, 5), buy(2, 3), 4, 2, 2, 5))
    }

    test("gazp test")
    {
        new SimplePrinter("GAZP")
        {
            analyzeIdea((VolatileCandles(2, 5, _.buyProfit > 0).filterInterestingDays(data), sell(1, 5)),
                (VolatileCandles(2, 3, _.sellProfit > 0).filterInterestingDays(data), sell(2, 3)))
        }
    }

    class SimplePrinter(val ticker: String, override val targetProfit: Double = 0.19) extends VolatileDaysStatisticalPrinter

    case class CombinationElement(ticker: String, op1: TradingOp, op2: TradingOp, op1CheckDays: Int,
        op1PositionDays: Int, op2CheckDays: Int, op2PositionDays: Int)
    {
        def getPositionAnalyzer = new SimplePrinter(ticker)
        {
            val analyzer = new TradingPositionAnalyzer(data,
                (VolatileCandles(op1CheckDays, op1PositionDays, _.buyProfit > 0).filterInterestingDays(data), op1),
                (VolatileCandles(op2CheckDays, op2PositionDays, _.sellProfit > 0).filterInterestingDays(data), op2))
        }.analyzer
    }

    def testCombination(one: CombinationElement, two: CombinationElement) =
    {
        def dateIn(date: LocalDate, range: (LocalDate, LocalDate, _)) =
            date.compareTo(range._1) >= 0 && date.compareTo(range._2) <= 0

        def hasIntersection(left: (LocalDate, LocalDate, _), right: (LocalDate, LocalDate, _)) =
            dateIn(left._1, right) || dateIn(left._2, right) || dateIn(right._1, left) || dateIn(right._2, left)

        val oneAnalyzer = one.getPositionAnalyzer
        val twoAnalyzer = two.getPositionAnalyzer

        new AnalyticalStatisticsPrinter
        {
            val ticker: String = null
            override def isUsefulOutput(yearProfits: Vector[YearProfit]): Boolean = true
            val oneProfits = oneAnalyzer.positionDatesProfit
            val twoProfits = twoAnalyzer.positionDatesProfit
            val twoMinusOneProfits = twoProfits.filter(gp => oneProfits.exists(hasIntersection(gp, _)))
            println(getStringStatistics(one.ticker + " priority: " + one.ticker, oneAnalyzer.getStatistics(oneProfits)))
            println(getStringStatistics(one.ticker + " priority: " + two.ticker, twoAnalyzer.getStatistics(twoMinusOneProfits)))
        }
    }
}
