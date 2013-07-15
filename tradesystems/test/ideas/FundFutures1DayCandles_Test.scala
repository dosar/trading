package ideas

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import tradingsystems.Candle
import tradinganalyzers.statistics.{AnalyticalStatisticsPrinter, TooTrendyDaysStatisticalPrinter}

/**
 * @author alespuh
 * @since 14.06.13
 */
@RunWith(classOf[JUnitRunner])
class FundFutures1DayCandles_Test extends FunSuite
{
    class SimpleTest(val ticker: String) extends TooTrendyDaysStatisticalPrinter
    {
        override lazy val data = AnalyticalStatisticsPrinter.standardImport("goods\\" + ticker)
    }

    test("brent 1 day candles standard test") { new SimpleTest("BRENT").standardTest(5, 1) }
    test("gold 1 day candles standard test") { new SimpleTest("GOLD").standardTest(5, 1) }
    test("light 1 day candles standard test") { new SimpleTest("LIGHT").standardTest(5, 1) }
    test("benzine 1 day candles standard test") { new SimpleTest("NYMEX.XRB").standardTest(5, 1) }
    test("sugar 1 day candles standard test") { new SimpleTest("US2.SB").standardTest(5, 1) }
    test("пшеница 1 day candles standard test") { new SimpleTest("US3.ZW").standardTest(5, 1) }

    test("gold detailed")
    {
        val tester = new SimpleTest("GOLD")
        tester.detailedTest(12, 1, 4, 4)//the best
        tester.detailedTest(3, 1, 3, 3)
    }
}
