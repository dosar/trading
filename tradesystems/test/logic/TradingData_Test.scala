package logic

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import tradingsystems.{Candle, TradingData}
import org.joda.time.LocalDate

/**
 * @author alespuh
 * @since 08.07.13
 */
@RunWith(classOf[JUnitRunner])
class TradingData_Test extends FunSuite with TestUtils
{
    test("avg prices")
    {
        assert(99 === TradingData(Vector(
            Candle(new LocalDate(2013, 1, 2), 100, 0, 0, 0),
            Candle(new LocalDate(2013, 1, 3), 99, 0, 0, 0),
            Candle(new LocalDate(2013, 1, 4), 98, 0, 0, 0))).avgPrice(2013))
    }
}
