package tradinganalyzers.statistics

import tradinganalyzers.{TradingPositionAnalyzer, TradingPosition, TradingOp}
import tradingsystems._

trait AnalyticalStatisticsPrinter
{
    val ticker: String
    lazy val data: TradingData = DayStandardImporter.standardImport(ticker)
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