package ideas

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import tradinganalyzers.statistics.{DayStandardImporter, AnalyticalStatisticsPrinter, VolatileDaysStatisticalPrinter}
import util.TradingImplicits.toSeqImplicits
import java.lang.Math._
import featuresextractors.SimpleFeatureExtractor
import tradingsystems.{YearProfit, TradingData}
import scala.io.Source
import scala.Array
import tradinganalyzers.TradingOp._
import tradinganalyzers.{TradingPositionAnalyzer, TradingPosition}
import logic.TestUtils

/**
 * @author alespuh
 * @since 14.07.13
 */
@RunWith(classOf[JUnitRunner])
class NeuralNetworkPreparation_Test extends FunSuite with TestUtils
{
    class SimpleTest(val ticker: String, override val targetProfit: Double = 19) extends VolatileDaysStatisticalPrinter

    val sber: TradingData = DayStandardImporter.sber

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

    test("prepare features")
    {
        //1 step. linear regression predicting close - open
        val features = new SimpleFeatureExtractor().generateFor(sber)
        for(dayFeatures <- features)
            println(dayFeatures.map(_.formatted("%.2f").replace(',', '.')).mkString(","))
    }

    // для наборов стопов и профитов по номеру метки определяем торговый день
    // и если метка положительная, то покупаем, если отрицательная, то продаем
    // здесь чуть лучше сумма процентов чем умножение
    test("check predictions")
    {
        val labels = Source.fromFile("g:\\work\\trademachine\\labels.txt").getLines().toVector.map(_.substring(1).toDouble)
//        assert(labels(0).nonStrict === sber.candles(10).buyProfit)

        new SimpleTest("", 10)
        {
            override def isUsefulOutput(yps: Vector[YearProfit]): Boolean = yps.length == 2 && yps(1).strictProfit(0)

            val result = (for(stop <- (1 to 20).map(_ * 0.1); takeProfit <- (1 to 10).map(_ * 0.5)) yield
            {
                val tradingPositionOps = for((label, index) <- labels.zipWithIndex) yield
                {
                    val op = if(label > 0) buy(stop, takeProfit)
                    else sell(stop, takeProfit)
//                    (TradingPosition(Array(sber.candles(index + 10))), op)
                    (new TradingPosition(Array(sber.data(index + 748))), op)
                }
                val yearProfits = new TradingPositionAnalyzer(tradingPositionOps.toArray).getStatistics
                getStringStatistics(stop.formatted("%.2f") + " | " + takeProfit.formatted("%.2f"), yearProfits)
            }).filter(_ != null).toArray
            result foreach println
        }
    }
}
