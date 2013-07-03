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

    test("sberbank 1 day candles percent stop, take profit") { new SimpleTest("SBER").standardTest(5, 1) }
    test("gazprom 1 day candles percent stop, take profit") { new SimpleTest("GAZP", 0.3).standardTest(5, 1) }
    test("nornikel 1 day candles percent stop, take profit") { new SimpleTest("GMKN").standardTest(5, 1) }
    test("lukoil 1 day candles percent stop, take profit") { new SimpleTest("LKOH").standardTest(5, 1) }
    test("novatek 1 day candles percent stop, take profit") { new SimpleTest("NVTK").standardTest(5, 1) }
    test("rosneft 1 day candles percent stop, take profit") { new SimpleTest("ROSN").standardTest(5, 1) }
    test("rostelecom 1 day candles percent stop, take profit") { new SimpleTest("RTKM", 0.3).standardTest(5, 1) }

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
}
