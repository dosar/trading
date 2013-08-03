package logic

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import tradingsystems.{TradingData, Candle}
import tradingideas.SignalCandles

/**
 * @author alespuh
 * @since 19.07.13
 */
@RunWith(classOf[JUnitRunner])
class SignalCandles_Test extends FunSuite with TestUtils
{
    test("position selected")
    {
        val data = TradingData(Vector(candle(1), candle(2), candle(-1), candle(100), candle(20), candle(-5)), "")
        val filteredPositions = new SignalCandles(Vector(1, 1, -1, 1), 1 to 2).filterInterestingPositions(data)
        assert(1 === filteredPositions.length)
        assert(candle(20) === filteredPositions(0).candles(0))
        assert(candle(-5) === filteredPositions(0).candles(1))
    }

    test("position selected from 2 to 3")
    {
        val data = TradingData(Vector(candle(1), candle(2), candle(-1), candle(100), candle(20), candle(-5)), "")
        val filteredPositions = new SignalCandles(Vector(1, 1, -1), 2 to 2).filterInterestingPositions(data)
        assert(1 === filteredPositions.length)
        assert(candle(20) === filteredPositions(0).candles(0))
    }

    test("position not selected")
    {
        val data = TradingData(Vector(candle(1), candle(2), candle(-1), candle(100), candle(20), candle(-5)), "")
        val filteredPositions = new SignalCandles(Vector(1, 1, -1, 1), 1 to 3).filterInterestingPositions(data)
        assert(0 === filteredPositions.length)
    }
}
