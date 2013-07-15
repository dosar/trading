package logic

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import tradingsystems.MultipliedProfit

/**
 * @author alespuh
 * @since 15.07.13
 */
@RunWith(classOf[JUnitRunner])
class MultipliedProfit_Test extends TestUtils with FunSuite
{
    test("negative slump exists")
    {
        val profit = MultipliedProfit(createProfits(Vector((100.0, 3.1), (25, -1.3), (50, -1), (80, 5))))//3.1, -5.2, -2, 6.25
        println(profit.profit)
        assert(((1 + 0.031) * (1 - 0.052) * (1 - 0.02) * (1 + 0.0625)) - 1 === profit.profit)
        assert((-1 * (1 - 0.95784024)).nonStrict === profit.slump)
    }
}