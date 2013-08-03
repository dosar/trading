package featureextractors

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import logic.TestUtils
import tradingsystems.{Candle, TradingData}
import featuresextractors.{TrendStat, SignalStatistics, DaysDirectionSignalExtractor}

/**
 * @author alespuh
 * @since 18.07.13
 */
@RunWith(classOf[JUnitRunner])
class DaysDirectionSignalExtractor_Test extends FunSuite with TestUtils
{
    type Positive = Int; type Negative = Int
    def checkSignal(expectedSignal: Vector[Int], expectedTrend: Vector[(Positive, Negative)], actual: SignalStatistics, trendInd: Int) =
    {
        assert(expectedSignal === actual.signal)
        assert(trendStat(expectedTrend:_*) === actual.statistics(trendInd))
    }

    def trendStat(stat: (Positive, Negative)*) = stat.map{case (pos, neg) => TrendStat(pos, neg)}.toVector

    test("test positive signals")
    {
        val input = inputCandles(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1)
        val statistics = new DaysDirectionSignalExtractor(input).getSignalStatistics
        checkSignal(Vector(1, 1), Vector((12, 0), (11, 0), (10, 0), (9, 0), (8, 0), (7, 0), (6, 0)), statistics(0), 0)
        checkSignal(Vector(1, 1), Vector((11, 0), (10, 0), (9, 0), (8, 0), (7, 0), (6, 0)), statistics(0), 1)
        checkSignal(Vector(1, 1, 1), Vector((11, 0), (10, 0), (9, 0), (8, 0), (7, 0), (6, 0), (5, 0)), statistics(1), 0)
        checkSignal(Vector(1, 1, 1, 1), Vector((10, 0), (9, 0), (8, 0), (7, 0), (6, 0), (5, 0), (4, 0)), statistics(2), 0)
        checkSignal(Vector(1, 1, 1, 1, 1), Vector((9, 0), (8, 0), (7, 0), (6, 0), (5, 0), (4, 0), (3, 0)), statistics(3), 0)
        checkSignal(Vector(1, 1, 1, 1, 1, 1), Vector((8, 0), (7, 0), (6, 0), (5, 0), (4, 0), (3, 0), (2, 0)), statistics(4), 0)
        checkSignal(Vector(1, 1, 1, 1, 1, 1, 1), Vector((7, 0), (6, 0), (5, 0), (4, 0), (3, 0), (2, 0), (1, 0)), statistics(5), 0)
        checkSignal(Vector(1, 1, 1, 1, 1, 1, 1, 1), Vector((6, 0), (5, 0), (4, 0), (3, 0), (2, 0), (1, 0)), statistics(6), 0)
        checkSignal(Vector(1, 1, 1, 1, 1, 1, 1, 1, 1), Vector((5, 0), (4, 0), (3, 0), (2, 0), (1, 0)), statistics(7), 0)
        checkSignal(Vector(1, 1, 1, 1, 1, 1, 1, 1, 1, 1), Vector((4, 0), (3, 0), (2, 0), (1, 0)), statistics(8), 0)
        checkSignal(Vector(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1), Vector((3, 0), (2, 0), (1, 0)), statistics(9), 0)
    }

    test("test signals with negative")
    {
        val input = inputCandles(1, 1, -0.5, 1, 1)
        val statistics = new DaysDirectionSignalExtractor(input).getSignalStatistics
        checkSignal(Vector(1, 1), Vector((0, 1), (1, 0), (1, 0)), statistics(0), 0)
        checkSignal(Vector(1, -1), Vector((1, 0), (1, 0)), statistics(1), 0)
        checkSignal(Vector(-1, 1), Vector((1, 0)), statistics(2), 0)
        checkSignal(Vector(1, 1, -1), Vector((1, 0), (1, 0)), statistics(3), 0)
        checkSignal(Vector(1, -1, 1), Vector((1, 0)), statistics(4), 0)
        checkSignal(Vector(1, 1, -1, 1), Vector((1, 0)), statistics(5), 0)
    }

    test("getSignal")
    {
        val candles = inputCandles(Vector[(Open, Close)]((100, 101), (99, 100.2), (100, 99), (100, 102), (99, 100.1), (98, 99), (100, 105), (99.4, 99.9), (98, 99.8))).data
        val extractor = new DaysDirectionSignalExtractor(TradingData(Vector.empty, ""))
        assert(Vector(1, 1, -1, 1) === extractor.getSignal(candles, 4, 4))
        assert(Vector(1, 1, -1) === extractor.getSignal(candles, 3, 3))
        assert(Vector(1, 1) === extractor.getSignal(candles, 2, 2))
        assert(Vector(1, -1, 1, 1, 1) === extractor.getSignal(candles, 6, 5))
        assert(Vector(1, -1, 1, 1) === extractor.getSignal(candles, 5, 4))
        assert(Vector(1, -1, 1) === extractor.getSignal(candles, 4, 3))
        assert(Vector(1, -1) === extractor.getSignal(candles, 3, 2))
    }

