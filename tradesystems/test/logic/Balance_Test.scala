package logic

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * @author alespuh
 * @since 09.07.13
 */
@RunWith(classOf[JUnitRunner])
class Balance_Test extends FunSuite with TestUtils
{
    test("balance lazy vals")
    {
        val balance = createBalance(Vector((100.0, 3.0), (100.0, -0.5)), Vector((2013, 1, 1)), Vector((2013, 1, 2)))
        assert(0 === balance.profit.slump)
        assert(2 === balance.daysCount)
        assert(1 === balance.positiveDeals)
        assert(1 === balance.positiveDeals)
    }
}
