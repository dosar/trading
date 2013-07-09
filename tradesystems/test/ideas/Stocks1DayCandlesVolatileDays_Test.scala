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
    class SimpleTest(val ticker: String, override val targetProfit: Double = 19) extends VolatileDaysStatisticalPrinter

    test("sberbank 1 day candles percent stop, take profit") { new SimpleTest("SBER", 30).standardTest(stopMultiplierStep = 1) }
    test("gazprom 1 day candles percent stop, take profit") { new SimpleTest("GAZP", 35).standardTest(stopMultiplierStep = 1) }
    test("nornikel 1 day candles percent stop, take profit") { new SimpleTest("GMKN", 25).standardTest(stopMultiplierStep = 1) }
    test("lukoil 1 day candles percent stop, take profit") { new SimpleTest("LKOH").standardTest(stopMultiplierStep = 1) }
    test("novatek 1 day candles percent stop, take profit") { new SimpleTest("NVTK", 25).standardTest(stopMultiplierStep = 1) }
    test("rosneft 1 day candles percent stop, take profit") { new SimpleTest("ROSN", 25).standardTest(stopMultiplierStep = 1) }
    test("rostelecom 1 day candles percent stop, take profit") { new SimpleTest("RTKM", 35).standardTest(stopMultiplierStep = 1) }

    test("checking")
    {
        new SimpleTest("SBER")
        {
            val op1Candles = VolatileCandles(2, 3, _.buyProfit > 0).filterInterestingDays(data)
            val op2Candles = VolatileCandles(2, 7, _.sellProfit > 0).filterInterestingDays(data)
            analyzeIdea((op1Candles, TradingOp.sell(2, 6)), (op2Candles, TradingOp.buy(5, 8)))
        }
    }
}
