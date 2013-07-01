package ideas

import logic.TooTrendyDaysStatisticalPrinter
import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import tradingideas.TradingIdea
import tradingsystems.Candle
import TradingIdea._

/**
 * @author alespuh
 * @since 15.06.13
 */
@RunWith(classOf[JUnitRunner])
class Stocks1DayCandlesTooTrendyDays_Test extends FunSuite
{
    class SimpleTest(tiker: String) extends TooTrendyDaysStatisticalPrinter
    {
        val data = standardImport("g:\\work\\trademachine\\" + tiker + "_2010_2013_1day.txt")

        override def standardTest(initialStopPercent: Double, initialTakeProfitPercent: Double)
        {
            println(tiker)
            super.standardTest(initialStopPercent, initialTakeProfitPercent)
        }

        override def detailedTest(stop: Double, takeProfit: Double, positiveDays: Int, negativeDays: Int)
        {
            println(tiker)
            super.detailedTest(stop, takeProfit, positiveDays, negativeDays)
        }
    }

    test("sberbank 1 day candles percent stop, take profit") { new SimpleTest("SBER").standardTest(5, 1) }
    test("gazprom 1 day candles percent stop, take profit") { new SimpleTest("GAZP").standardTest(5, 1) }
    test("nornikel 1 day candles percent stop, take profit") { new SimpleTest("GMKN").standardTest(5, 1) }
    test("lukoil 1 day candles percent stop, take profit") { new SimpleTest("LKOH").standardTest(5, 1) }
    test("novatek 1 day candles percent stop, take profit") { new SimpleTest("NVTK").standardTest(5, 1) }
    test("rosneft 1 day candles percent stop, take profit") { new SimpleTest("ROSN").standardTest(5, 1) }
    test("rostelecom 1 day candles percent stop, take profit") { new SimpleTest("RTKM").standardTest(5, 1) }
}