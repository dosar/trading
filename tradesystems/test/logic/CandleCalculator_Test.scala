package logic

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import tradingsystems.CandleCalculator

/**
 * @author alespuh
 * @since 29.06.13
 */
@RunWith(classOf[JUnitRunner])
class CandleCalculator_Test extends FunSuite
{
    test("smoke test")
    {
        val history = CandleCalculator.balanceHistory(List[Double](1, -2, 3, 4, 5))
        assert(11 === history.profit)
        assert(-1 === history.slump)
    }
}
