package logic

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import tradingsystems.{Candle, TradingData}
import org.joda.time.LocalDate
import tradinganalyzers.statistics.DayStandardImporter
import util.TradingImplicits.toDoubleImplicits

/**
 * @author alespuh
 * @since 08.07.13
 */
@RunWith(classOf[JUnitRunner])
class TradingData_Test extends FunSuite with TestUtils
{
    def getIndex(data: TradingData, date: LocalDate) =
        data.data.zipWithIndex.filter{case(candle, index) => candle.date == date}.head._2

    test("sber atr14")
    {
        val sber = DayStandardImporter.sber
        assert(2.17 === sber.atr14(getIndex(sber, new LocalDate(2013, 4, 17))).roundP(2))
        assert(2.65 === sber.atr14(getIndex(sber, new LocalDate(2013, 5, 8))).roundP(2))
        assert(2.47 === sber.atr14(getIndex(sber, new LocalDate(2013, 5, 22))).roundP(2))
    }

    test("sber williamsAD")
    {
        val sber = DayStandardImporter.sber
        assert(16.39 === sber.williamsAD(getIndex(sber, new LocalDate(2013, 1, 23))).roundP(2))
        assert(15.42 === sber.williamsAD(getIndex(sber, new LocalDate(2013, 1, 24))).roundP(2))
        assert(17.16 === sber.williamsAD(getIndex(sber, new LocalDate(2013, 1, 25))).roundP(2))
    }

    test("moving func")
    {
        val data = inputCandles(1, 2, 3, 4, 3, 4, 5, 6, 3, 6)
        val movingMax = data.movingFunc(5, _.map(data.data(_).close).max)
        assert(101 === movingMax(0))
        assert(102 === movingMax(1))
        assert(103 === movingMax(2))
        assert(104 === movingMax(3))
        assert(104 === movingMax(4))
        assert(104 === movingMax(5))
        assert(105 === movingMax(6))
        assert(106 === movingMax(7))
        assert(106 === movingMax(8))
        assert(106 === movingMax(9))
    }
}
