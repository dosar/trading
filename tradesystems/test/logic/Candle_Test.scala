package logic

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import tradingsystems._
import org.joda.time.LocalDate
import tradingsystems.Balance
import tradingsystems.Candle
import tradingsystems.Profit

trait TestUtils
{
    private val tolerance = 0.0001

    def abs(x: Double) = if(x < 0) -x else x

    def isCloseEnough(x: Double, y: Double) = x == y || abs((x - y) / x) / x < tolerance

    type Start = Double; type Change = Double
    type Profits = Vector[(Start, Change)]
    type Year = Int; type Month = Int; type Day = Int
    type Dates = Vector[(Year, Month, Day)]

    def createBalance(profits: Profits, positiveStartDatePositions: Dates, negativeStartDatePositions: Dates) =
        Balance(AccumulatedProfit.Accumulator(profits.map(p => Profit(p._1, p._2))),
            positiveStartDatePositions.map(d => new LocalDate(d._1, d._2, d._3)),
            negativeStartDatePositions.map(date => new LocalDate(date._1, date._2, date._3)))

    def createProfits(profits: Profits): Vector[Profit] = profits.map(p => Profit(p._1, p._2))

    val m1 = MonthProfit(1, createBalance(Vector((50.0, 2.1), (20, -4)), Vector((2013, 1, 1)), Vector((2013, 1, 2))))
    val m2 = MonthProfit(2, createBalance(Vector((100.0, 3.5)), Vector((2013, 2, 1)), Vector()))
    val m3 = MonthProfit(3, createBalance(Vector((80.0, -2.0)), Vector(), Vector((2013, 3, 2))))

    protected implicit def toLocalDate(origin: (Year, Month, Day)) =
        new Object { def toLocalDate = new LocalDate(origin._1, origin._2, origin._3) }

    protected implicit def nonStrict(origin: Double) =
    {
        class DoubleWrapper(val d: Double)
        {
            override def equals(obj: Any): Boolean = obj match
            {
                case number: Double => isCloseEnough(d, number)
                case dw: DoubleWrapper => dw.equals(d)
                case x => false
            }

            override def toString: String = d.toString

            def nonStrict = this
        }
        new DoubleWrapper(origin)
    }
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
