package formatting

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import tradingsystems.{MonthProfit, Balance, YearProfit}
import tradingsystems.MonthProfit
import org.joda.time.LocalDate

/**
 * @author alespuh
 * @since 06.07.13
 */
@RunWith(classOf[JUnitRunner])
class YearProfit_Test  extends FunSuite
{
    test("test year part of statistics formatting")
    {
        val yp = YearProfit(2013, Balance(10, -2, 36, 12, 24, Vector(), Vector()), 100, Vector(
            MonthProfit(1, Balance(2, -1, 2, 1, 1, Vector(new LocalDate(2013, 1, 1), new LocalDate(2013, 5, 3)), Vector())),
            MonthProfit(2, Balance(2, -1, 2, 1, 1, Vector(), Vector(new LocalDate(2013, 3, 11), new LocalDate(2013, 6, 18))))
        ))
        println(yp.yearProfitString)
        println(yp.monthSlumpsString)
    }
}
