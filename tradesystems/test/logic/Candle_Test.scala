package logic

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import tradingsystems.Candle

trait TestUtils
{
    private val tolerance = 0.0001

    def abs(x: Double) = if(x < 0) -x else x

    def isCloseEnough(x: Double, y: Double) = x == y || abs((x - y) / x) / x < tolerance
}

@RunWith(classOf[JUnitRunner])
class Candle_Test extends FunSuite with TestUtils
{
    test("buyProfitPct")
    {
        assert(isCloseEnough(0.5, Candle(null, 200, 208, 199, 201).buyProfitPct))
        assert(isCloseEnough(1, Candle(null, 200, 208, 199, 202).buyProfitPct))
        assert(isCloseEnough(0.0, Candle(null, 200, 208, 199, 200).buyProfitPct))
        assert(isCloseEnough(-0.5, Candle(null, 200, 208, 199, 199).buyProfitPct))
    }

    test("buyProfit")
    {
        assert(1 === Candle(null, 200, 208, 199, 201).buyProfit)
        assert(2 === Candle(null, 200, 208, 199, 202).buyProfit)
        assert(0 === Candle(null, 200, 208, 199, 200).buyProfit)
        assert(-1 === Candle(null, 200, 208, 199, 199).buyProfit)
    }

    test("buySlumpPct")
    {
        assert(isCloseEnough(0.5, Candle(null, 200, 208, 199, 201).buySlumpPct))
        assert(isCloseEnough(0.5, Candle(null, 200, 208, 199, 202).buySlumpPct))
        assert(isCloseEnough(0.5, Candle(null, 200, 208, 199, 200).buySlumpPct))
        assert(isCloseEnough(0.5, Candle(null, 200, 208, 199, 199).buySlumpPct))
        assert(isCloseEnough(1.0, Candle(null, 200, 208, 198, 199).buySlumpPct))
        assert(isCloseEnough(50.0, Candle(null, 200, 208, 100, 199).buySlumpPct))
        assert(isCloseEnough(-2.5, Candle(null, 200, 208, 205, 199).buySlumpPct))
        assert(isCloseEnough(0.0, Candle(null, 200, 208, 200, 199).buySlumpPct))
    }

    test("buySlump")
    {
        assert(1 === Candle(null, 200, 208, 199, 201).buySlump)
        assert(20 === Candle(null, 200, 208, 180, 202).buySlump)
        assert(-3 === Candle(null, 200, 208, 203, 200).buySlump)
        assert(0 === Candle(null, 200, 208, 200, 199).buySlump)
    }

    test("sellProfitPct")
    {
        assert(isCloseEnough(-0.5, Candle(null, 200, 208, 199, 201).sellProfitPct))
        assert(isCloseEnough(-1, Candle(null, 200, 208, 199, 202).sellProfitPct))
        assert(isCloseEnough(0.0, Candle(null, 200, 208, 199, 200).sellProfitPct))
        assert(isCloseEnough(0.5, Candle(null, 200, 208, 199, 199).sellProfitPct))
    }

    test("sellProfit")
    {
        assert(9 === Candle(null, 210, 208, 199, 201).sellProfit)
        assert(-2 === Candle(null, 200, 208, 180, 202).sellProfit)
        assert(0 === Candle(null, 200, 208, 203, 200).sellProfit)
        assert(1 === Candle(null, 200, 208, 200, 199).sellProfit)
    }

    test("sellSlumpPct")
    {
        assert(isCloseEnough(4.0, Candle(null, 200, 208, 199, 201).sellSlumpPct))
        assert(isCloseEnough(4.0, Candle(null, 200, 208, 199, 202).sellSlumpPct))
        assert(isCloseEnough(4.0, Candle(null, 200, 208, 199, 200).sellSlumpPct))
        assert(isCloseEnough(4.0, Candle(null, 200, 208, 199, 199).sellSlumpPct))
        assert(isCloseEnough(4.0, Candle(null, 200, 208, 198, 199).sellSlumpPct))
        assert(isCloseEnough(4.0, Candle(null, 200, 208, 100, 199).sellSlumpPct))
        assert(isCloseEnough(4.0, Candle(null, 200, 208, 205, 199).sellSlumpPct))
        assert(isCloseEnough(4.0, Candle(null, 200, 208, 200, 199).sellSlumpPct))
        assert(isCloseEnough(3.0, Candle(null, 200, 206, 198, 199).sellSlumpPct))
        assert(isCloseEnough(1.5, Candle(null, 200, 203, 100, 199).sellSlumpPct))
        assert(isCloseEnough(0.0, Candle(null, 200, 200, 205, 199).sellSlumpPct))
        assert(isCloseEnough(-0.5, Candle(null, 200, 199, 200, 199).sellSlumpPct))
    }

    test("sellSlump")
    {
        assert(-2 === Candle(null, 210, 208, 199, 201).sellSlump)
        assert( 8 === Candle(null, 200, 208, 180, 202).sellSlump)
        assert( 0 === Candle(null, 200, 200, 203, 200).sellSlump)
    }
}
