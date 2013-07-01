package logic

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import tradinganalyzers.{TradingPosition, TradingOp}
import tradingsystems.Candle
import org.joda.time.LocalDate

/**
 * @author alespuh
 * @since 16.06.13
 */
@RunWith(classOf[JUnitRunner])
class TradingOp_Test extends FunSuite with TestUtils
{
    import TradingOp._
    test("test positive buy operation")
    {
        val candle = Candle(null, 100, 108, 99, 101)
        assert(2.0 === buy(1.1, 2).profit(candle))
        assert(3.0 === buy(1.1, 3).profit(candle))
        assert(4.0 === buy(1.1, 4).profit(candle))
        assert(1 === buy(1.1, 8.1).profit(candle))
        assert(-1 === buy(1, 5).profit(candle))
        assert(-0.5 === buy(0.5, 2).profit(candle))
        assert(-0.5 === buy(0.5, 3).profit(candle))
        assert(-0.5 === buy(0.5, 4).profit(candle))
        assert(-0.5 === buy(0.5, 5).profit(candle))
    }

    test("test negative buy operation")
    {
        val candle = Candle(null, 101, 103, 99, 100)
        assert(1.01 === buy(2.01, 1).profit(candle))
        assert(isCloseEnough(-1.111, buy(1.1, 3).profit(candle)))
        assert(-0.505 === buy(0.5, 2).profit(candle))
        assert(-0.505 === buy(0.5, 3).profit(candle))
        assert(-0.505 === buy(0.5, 4).profit(candle))
        assert(isCloseEnough(-1.011, buy(1.001, 4).profit(candle)))//stop то сработал
        assert(-1.01 === buy(1, 2).profit(candle))
    }

    test("test buy operation")
    {
        val candle = Candle(null, 100, 103, 99, 101)
        assert(2.0 === buy(1.1, 2).profit(candle))
        assert(3.0 === buy(1.1, 3).profit(candle))
        assert(-0.5 === buy(0.5, 2).profit(candle))
        assert(-0.5 === buy(0.5, 3).profit(candle))
        assert(-0.5 === buy(0.5, 4).profit(candle))
        assert(1.0 === buy(1.001, 4).profit(candle))
        assert(-1.0 === buy(1, 2).profit(candle))
    }

    test("sell trading position")
    {
        val position = new TradingPosition(Candle(new LocalDate(2013, 1, 15), 100.7, 101.00, 99.78, 99.85),
            Candle(new LocalDate(2013, 1, 16), 99.93, 100.13, 99.19, 99.97),
            Candle(new LocalDate(2013, 1, 17), 99.90, 101.33, 99.54, 101.15))
        assert(isCloseEnough(-0.45, sell(1, 3).profit(position)._1))
        assert(isCloseEnough(1.007, sell(1, 1).profit(position)._1))
        assert(isCloseEnough(-0.5035, sell(0.5, 2).profit(position)._1))
    }

    test("buy trading position")
    {
        val position = new TradingPosition(Candle(new LocalDate(2013, 1, 15), 100.0, 101.00, 99.00, 99.65),
            Candle(new LocalDate(2013, 1, 16), 99.20, 101.13, 99.00, 101),
            Candle(new LocalDate(2013, 1, 17), 101.2, 102.4, 100.00, 102.05))
        assert(-1 === buy(1, 3).profit(position)._1)
        assert(2 === buy(1.1, 2).profit(position)._1)
        assert(isCloseEnough(2.05, buy(1.1, 3).profit(position)._1))
    }
}
