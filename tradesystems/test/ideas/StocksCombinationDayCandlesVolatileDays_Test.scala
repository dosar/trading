package ideas

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import tradingsystems.YearProfit
import tradinganalyzers.statistics.VolatileDaysCombinationStatisticalPrinter

@RunWith(classOf[JUnitRunner])
class StocksCombinationDayCandlesVolatileDays_Test extends FunSuite with VolatileDaysCombinationStatisticalPrinter
{
    override val targetProfit: Double = 170
    override val strategiesFile: String = "trading_stabile_ideas.txt"

    test("brute force all two strategies combinations"){ test2StrategiesCombination() }

    test("brute force all three strategies combinations"){ test3StrategiesCombination() }
}
