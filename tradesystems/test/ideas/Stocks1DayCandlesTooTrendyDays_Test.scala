package ideas

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import tradingideas.TradingIdea
import tradingsystems.Candle
import TradingIdea._
import tradinganalyzers.statistics.TooTrendyDaysStatisticalPrinter

/**
 * @author alespuh
 * @since 15.06.13
 */
@RunWith(classOf[JUnitRunner])
class Stocks1DayCandlesTooTrendyDays_Test extends FunSuite
{
    class SimpleTest(val ticker: String) extends TooTrendyDaysStatisticalPrinter

    test("sberbank 1 day candles percent stop, take profit") { new SimpleTest("SBER").standardTest(5, 1) }
    test("gazprom 1 day candles percent stop, take profit") { new SimpleTest("GAZP").standardTest(5, 1) }
    test("nornikel 1 day candles percent stop, take profit") { new SimpleTest("GMKN").standardTest(5, 1) }
    test("lukoil 1 day candles percent stop, take profit") { new SimpleTest("LKOH").standardTest(5, 1) }
    test("novatek 1 day candles percent stop, take profit") { new SimpleTest("NVTK").standardTest(5, 1) }
    test("rosneft 1 day candles percent stop, take profit") { new SimpleTest("ROSN").standardTest(5, 1) }
    test("rostelecom 1 day candles percent stop, take profit") { new SimpleTest("RTKM").standardTest(5, 1) }
}