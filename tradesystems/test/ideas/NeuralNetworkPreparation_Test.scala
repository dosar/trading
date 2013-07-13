package ideas

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import tradinganalyzers.statistics.VolatileDaysStatisticalPrinter
import util.TradingImplicits.toSeqImplicits
import java.lang.Math._

/**
 * @author alespuh
 * @since 14.07.13
 */
@RunWith(classOf[JUnitRunner])
class NeuralNetworkPreparation_Test extends FunSuite
{
    class SimpleTest(val ticker: String, override val targetProfit: Double = 19) extends VolatileDaysStatisticalPrinter

    test("profit limit for stocks 1 day of 2013 year")
    {
        for(ticker <- Array("SBER", "GAZP", "GMKN", "LKOH", "NVTK", "ROSN", "RTKM"))
        {
            val yearCandles = new SimpleTest(ticker).data.data.filter(_.date.getYear == 2013)
            //open/close profit | max open/close, open/high, open/low | high/low
            println(ticker + " |" + yearCandles.sumBy(c => abs(c.buyProfitPct)).formatted("%7.2f") + " |" +
                yearCandles.sumBy(c => max(max(abs(c.buyProfitPct), abs(c.buySlumpPct)), abs(c.sellSlumpPct))).formatted("%7.2f") + " |" +
                yearCandles.sumBy(c => (c.high - c.low) / c.high * 100).formatted("%7.2f"))
        }
    }
}
