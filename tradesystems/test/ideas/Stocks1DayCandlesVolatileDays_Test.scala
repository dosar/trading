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
    class SimpleTest(val ticker: String, override val targetProfit: Double = 0.19) extends VolatileDaysStatisticalPrinter
    {
        def analyze(checkDays: Int, positionDays: Int, op1: TradingOp, op2: TradingOp) =
        {
            val op1Candles = VolatileCandles(checkDays, positionDays, _.buyProfit > 0).filterInterestingDays(data)
            val op2Candles = VolatileCandles(checkDays, positionDays, _.sellProfit > 0).filterInterestingDays(data)
            analyzeIdea((op1Candles, op1), (op2Candles, op2))
        }
    }

    test("sberbank 1 day candles percent stop, take profit") { new SimpleTest("SBER", 0.3).standardTest(stopMultiplierStep = 1) }
    test("gazprom 1 day candles percent stop, take profit") { new SimpleTest("GAZP", 0.35).standardTest(stopMultiplierStep = 1) }
    test("nornikel 1 day candles percent stop, take profit") { new SimpleTest("GMKN", 0.25).standardTest(stopMultiplierStep = 1) }
    test("lukoil 1 day candles percent stop, take profit") { new SimpleTest("LKOH").standardTest(stopMultiplierStep = 1) }
    test("novatek 1 day candles percent stop, take profit") { new SimpleTest("NVTK", 0.25).standardTest(stopMultiplierStep = 1) }
    test("rosneft 1 day candles percent stop, take profit") { new SimpleTest("ROSN", 0.25).standardTest(stopMultiplierStep = 1) }
    test("rostelecom 1 day candles percent stop, take profit") { new SimpleTest("RTKM", 0.35).standardTest(stopMultiplierStep = 1) }
}
