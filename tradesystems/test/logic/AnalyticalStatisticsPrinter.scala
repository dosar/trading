package logic

import org.joda.time.LocalDate
import scala.io.Source
import tradinganalyzers.{TradingPositionAnalyzer, TradingPosition, TradingDaysAnalyzer, TradingOp}
import tradingideas.TradingIdea
import tradingsystems._
import TradingIdea._

trait AnalyticalStatisticsPrinter
{
    def analyzeIdea(candleOps: (Vector[TradingPosition], TradingOp)*) =
    {
        println("idea days count " + candleOps.map(_._1.size).sum)
        val positionOps = candleOps.flatMap{case (positions, op) => positions.map((_, op))}.toVector
        println(new TradingPositionAnalyzer(positionOps).getStatistics.map
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

    def getStringStatistics(prefix: String, candleOps: (Vector[TradingPosition], TradingOp)*) =
    {
        val positionOps = candleOps.flatMap{case (positions, op) => positions.map((_, op))}.toVector
        val yearProfits = new TradingPositionAnalyzer(positionOps).getStatistics
        if(isUsefulOutput(yearProfits))
        {
            val yearProfitsDescription: String = yearProfits.map(_.yearProfitString).mkString("|")
            val monthAvgs = yearProfits.map(yp => yp.avgSlumpString + "|" +  yp.monthSlumpsString).mkString("|")
//            prefix + " " + candleOps.map(_._1.size).sum.formatted("%03d") + "/" + data.size + " " + yearProfitsDescription + " slumps: " + monthAvgs
            prefix + " | " + yearProfitsDescription + " | " + monthAvgs
        }
        else null
    }

    def isUsefulOutput(yearProfits: Vector[YearProfit]): Boolean =
        yearProfits.exists(yp => yp.year == 2013 && (yp.yearProfit / yp.avgPrice) > 0.19)

    def standardImport(importFile: String) =
    {
        (for(line <- Source.fromFile(importFile).getLines(); cells = line.split(',');
            date = cells(0); open = cells(2); high = cells(3); low = cells(4); close = cells(5))
        yield
        {
            Candle(date = new LocalDate(date.take(4).toInt, date.take(6).drop(4).toInt, date.take(8).drop(6).toInt),
                open.toDouble, high.toDouble, low.toDouble, close.toDouble)
        }).toVector
    }
}