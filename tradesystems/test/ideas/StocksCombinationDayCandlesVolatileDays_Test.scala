package ideas

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import tradingsystems.YearProfit
import tradinganalyzers.statistics.VolatileDaysCombinationStatisticalPrinter

@RunWith(classOf[JUnitRunner])
class StocksCombinationDayCandlesVolatileDays_Test extends FunSuite with VolatileDaysCombinationStatisticalPrinter
{
    override val targetProfit: Double = 60

    test("brute force all two strategies combinations"){ test2StrategiesCombination() }
}
