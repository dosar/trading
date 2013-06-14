import org.joda.time.LocalDate
import scala.io.Source
import tradingsystems._
import tradingsystems.Candle
import tradingsystems.Candle
import tradingsystems.CandleCalculator._
import tradingsystems.TradingAnalytics._
import tradingsystems.TradingIdea._
import tradingsystems.YearProfit

trait AnalyticalStatisticsPrinter
{
    import CandleCalculator._
    import TradingAnalytics._

    val data: List[Candle]

    def analyzeIdea(candles: List[(Candle, Profit)]) =
    {
        println("all days count " + data.size)
        println("idea days count " + candles.size)
        println(analyzeProfit(candles).map
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

    def fastAnalyzeIdea(candles: List[(Candle, Profit)], prefix: String = "") =
        println(getStringStatistics(candles, prefix))

    def getStringStatistics(candles: List[(Candle, Profit)], prefix: String = "") =
    {
        val yearProfits = analyzeProfit(candles).filter
        {
            case yearProfit: YearProfit => true
            case x => false
        }.map(_.asInstanceOf[YearProfit])
        val yearProfitsDescription: String = yearProfits.map(_.yearProfitString).mkString(", ")
        val monthAvgs = yearProfits.map(yp => yp.avgSlumpString + " " +  yp.monthSlumpsString).mkString(", ")
        prefix + " " + candles.size + "/" + data.size + " " + yearProfitsDescription + " slumps: " + monthAvgs
    }

    def standardTest(initialStop: Double = 1)
    {
        val input = for(trendyDay <- 2 to 10; stop <- List(initialStop, initialStop / 2, initialStop / 4)) yield (trendyDay, stop)
        val statistics = for((trendyDay, stop) <- input.par) yield
        {
            val tooPositive = zipWithProfit(tooTrendyCandles(data, _.buyProfit > 0, trendyDay), _.sellProfit, _.sellSlump, stop)
            val tooNegative = zipWithProfit(tooTrendyCandles(data, _.sellProfit > 0, trendyDay), _.buyProfit, _.buySlump, stop)
            getStringStatistics(tooPositive ++ tooNegative, "days:" + trendyDay + " stop:" + stop.formatted("%.2f"))
        }
        statistics.toList.sortBy(_.take(6)).foreach(println)
    }

    def detailedTest(stop: Double = 1, trendyDay: Int = 2)
    {
        val tooPositive = zipWithProfit(tooTrendyCandles(data, _.buyProfit > 0, trendyDay), _.sellProfit, _.sellSlump, stop)
        val tooNegative = zipWithProfit(tooTrendyCandles(data, _.sellProfit > 0, trendyDay), _.buyProfit, _.buySlump, stop)
        analyzeIdea(tooPositive ++ tooNegative)
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
