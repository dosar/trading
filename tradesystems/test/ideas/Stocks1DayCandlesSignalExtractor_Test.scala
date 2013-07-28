package ideas

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import logic.TestUtils
import featuresextractors.{TrendStat, DaysDirectionSignalExtractor}
import tradinganalyzers.statistics.{StandardImporter, AnalyticalStatisticsPrinter}
import tradingsystems.TradingData

/**
 * @author alespuh
 * @since 19.07.13
 */
@RunWith(classOf[JUnitRunner])
class Stocks1DayCandlesSignalExtractor_Test extends FunSuite with TestUtils
{
    final val limit = 0.65

    def strongSignal(stat: TrendStat) = stat.sumGreater(50) && stat.ratio > 0.65

    def check(data: TradingData)
    {
        for(element <- new DaysDirectionSignalExtractor(data).getSignalStatistics)
        {
            if(element.statistics.exists(_.exists(strongSignal)))
            {
                println(element.signal.mkString("|") + " = ")
                for((statistics, ind) <- element.statistics.zipWithIndex)
                    println((" " * (6 * ind)) + statistics.mkString("(", ",", ")"))
            }
        }
    }

    test("brute force signals on SBER"){ check(StandardImporter.importSber) }
    test("brute force signals on RTKM"){ check(StandardImporter.importRtkm) }
    test("brute force signals on ROSN"){ check(StandardImporter.importRosn) }
    test("brute force signals on NVTK"){ check(StandardImporter.importNvtk) }
    test("brute force signals on LKOH"){ check(StandardImporter.importLkoh) }
    test("brute force signals on GMKN"){ check(StandardImporter.importGmkn) }
    test("brute force signals on GAZP"){ check(StandardImporter.importGazp) }
}
