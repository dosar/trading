package logic

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import tradingsystems.Candle
import org.joda.time.LocalDate
import tradinganalyzers.TradingPosition

@RunWith(classOf[JUnitRunner])
class TradingPosition_Test extends FunSuite with TestUtils
{
    val position = new TradingPosition(Candle(new LocalDate(2013, 1, 15), 100.7, 101.00, 99.78, 99.85),
        Candle(new LocalDate(2013, 1, 16), 99.93, 100.13, 99.19, 99.97),
        Candle(new LocalDate(2013, 1, 17), 99.90, 101.33, 99.54, 101.15))

    test("position properties")
    {
        assert(new LocalDate(2013, 1, 15) === position.positionDate)
        assert(100.7 === position.open)
        assert(101.33 === position.high)
        assert(2 === position.highCandleIndex)
        assert(99.19 === position.low)
        assert(1 === position.lowCandleIndex)
        assert(101.15 === position.close)
    }
}
