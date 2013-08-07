package parsing

import org.scalatest.FunSuite
import logic.TestUtils
import tradingsystems.Candle
import org.joda.time.LocalDateTime
import tradinganalyzers.statistics.DayStandardImporter

/**
 * @author alespuh
 * @since 06.08.13
 */
class DayStandardImporter_Test extends FunSuite with TestUtils
{
    test("check first/last candle")
    {
        assert(Candle(new LocalDateTime(2010, 1, 11, 0, 0, 0), 86.56, 88.17, 85.51, 86.69, 148661237) === DayStandardImporter.sber.data.head)
        assert(Candle(new LocalDateTime(2013, 6, 14, 0, 0, 0), 92.87, 94.09, 91.42, 93.70, 88187820) === DayStandardImporter.sber.data.last)
    }
}