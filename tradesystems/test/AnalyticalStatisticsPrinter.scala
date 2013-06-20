import org.joda.time.LocalDate
import scala.io.Source
import tradinganalyzers.{TradingDaysAnalyzer, TradingOp}
import tradingideas.TradingIdea
import tradingsystems._
import TradingIdea._

trait AnalyticalStatisticsPrinter
{
    val data: List[Candle]

    def analyzeIdea(candleOps: (List[Candle], TradingOp)*) =
    {
        println("all days count " + data.size)
        println("idea days count " + candleOps.map(_._1.size).sum)
        println(TradingDaysAnalyzer(candleOps:_*).getStatistics.map
        {
            case yp: YearProfit =>
                val plusProfits = yp.monthProfits.filter(_.profit > 0).map(_.month)
                val minusProfits = yp.monthProfits.filter(_.profit < 0).map(_.month)
                val highSlumps = yp.monthProfits.filter(_.slump < -2).map(_.month)
                yp.toString + "\n" + "profit: " + plusProfits.size + "/" + minusProfits.size +
                    " >0: " + plusProfits.mkString(", ") + " <0: " + minusProfits.mkString(", ") + "\n" +
                    "slump2: " + highSlumps.size + "/12 " + highSlumps.mkString(", ") + "\n"
            case x => x
        }.mkString("\n", "\n", "\n"))
    }

    def getStringStatistics(prefix: String, candleOps: (List[Candle], TradingOp)*) =
    {
        val yearProfits = TradingDaysAnalyzer(candleOps:_*).getStatistics
        if(yearProfits.exists(yp => yp.year == 2013 && yp.yearProfit / yp.avgPrice > 0.19))
        {
            val yearProfitsDescription: String = yearProfits.map(_.yearProfitString).mkString(", ")
            val monthAvgs = yearProfits.map(yp => yp.avgSlumpString + " " +  yp.monthSlumpsString).mkString(", ")
            prefix + " " + candleOps.map(_._1.size).sum.formatted("%03d") + "/" + data.size + " " + yearProfitsDescription + " slumps: " + monthAvgs
        }
        else null
    }

    def standardImport(importFile: String) =
    {
        (for(line <- Source.fromFile(importFile).getLines(); cells = line.split(',');
            date = cells(0); open = cells(2); high = cells(3); low = cells(4); close = cells(5))
        yield
        {
            Candle(date = new LocalDate(date.take(4).toInt, date.take(6).drop(4).toInt, date.take(8).drop(6).toInt),
                open.toDouble, high.toDouble, low.toDouble, close.toDouble)
        }).toList
    }
}