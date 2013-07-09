package logic

import org.joda.time.LocalDate
import scala.io.Source
import tradinganalyzers.{TradingPositionAnalyzer, TradingPosition, TradingOp}
import tradingsystems._

trait AnalyticalStatisticsPrinter
{
    val ticker: String
    lazy val data: TradingData = standardImport("g:\\work\\trademachine\\" + ticker + "_2010_2013_1day.txt")
    val targetProfit: Double = 19.0

    def analyzeIdea(candleOps: (Vector[TradingPosition], TradingOp)*) =
    {
        println("idea days count " + candleOps.map(_._1.size).sum)
        println(new TradingPositionAnalyzer(data, candleOps:_*).getStatistics.map
        {
            case yp: YearProfit =>
                val plusProfits = yp.monthProfits.filter(_.profitPct > 0).map(_.month)
                val minusProfits = yp.monthProfits.filter(_.profitPct < 0).map(_.month)
                val highSlumps = yp.monthProfits.filter(_.slumpPct < -2).map(_.month)
                yp.toString + "\n" + "profit: " + plusProfits.size + "/" + minusProfits.size +
                    " >0: " + plusProfits.mkString(", ") + " <0: " + minusProfits.mkString(", ") + "\n" +
                    "slump2: " + highSlumps.size + "/12 " + highSlumps.mkString(", ") + "\n"
            case x => x
        }.mkString("\n", "\n", "\n"))
    }

    def getStringStatistics(prefix: String, candleOps: (Vector[TradingPosition], TradingOp)*): String =
        getStringStatistics(prefix, new TradingPositionAnalyzer(data, candleOps:_*).getStatistics)

    def getStringStatistics(prefix: String, yearProfits: Vector[YearProfit]): String =
    {
        if(isUsefulOutput(yearProfits))
        {
            val yearProfitsDescription: String = yearProfits.map(_.yearProfitString).mkString("|")
//            val monthAvgs = yearProfits.map(yp => yp.worstMonthSlumpString +
//                yp.balance.negativeStartDatePositions.mkString("|", "|", "|") + yp.monthSlumpsString).mkString("|")
            val monthAvgs = yearProfits.map(_.monthSlumpsString).mkString("|")
            prefix + " | " + yearProfitsDescription + " | " + monthAvgs
        }
        else null
    }

    def isUsefulOutput(yearProfits: Vector[YearProfit]): Boolean =
        yearProfits.length == 4 && yearProfits(3).strictProfit(targetProfit) && yearProfits(2).positiveProfit &&
            yearProfits(1).positiveProfit && yearProfits(0).positiveProfit

    def standardImport(importFile: String) =
    {
        val data = (for(line <- Source.fromFile(importFile).getLines(); cells = line.split(',');
            date = cells(0); open = cells(2); high = cells(3); low = cells(4); close = cells(5))
        yield
        {
            Candle(date = new LocalDate(date.take(4).toInt, date.take(6).drop(4).toInt, date.take(8).drop(6).toInt),
                open.toDouble, high.toDouble, low.toDouble, close.toDouble)
        }).toVector
        TradingData(data)
    }
}