package tradinganalyzers.statistics

import tradingsystems.{Candle, TradingData}
import org.joda.time.LocalDateTime
import scala.io.Source

/**
 * @author alespuh
 * @since 06.08.13
 */
object DayStandardImporter extends DayStandardImporter("_2010_2013_1day.txt")
object HourStandardImporter extends DayStandardImporter("_2010_2013_1hour.txt")

class DayStandardImporter(postfix: String)
{
    lazy val sber = standardImport("SBER")
    lazy val gazp = standardImport("GAZP")
    lazy val nvtk = standardImport("NVTK")
    lazy val gmkn = standardImport("GMKN")
    lazy val rosn = standardImport("ROSN")
    lazy val rtkm = standardImport("RTKM")
    lazy val lkoh = standardImport("LKOH")

    def standardImport(ticker: String): TradingData =
    {
        val importFile = ".\\" + ticker + postfix
        val data = (for(line <- Source.fromFile(importFile).getLines(); cells = line.split(',');
            date = cells(0); time = cells(1);
            open = cells(2); high = cells(3); low = cells(4); close = cells(5); volume = cells(6))
        yield
        {
            val year = date.substring(0, 4).toInt
            val month = date.substring(4, 6).toInt
            val day = date.substring(6, 8).toInt
            val hour = time.substring(0, 2).toInt
            val minutes = time.substring(2, 4).toInt
            val seconds = time.substring(4, 6).toInt
            Candle(date = new LocalDateTime(year, month, day, hour, minutes, seconds),
                open.toDouble, high.toDouble, low.toDouble, close.toDouble, volume.toInt)
        }).toVector
        TradingData(data, ticker)
    }
}