    test("getTrendStatistics")
    {
        val candles = inputCandles(Vector[(Open, Close)]((100, 101), (99, 100.2), (100, 99), (100, 102), (99, 100.1), (98, 99), (100, 105), (99.4, 99.9), (98, 99.8))).data
        val extractor = new DaysDirectionSignalExtractor(TradingData(Vector.empty, ""))
        assert(Vector((1, 0), (1, 0), (0, 1), (1, 0), (1, 0), (0, 1), (1, 0)) === extractor.getTrendStatistics(candles, 0)(0))
        assert(Vector((1, 0), (0, 1), (1, 0), (1, 0), (0, 1), (1, 0)) === extractor.getTrendStatistics(candles, 0)(1))
        assert(Vector((0, 1), (1, 0), (1, 0), (0, 1), (1, 0)) === extractor.getTrendStatistics(candles, 0)(2))
        assert(Vector((1, 0), (0, 1), (1, 0), (1, 0), (0, 1), (1, 0), (1, 0)) === extractor.getTrendStatistics(candles, 1)(0))
        assert(Vector((0, 1), (1, 0), (1, 0), (0, 1), (1, 0), (0, 1), (0, 1)) === extractor.getTrendStatistics(candles, 2)(0))
        assert(Vector((1, 0), (1, 0), (0, 1), (1, 0), (0, 1), (0, 1)) === extractor.getTrendStatistics(candles, 3)(0))
        assert(Vector((1, 0), (0, 1), (1, 0), (1, 0), (1, 0)) === extractor.getTrendStatistics(candles, 4)(0))
        assert(Vector((1, 0), (1, 0), (1, 0), (1, 0)) === extractor.getTrendStatistics(candles, 5)(0))
        assert(Vector((1, 0), (0, 1), (0, 1)) === extractor.getTrendStatistics(candles, 6)(0))
        assert(Vector((1, 0), (1, 0)) === extractor.getTrendStatistics(candles, 7)(0))
        assert(Vector((1, 0)) === extractor.getTrendStatistics(candles, 8)(0))
    }

    test("sum")
    {
        def check(expected: Vector[TrendStat], actual: Vector[TrendStat]) =
        {
            assert(expected.length === actual.length)
            for(ind <- 0 until expected.length)
            {
                assert(expected(ind).positive === actual(ind).positive)
                assert(expected(ind).negative === actual(ind).negative)
            }
        }

        val extractor = new DaysDirectionSignalExtractor(TradingData(Vector.empty, ""))
        val summed = extractor.sum(Vector(
            Vector(
                Vector((02, -01), (03, 04), (-05, 03), (01, 0)),
                           Vector((19, 21), (22, 10), (9, -10)),
                                     Vector((19, 21), (22, 15)),
                                               Vector((13, 07))),
            Vector(
                Vector((01, 03), (03, 07), (23, 11), (10, -23)),
                          Vector((11, -11), (-21, -3), (-11, 5)),
                                     Vector((02, -5), (-21, -3)),
                                               Vector((07, 13)))))
        check(trendStat((3, 2), (06, 11), (18, 14), (11, -23)), summed(0))
        check(trendStat((30, 10), (1, 7), (-2, -5)), summed(1))
        check(trendStat((21, 16), (1, 12)), summed(2))
        check(trendStat((20, 20)), summed(3))
    }

    test("getAccumulatedTrend")
    {
        val candles = inputCandles(Vector[(Open, Close)]((100, 101), (99, 100.2), (100, 99), (100, 102))).data
        val extractor = new DaysDirectionSignalExtractor(TradingData(Vector.empty, ""))
        val trend0 = extractor.getTrendStatistics(candles, 0)
        assert(Vector((1, 0), (1, 0), (0, 1), (1, 0)) === trend0(0))
        assert(Vector((1, 0), (0, 1), (1, 0)) === trend0(1))
        assert(Vector((0, 1), (1, 0)) === trend0(2))
        assert(Vector((1, 0)) === trend0(3))
        val trend1 = extractor.getTrendStatistics(candles, 1)
        assert(Vector((1, 0), (0, 1), (1, 0)) === trend1(0))
        assert(Vector((0, 1), (1, 0)) === trend1(1))
        assert(Vector((1, 0)) === trend1(2))
        val trend2 = extractor.getTrendStatistics(candles, 2)
        assert(Vector((0, 1), (1, 0)) === trend2(0))
        assert(Vector((1, 0)) === trend2(1))
        val trend3 = extractor.getTrendStatistics(candles, 3)
        assert(Vector((1, 0)) === trend3(0))
    }
}