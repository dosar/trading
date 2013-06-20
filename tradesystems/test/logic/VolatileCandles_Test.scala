package logic

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import tradingideas.VolatileCandles
import tradingsystems.Candle
import org.joda.time.LocalDate

@RunWith(classOf[JUnitRunner])
class VolatileCandles_Test extends FunSuite with TestUtils
{
    val testList = List(
        Candle(null, 100, 103, 99, 101),
        Candle(null, 100, 103, 99, 101),
        Candle(null, 100, 103, 99, 101),
        Candle(new LocalDate(2013, 6, 21), 100, 103, 99, 101),
        Candle(null, 105, 101, 88, 110)
    )

    test("smoke")
    {
        val candles = new VolatileCandles(3, 2, null, _.buyProfit > 0).filterInterestingDays(testList)
        assert(1 === candles.size)
        assert(Candle(new LocalDate(2013, 6, 21), 100, 103, 88, 110) === candles.head._1)
    }

    test("SBER")
    {

    }
}
