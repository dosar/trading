package logic

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import tradingideas.{PositiveTrendCandles, LongTrendCandles}
import tradingsystems.{Candle, TradingData}
import org.joda.time.LocalDate
import tradinganalyzers.TradingPosition
import tradinganalyzers.statistics.{StandardImporter, AnalyticalStatisticsPrinter}
import tradingsystems.Candle

@RunWith(classOf[JUnitRunner])
class VolatileCandles_Test extends FunSuite with TestUtils with AnalyticalStatisticsPrinter
{
//    нужно чтобы было минимум 2 торговых дня
//    также нужно чтобы было вытряхивание по стопу тому или другому
    val ticker: String = null
    override lazy val data = new TradingData(StandardImporter.importSber.data.filter(_.date.getYear == 2013), "SBER")

    test("filter for 2 days rising and position for 4 days")
    {
        val tradingPositions = new PositiveTrendCandles(2, 4).filterInterestingPositions(
            TradingData(Vector(candle(2), candle(3.4), candle(-1.7), candle(2.1), candle(8.14), candle(7.41), candle(93.4)), ""))
        assert(1 === tradingPositions.length)
        assert(4 === tradingPositions(0).candles.length)
        assert(candle(-1.7) == tradingPositions(0).candles(0))
        assert(candle(2.1) == tradingPositions(0).candles(1))
        assert(candle(8.14) == tradingPositions(0).candles(2))
        assert(candle(7.41) == tradingPositions(0).candles(3))
    }

    test("filter trading days for 3 days rising SBER")
    {
        val tradingPositions = new PositiveTrendCandles(3, 3).filterInterestingPositions(data)
        val allCandlesIterator = tradingPositions.flatMap(_.candles).iterator

        def checkCandle(date: LocalDate, open: Double, high: Double, low: Double, close: Double) =
        {
            val actualCandle = allCandlesIterator.next
            assert(date === actualCandle.date)
            assert(open === actualCandle.open)
            assert(high === actualCandle.high)
            assert(low === actualCandle.low)
            assert(close === actualCandle.close)
        }

        checkCandle(new LocalDate(2013, 1, 15), 100.7, 101.00, 99.78, 99.85)
        checkCandle(new LocalDate(2013, 1, 16), 99.93, 100.13, 99.19, 99.97)
        checkCandle(new LocalDate(2013, 1, 17), 99.90, 101.33, 99.54, 101.15)
        checkCandle(new LocalDate(2013, 1, 21), 103.55, 103.95, 102.78, 103.64)
        checkCandle(new LocalDate(2013, 1, 22), 103.70, 103.85, 102.07, 102.35)
        checkCandle(new LocalDate(2013, 1, 23), 102.73, 103.65, 102.18, 103.45)
        checkCandle(new LocalDate(2013, 1, 22), 103.70, 103.85, 102.07, 102.35)
        checkCandle(new LocalDate(2013, 1, 23), 102.73, 103.65, 102.18, 103.45)
        checkCandle(new LocalDate(2013, 1, 24), 103.06, 103.07, 101.92, 102.48)
        checkCandle(new LocalDate(2013, 2, 13), 109.17, 109.34, 107.80, 108.80)
        checkCandle(new LocalDate(2013, 2, 14), 108.66, 111.50, 107.27, 107.62)
        checkCandle(new LocalDate(2013, 2, 15), 107.82, 107.98, 105.05, 105.08)
        checkCandle(new LocalDate(2013, 3, 7), 105.35, 105.96, 104.91, 105.31)
        checkCandle(new LocalDate(2013, 3, 11), 106.13, 107.48, 106.12, 106.78)
        checkCandle(new LocalDate(2013, 3, 12), 106.88, 107.08, 105.61, 106.07)
        checkCandle(new LocalDate(2013, 4, 5), 100.02, 100.70, 98.36, 99.26)
        checkCandle(new LocalDate(2013, 4, 8), 99.77, 100.79, 99.66, 100.47)
        checkCandle(new LocalDate(2013, 4, 9), 101.15, 102.69, 100.65, 102.50)
        checkCandle(new LocalDate(2013, 4, 11), 103.62, 103.74, 101.80, 102.40)
        checkCandle(new LocalDate(2013, 4, 12), 99.97, 100.28, 98.16, 98.36)
        checkCandle(new LocalDate(2013, 4, 15), 97.37, 97.83, 95.05, 95.62)
        checkCandle(new LocalDate(2013, 5, 6), 103.35, 103.38, 102.01, 102.49)
        checkCandle(new LocalDate(2013, 5, 7), 102.20, 105.40, 101.62, 105.23)
        checkCandle(new LocalDate(2013, 5, 8), 105.08, 105.90, 104.25, 105.76)
        checkCandle(new LocalDate(2013, 5, 22), 108.85, 111.44, 108.85, 110.74)
        checkCandle(new LocalDate(2013, 5, 23), 108.84, 108.95, 105.53, 105.55)
        checkCandle(new LocalDate(2013, 5, 24), 106.00, 106.49, 102.38, 103.08)
        checkCandle(new LocalDate(2013, 5, 23), 108.84, 108.95, 105.53, 105.55)
        checkCandle(new LocalDate(2013, 5, 24), 106.00, 106.49, 102.38, 103.08)
        checkCandle(new LocalDate(2013, 5, 27), 103.44, 103.79, 102.04, 102.87)
    }
}
