package logic

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import tradingideas.MergingFigureCandles
import tradingsystems.{Candle, TradingData}

/**
 * @author alespuh
 * @since 17.07.13
 */
@RunWith(classOf[JUnitRunner])
class MergingFigureCandles_Test extends FunSuite with TestUtils
{
    test("test 1 opposite day")
    {
        val data = TradingData(Vector(candle(1), candle(2), candle(-1), candle(100), candle(20), candle(10)))
        val filteredPositions = new MergingFigureCandles(2, 3, 1, _.buyProfit > 0).filterInterestingPositions(data)
        assert(1 === filteredPositions.length)
        assert(candle(100) === filteredPositions(0).candles(0))
        assert(candle(20) === filteredPositions(0).candles(1))
        assert(candle(10) === filteredPositions(0).candles(1))
    }

    test("test 2 opposite day")
    {
        val data = TradingData(Vector(candle(1), candle(2), candle(-1), candle(-100), candle(20), candle(10), candle(12)))
        val filteredPositions = new MergingFigureCandles(2, 2, 2, _.buyProfit > 0).filterInterestingPositions(data)
        assert(1 === filteredPositions.length)
        assert(candle(20) === filteredPositions(0).candles(0))
        assert(candle(10) === filteredPositions(0).candles(1))
    }
}