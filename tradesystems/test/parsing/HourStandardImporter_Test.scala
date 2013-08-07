package parsing

import org.scalatest.FunSuite
import logic.TestUtils
import tradingsystems.Candle
import org.joda.time.LocalDateTime
import tradinganalyzers.statistics.HourStandardImporter

/**
 * @author alespuh
 * @since 06.08.13
 */
class HourStandardImporter_Test extends FunSuite with TestUtils
 {
     test("check first/last candle")
     {
         assert(Candle(new LocalDateTime(2010, 1, 11, 10, 0, 0), 86.56, 87.40, 85.51, 87.12, 25222076) === HourStandardImporter.sber.data.head)
         assert(Candle(new LocalDateTime(2013, 6, 11, 18, 0, 0), 94.16, 94.98, 94.02, 94.66, 10875850) === HourStandardImporter.sber.data.last)
     }
 }
