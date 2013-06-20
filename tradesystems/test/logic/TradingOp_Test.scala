package logic

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import tradinganalyzers.TradingOp
import tradingsystems.Candle

/**
 * @author alespuh
 * @since 16.06.13
 */
@RunWith(classOf[JUnitRunner])
class TradingOp_Test extends FunSuite with TestUtils
{
    test("test positive buy operation")
    {
        val candle = Candle(null, 100, 108, 99, 101)
        assert(2.0 === TradingOp.buy(1.1, 2).profit(candle))
        assert(3.0 === TradingOp.buy(1.1, 3).profit(candle))
        assert(4.0 === TradingOp.buy(1.1, 4).profit(candle))
        assert(1 === TradingOp.buy(1.1, 8.1).profit(candle))
        assert(-1 === TradingOp.buy(1, 5).profit(candle))
        assert(-0.5 === TradingOp.buy(0.5, 2).profit(candle))
        assert(-0.5 === TradingOp.buy(0.5, 3).profit(candle))
        assert(-0.5 === TradingOp.buy(0.5, 4).profit(candle))
        assert(-0.5 === TradingOp.buy(0.5, 5).profit(candle))
    }

    test("test negative buy operation")
    {
        val candle = Candle(null, 101, 103, 99, 100)
        assert(1.01 === TradingOp.buy(2.01, 1).profit(candle))
        assert(isCloseEnough(-1.111, TradingOp.buy(1.1, 3).profit(candle)))
        assert(-0.505 === TradingOp.buy(0.5, 2).profit(candle))
        assert(-0.505 === TradingOp.buy(0.5, 3).profit(candle))
        assert(-0.505 === TradingOp.buy(0.5, 4).profit(candle))
        assert(isCloseEnough(-1.011, TradingOp.buy(1.001, 4).profit(candle)))//stop то сработал
        assert(-1.01 === TradingOp.buy(1, 2).profit(candle))
    }

    test("test buy operation")
    {
        val candle = Candle(null, 100, 103, 99, 101)
        assert(2.0 === TradingOp.buy(1.1, 2).profit(candle))
        assert(3.0 === TradingOp.buy(1.1, 3).profit(candle))
        assert(-0.5 === TradingOp.buy(0.5, 2).profit(candle))
        assert(-0.5 === TradingOp.buy(0.5, 3).profit(candle))
        assert(-0.5 === TradingOp.buy(0.5, 4).profit(candle))
        assert(1.0 === TradingOp.buy(1.001, 4).profit(candle))
        assert(-1.0 === TradingOp.buy(1, 2).profit(candle))
    }
}
