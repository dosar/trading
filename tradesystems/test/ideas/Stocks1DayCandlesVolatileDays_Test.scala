package ideas

import logic.{AnalyticalStatisticsPrinter, VolatileDaysStatisticalPrinter}
import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import tradinganalyzers.{TradingPositionAnalyzer, TradingOp}
import tradingideas.TradingIdea._
import tradingideas.VolatileCandles
import tradingsystems.{YearProfit, Candle}
import org.joda.time.LocalDate

@RunWith(classOf[JUnitRunner])
class Stocks1DayCandlesVolatileDays_Test extends FunSuite
{
    class SimpleTest(tiker: String, targetProfit: Double = 0.19) extends VolatileDaysStatisticalPrinter
    {
        val data = standardImport("g:\\work\\trademachine\\" + tiker + "_2010_2013_1day.txt")

        def analyze(checkDays: Int, positionDays: Int, op1: TradingOp, op2: TradingOp) =
        {
            val op1Candles = VolatileCandles(checkDays, positionDays, _.buyProfit > 0).filterInterestingDays(data)
            val op2Candles = VolatileCandles(checkDays, positionDays, _.sellProfit > 0).filterInterestingDays(data)
            analyzeIdea((op1Candles, op1), (op2Candles, op2))
        }

        override def standardTest(initialStopPercent: Int, initialTakeProfitPercent: Int)
        {
            println(tiker)
            super.standardTest(initialStopPercent, initialTakeProfitPercent)
        }

        override def isUsefulOutput(yearProfits: Vector[YearProfit]): Boolean =
            yearProfits.exists(yp => yp.year == 2013 && (yp.yearProfit / yp.avgPrice) > targetProfit)
    }

    test("sberbank 1 day candles percent stop, take profit") { new SimpleTest("SBER").standardTest(5, 1) }
    test("gazprom 1 day candles percent stop, take profit") { new SimpleTest("GAZP", 0.25).standardTest(5, 1) }
    test("nornikel 1 day candles percent stop, take profit") { new SimpleTest("GMKN").standardTest(5, 1) }
    test("lukoil 1 day candles percent stop, take profit") { new SimpleTest("LKOH").standardTest(5, 1) }
    test("novatek 1 day candles percent stop, take profit") { new SimpleTest("NVTK").standardTest(5, 1) }
    test("rosneft 1 day candles percent stop, take profit") { new SimpleTest("ROSN").standardTest(5, 1) }
    test("rostelecom 1 day candles percent stop, take profit") { new SimpleTest("RTKM", 0.25).standardTest(5, 1) }

    test("sberbank volatile candles details")
    {
        new SimpleTest("SBER")
        {
            analyze(2, 5, TradingOp.sell(4, 3), TradingOp.buy(4, 3))
            analyze(2, 5, TradingOp.sell(3, 2), TradingOp.buy(4, 3))
            analyze(2, 5, TradingOp.sell(3, 3), TradingOp.buy(3, 3))
            analyze(2, 5, TradingOp.sell(3, 3), TradingOp.buy(2, 3))
            analyze(2, 5, TradingOp.sell(4, 3), TradingOp.buy(4, 3))//few slump months
            analyze(2, 5, TradingOp.sell(4, 3), TradingOp.buy(3, 3))
            analyze(2, 5, TradingOp.sell(3, 4), TradingOp.buy(4, 3))
            analyze(2, 5, TradingOp.sell(4, 4), TradingOp.buy(4, 3))//few slump months
            analyze(2, 5, TradingOp.sell(3, 5), TradingOp.buy(4, 3))
        }
    }

    test("gmkn volatile candles details")
    {
        new SimpleTest("GMKN")
        {
            analyze(2, 3, TradingOp.sell(3, 2), TradingOp.buy(4, 1))
            analyze(2, 3, TradingOp.sell(4, 2), TradingOp.buy(4, 1))
            analyze(2, 3, TradingOp.sell(4, 2), TradingOp.buy(2, 1))
            analyze(2, 3, TradingOp.sell(3, 2), TradingOp.buy(4, 1))
            analyze(2, 3, TradingOp.sell(4, 2), TradingOp.buy(4, 1))
        }
    }

    test("sber + gmkn")
    {
        import TradingOp._
        val sberData = new SimpleTest("SBER").data
        val gmknData = new SimpleTest("GMKN").data

        def dateIn(date: LocalDate, range: (LocalDate, LocalDate, _)) =
            date.compareTo(range._1) >= 0 && date.compareTo(range._2) <= 0

        def hasIntersection(left: (LocalDate, LocalDate, _), right: (LocalDate, LocalDate, _)) =
            dateIn(left._1, right) || dateIn(left._2, right) || dateIn(right._1, left) || dateIn(right._2, left)

        new AnalyticalStatisticsPrinter
        {
            val gmknProfits = new TradingPositionAnalyzer(
                VolatileCandles(2, 3, _.buyProfit > 0).filterInterestingDays(gmknData).map((_, sell(4, 2))) ++
                VolatileCandles(2, 3, _.sellProfit > 0).filterInterestingDays(gmknData).map((_, buy(4, 1)))
            ).positionDatesProfit
            val sberProfits = new TradingPositionAnalyzer(
                VolatileCandles(2, 5, _.buyProfit > 0).filterInterestingDays(sberData).map((_, sell(4, 4))) ++
                VolatileCandles(2, 5, _.sellProfit > 0).filterInterestingDays(sberData).map((_, buy(4, 3)))
            ).positionDatesProfit
            val intersections = sberProfits.count(sp => gmknProfits.exists(hasIntersection(_, sp)))
            println(gmknProfits.size + " | " + intersections + " | " + sberProfits.size)
        }
    }
}
