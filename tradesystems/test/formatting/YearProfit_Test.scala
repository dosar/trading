package formatting

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import tradingsystems._
import org.joda.time.LocalDate
import tradingsystems.MonthProfit
import tradingsystems.YearProfit
import tradingsystems.Balance
import logic.TestUtils

/**
 * @author alespuh
 * @since 06.07.13
 */
@RunWith(classOf[JUnitRunner])
class YearProfit_Test extends FunSuite with TestUtils
{
    val mps = Vector(m1, m2, m3)
    val balance = BalanceCalculator.balanceHistoryM(mps)
    val yearProfit = YearProfit(2013, balance, 78.3, mps)
    test("profit"){ assert(balance.profit.profitPct === yearProfit.yearProfitPct) }
    test("yearSlump"){ assert(balance.profit.slumpPct === yearProfit.yearSlumpPct) }
    test("monthAverageProfitPct"){ assert(-4.93.nonStrict === yearProfit.monthAverageProfitPct) }
    test("worstMonthSlumpPct"){ assert(-15.8 === yearProfit.worstMonthSlumpPct) }
    test("daysCount"){ assert("004" === yearProfit.daysCount) }
    test("positiveDeals"){ assert("002" === yearProfit.positiveDeals) }
    test("negativeDeals"){ assert("002" === yearProfit.negativeDeals) }
    test("positiveProfit"){ assert(false === yearProfit.positiveProfit) }
    test("strictProfit -15"){ assert(true === yearProfit.strictProfit(-15)) }
    test("strictProfit -14"){ assert(false === yearProfit.strictProfit(-14)) }
}
