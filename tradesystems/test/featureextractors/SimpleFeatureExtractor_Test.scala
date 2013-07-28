package featureextractors

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import featuresextractors.SimpleFeatureExtractor
import tradingsystems.{Candle, TradingData}
import tradinganalyzers.statistics.{StandardImporter, AnalyticalStatisticsPrinter}
import logic.TestUtils

/**
 * @author alespuh
 * @since 15.07.13
 */
@RunWith(classOf[JUnitRunner])
class SimpleFeatureExtractor_Test extends FunSuite with TestUtils
{
    val extractor = new SimpleFeatureExtractor()
    val sberCandles = StandardImporter.importSber
    var currentDay: Iterator[Double] = null

    def forr(dayFeatures: Array[Double])(f: => Unit)
    {
        currentDay = dayFeatures.iterator
        f
    }

    def checkDayFeature(dayOfWeek: Int, tradeWeekOfYear: Int, monthOfYear: Int, close_open: Double, volume: Int)
    {
        assert(dayOfWeek === currentDay.next)
        assert(tradeWeekOfYear === currentDay.next)
        assert(monthOfYear === currentDay.next)
        assert(close_open.nonStrict === currentDay.next)
        assert(volume === currentDay.next)
    }

    test("empty input"){ assert(Array.empty === extractor.generateFor(TradingData(Vector.empty))) }
    test("real data input")
    {
        val featureMatrix = extractor.generateFor(sberCandles)
        forr(featureMatrix(0))
        {
            checkDayFeature(1, 2, 1, 0.13, 148661237)
            checkDayFeature(2, 2, 1, -1.55, 130276079)
            checkDayFeature(3, 2, 1, 2.21, 128684773)
            checkDayFeature(4, 2, 1, 0.05, 111263614)
            checkDayFeature(5, 2, 1, 0.68, 142060148)
            checkDayFeature(1, 3, 1, 2.63, 179399350)
            checkDayFeature(2, 3, 1, 1.5, 268915291)
            checkDayFeature(3, 3, 1, -1.4, 220273259)
            checkDayFeature(4, 3, 1, -2.08, 219353508)
            checkDayFeature(5, 3, 1, -1.96, 221223200)
            assert(2.39.nonStrict === currentDay.next())//close - open on next day
        }
        forr(featureMatrix(280))
        {
            checkDayFeature(5, 8, 2, 2.15, 173055676)
            checkDayFeature(1, 9, 2, 1.27, 172685141)
            checkDayFeature(2, 9, 3, -2.62, 193923890)
            checkDayFeature(3, 9, 3, 0.51, 155257370)
            checkDayFeature(4, 9, 3, 1.78, 182343720)
            checkDayFeature(5, 9, 3, -2.1, 147805750)
            checkDayFeature(6, 9, 3, 1.09, 41881820)
            checkDayFeature(3, 10, 3, 1.06, 205367670)
            checkDayFeature(4, 10, 3, -2.22, 198655700)
            checkDayFeature(5, 10, 3, -0.24, 240633670)
            assert(-0.86.nonStrict === currentDay.next())//close - open on next day
        }
    }
}
