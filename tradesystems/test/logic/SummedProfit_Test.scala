package logic

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import tradingsystems.SummedProfit

/**
 * @author alespuh
 * @since 09.07.13
 */
@RunWith(classOf[JUnitRunner])
class SummedProfit_Test extends TestUtils with FunSuite
{
    test("negative slump exists")
    {
        val profit = SummedProfit(createProfits(Vector((100.0, 3.1), (25, -1.3), (50, -1), (80, 5))))//3.1, -5.2, -2, 6.25
        assert(0.0215.nonStrict === profit.profit)
        assert(2.15.nonStrict === profit.profitPct)
        assert(-0.041.nonStrict === profit.slump)
        assert(-4.1.nonStrict === profit.slumpPct)
    }

    test("negative slump isnt exist")
    {
        val profit = SummedProfit(createProfits(Vector((100.0, 3.1), (25, -0.3), (50, -0.2))))//3.1, -1.2, -0.4
        assert(0.015 === profit.profit)
        assert(1.5 === profit.profitPct)
        assert(0.0 === profit.slump)
        assert(0.0 === profit.slumpPct)
    }
}
