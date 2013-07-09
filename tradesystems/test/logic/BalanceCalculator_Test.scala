package logic

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import tradingsystems.{Profit, Balance, MonthProfit, BalanceCalculator}
import org.joda.time.LocalDate

/**
 * @author alespuh
 * @since 29.06.13
 */
@RunWith(classOf[JUnitRunner])
class BalanceCalculator_Test extends FunSuite with TestUtils
{
    test("balanceHistoryM")
    {
        val balance = BalanceCalculator.balanceHistoryM(Vector(m1, m2, m3))
        assert(createBalance(Vector((50.0, 2.1), (20, -4), (100.0, 3.5), (80.0, -2.0)),
            Vector((2013, 1, 1), (2013, 2, 1)), Vector((2013, 1, 2), (2013, 3, 2))) === balance)
    }

    test("balanceHistory")
    {
        val p1 = (new LocalDate(2013, 1, 1), Profit(24.0, 3.5))
        val p2 = (new LocalDate(2013, 2, 23), Profit(50.0, -1.5))
        val p3 = (new LocalDate(2013, 3, 8), Profit(100.0, 0.5))
        val balance = BalanceCalculator.balanceHistory(Vector(p1, p2, p3))
        assert(createBalance(Vector((24.0, 3.5), (50.0, -1.5), (100, 0.5)),
            Vector((2013, 1, 1), (2013, 3, 8)), Vector((2013, 2, 23))) === balance)
    }
}
