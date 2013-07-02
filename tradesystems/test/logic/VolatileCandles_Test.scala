package logic

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import tradingideas.VolatileCandles
import tradingsystems.Candle
import org.joda.time.LocalDate
import tradinganalyzers.TradingPosition

@RunWith(classOf[JUnitRunner])
class VolatileCandles_Test extends FunSuite with TestUtils with AnalyticalStatisticsPrinter
{
//    нужно чтобы было минимум 2 торговых дня
//    также нужно чтобы было вытряхивание по стопу тому или другому

    lazy val data = standardImport("g:\\work\\trademachine\\SBER_2010_2013_1day.txt")
        .filter(_.date.getYear == 2013)

    test("filter trading days for 3 days rising SBER")
    {
        def position(candles: Candle*) = new TradingPosition(candles:_*)
        {
            override def equals(obj: Any): Boolean =
            {
                obj match
                {
                    case TradingPosition(anotherCandles) => candles.toList == anotherCandles.toList
                    case x => false
                }
            }
        }

        val tradingPositions = new VolatileCandles(3, 3, _.buyProfit > 0).filterInterestingDays(data)
        assert(position(Candle(new LocalDate(2013, 1, 15), 100.7, 101.00, 99.78, 99.85),
            Candle(new LocalDate(2013, 1, 16), 99.93, 100.13, 99.19, 99.97),
            Candle(new LocalDate(2013, 1, 17), 99.90, 101.33, 99.54, 101.15)) === tradingPositions(0))
        assert(position(Candle(new LocalDate(2013, 1, 21), 103.55, 103.95, 102.78, 103.64),
            Candle(new LocalDate(2013, 1, 22), 103.70, 103.85, 102.07, 102.35),
            Candle(new LocalDate(2013, 1, 23), 102.73, 103.65, 102.18, 103.45)) === tradingPositions(1))
        assert(position(Candle(new LocalDate(2013, 1, 22), 103.70, 103.85, 102.07, 102.35),
            Candle(new LocalDate(2013, 1, 23), 102.73, 103.65, 102.18, 103.45),
            Candle(new LocalDate(2013, 1, 24), 103.06, 103.07, 101.92, 102.48)) === tradingPositions(2))
        assert(position(Candle(new LocalDate(2013, 2, 13), 109.17, 109.34, 107.80, 108.80),
            Candle(new LocalDate(2013, 2, 14), 108.66, 111.50, 107.27, 107.62),
            Candle(new LocalDate(2013, 2, 15), 107.82, 107.98, 105.05, 105.08)) === tradingPositions(3))
        assert(position(Candle(new LocalDate(2013, 3, 7), 105.35, 105.96, 104.91, 105.31),
            Candle(new LocalDate(2013, 3, 11), 106.13, 107.48, 106.12, 106.78),
            Candle(new LocalDate(2013, 3, 12), 106.88, 107.08, 105.61, 106.07)) === tradingPositions(4))
        assert(position(Candle(new LocalDate(2013, 4, 5), 100.02, 100.70, 98.36, 99.26),
            Candle(new LocalDate(2013, 4, 8), 99.77, 100.79, 99.66, 100.47),
            Candle(new LocalDate(2013, 4, 9), 101.15, 102.69, 100.65, 102.50)) === tradingPositions(5))
        assert(position(Candle(new LocalDate(2013, 4, 11), 103.62, 103.74, 101.80, 102.40),
            Candle(new LocalDate(2013, 4, 12), 99.97, 100.28, 98.16, 98.36),
            Candle(new LocalDate(2013, 4, 15), 97.37, 97.83, 95.05, 95.62)) === tradingPositions(6))
        assert(position(Candle(new LocalDate(2013, 5, 6), 103.35, 103.38, 102.01, 102.49),
            Candle(new LocalDate(2013, 5, 7), 102.20, 105.40, 101.62, 105.23),
            Candle(new LocalDate(2013, 5, 8), 105.08, 105.90, 104.25, 105.76)) === tradingPositions(7))
        assert(position(Candle(new LocalDate(2013, 5, 22), 108.85, 111.44, 108.85, 110.74),
            Candle(new LocalDate(2013, 5, 23), 108.84, 108.95, 105.53, 105.55),
            Candle(new LocalDate(2013, 5, 24), 106.00, 106.49, 102.38, 103.08)) === tradingPositions(8))
        assert(position(Candle(new LocalDate(2013, 5, 23), 108.84, 108.95, 105.53, 105.55),
            Candle(new LocalDate(2013, 5, 24), 106.00, 106.49, 102.38, 103.08),
            Candle(new LocalDate(2013, 5, 27), 103.44, 103.79, 102.04, 102.87)) === tradingPositions(9))
    }
}
