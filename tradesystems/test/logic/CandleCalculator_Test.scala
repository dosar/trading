package logic

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import tradingsystems.CandleCalculator
import org.joda.time.LocalDate

/**
 * @author alespuh
 * @since 29.06.13
 */
@RunWith(classOf[JUnitRunner])
class CandleCalculator_Test extends FunSuite
{
    test("smoke test")
    {
        val history = CandleCalculator.balanceHistory(List[(LocalDate, Double)]((null, 1), (null, -2), (null, 3), (null, 4), (null, 5)))
        assert(11 === history.profit)
        assert(-1 === history.slump)
    }
}
