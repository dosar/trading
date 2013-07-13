package ideas

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import tradingsystems.YearProfit
import tradinganalyzers.statistics.VolatileDaysCombinationStatisticalPrinter

@RunWith(classOf[JUnitRunner])
class StocksCombinationDayCandlesVolatileDays_Test extends FunSuite with VolatileDaysCombinationStatisticalPrinter
{
    test("brute force all two strategies combinations"){ test2StrategiesCombination() }

    override def isUsefulOutput(yps: Vector[YearProfit]): Boolean =
        yps.length == 4 && yps.take(3).forall(_.yearProfitPct >= 65)
}
