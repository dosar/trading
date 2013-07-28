package logic

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import tradingsystems.TradingData
import tradingideas.WilliamsAdMax

/**
 * @author alespuh
 * @since 25.07.13
 */
@RunWith(classOf[JUnitRunner])
class WilliamsAdMax_Test extends FunSuite with TestUtils
{
    test("1 day position")
    {
        val data = inputCandles(1, 2, 3, 4, 5, 1, 2, 3, 4.1)
        println(data.williamsAD)
        val filteredPositions = new WilliamsAdMax(5, 1, 1).filterInterestingPositions(data)
        assert(1 === filteredPositions.length)
        assert(candle(4.1) === filteredPositions(0).candles(0))
    }

    test("1 day after 2 days position")
    {
        val data = inputCandles(1, 2, 3, 4, 5, 1, 2, 3, 4.1, 4.2)
        println(data.williamsAD)
        val filteredPositions = new WilliamsAdMax(5, 1, 2).filterInterestingPositions(data)
        assert(1 === filteredPositions.length)
        assert(candle(4.2) === filteredPositions(0).candles(0))
    }

    test("2 day position")
    {
        val data = inputCandles(1, 2, 2, 4.1, 3.1, 1.1, 2.1, 3.2, 4.2)
        println(data.williamsAD)
        val filteredPositions = new WilliamsAdMax(3, 2, 1).filterInterestingPositions(data)
        assert(2 === filteredPositions.length)
        assert(candle(3.1) === filteredPositions(0).candles(0))
        assert(candle(1.1) === filteredPositions(0).candles(1))
        assert(candle(3.2) === filteredPositions(1).candles(0))
        assert(candle(4.2) === filteredPositions(1).candles(1))
    }
}
