package tradinganalyzers.statistics

import org.joda.time.LocalDate
import scala.io.Source
import tradinganalyzers.{TradingPositionAnalyzer, TradingPosition, TradingOp}
import tradingsystems._

object StandardImporter
{
    def standardImport(ticker: String): TradingData =
    {
        val importFile = ".\\" + ticker + "_2010_2013_1day.txt"
        val data = (for(line <- Source.fromFile(importFile).getLines(); cells = line.split(',');
            date = cells(0); open = cells(2); high = cells(3); low = cells(4); close = cells(5); volume = cells(6))
        yield
        {
            Candle(date = new LocalDate(date.take(4).toInt, date.take(6).drop(4).toInt, date.take(8).drop(6).toInt),
                open.toDouble, high.toDouble, low.toDouble, close.toDouble, volume.toInt)
        }).toVector
        TradingData(data, ticker)
    }

    def importSber = standardImport("SBER")
    def importGazp = standardImport("GAZP")
    def importNvtk = standardImport("NVTK")
    def importGmkn = standardImport("GMKN")
    def importRosn = standardImport("ROSN")
    def importRtkm = standardImport("RTKM")
    def importLkoh = standardImport("LKOH")
}

trait AnalyticalStatisticsPrinter
{
    val ticker: String
    lazy val data: TradingData = StandardImporter.standardImport(ticker)
    val targetProfit: Double = 19.0

    def analyzeIdea(candleOps: (Vector[TradingPosition], TradingOp)*) =
    {
        println("idea days count " + candleOps.map(_._1.size).sum)
        println(new TradingPositionAnalyzer(candleOps.toVector).getStatistics.map
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
        getStringStatistics(prefix, new TradingPositionAnalyzer(candleOps.toVector).getStatistics)

    def getStringStatistics(prefix: String, yearProfits: Array[YearProfit]): String =
        new YearProfitStatistics(yearProfits).compactStat(isUsefulOutput(yearProfits.toVector), prefix)

    def isUsefulOutput(yearProfits: Vector[YearProfit]): Boolean = yearProfits.length == 4 &&
            yearProfits(3).strictProfit(targetProfit / 2) &&
            yearProfits(2).strictProfit(targetProfit) &&
            yearProfits(1).strictProfit(targetProfit) &&
            yearProfits(0).strictProfit(targetProfit)
}