import org.joda.time.LocalDate
import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import scala.io.Source
import tradingsystems.Candle
import tradingsystems.Candle
import tradingsystems.Candle
import tradingsystems.TradingAnalytics._
import tradingsystems.TradingIdea._

/**
 * @author alespuh
 * @since 12.06.13
 */
@RunWith(classOf[JUnitRunner])
class SberHourCandles2010to2013_Test extends FunSuite with AnalyticalStatisticsPrinter
{
    lazy val data =
    {
        (for(line <- Source.fromFile("g:\\work\\trademachine\\SBER_2010_2013_hour.txt").getLines(); cells = line.split(',');
            date = cells(0); open = cells(2); high = cells(3); low = cells(4); close = cells(5))
        yield
        {
            Candle(date = new LocalDate(date.take(4).toInt, date.take(6).drop(4).toInt, date.take(8).drop(6).toInt),
                open.toDouble, high.toDouble, low.toDouble, close.toDouble)
        }).toList
    }

    test("n day plus/n day minus with different stops")
    {
        val expectedStrings = """days:2 stop:1.0 3718/7731 2010 = -30,81, 2011 = -11,65, 2012 = -19,56, 2013 = -11,68
                                |days:2 stop:0.5 3718/7731 2010 = -25,57, 2011 = -16,93, 2012 = -9,46, 2013 = -10,88
                                |days:2 stop:0.25 3718/7731 2010 = -20,88, 2011 = 2,43, 2012 = -0,97, 2013 = 0,25
                                |days:3 stop:1.0 1816/7731 2010 = -13,23, 2011 = -8,35, 2012 = -0,50, 2013 = -13,54
                                |days:3 stop:0.5 1816/7731 2010 = -7,33, 2011 = -10,79, 2012 = 1,71, 2013 = -14,12
                                |days:3 stop:0.25 1816/7731 2010 = -14,45, 2011 = -8,52, 2012 = 1,14, 2013 = -4,71
                                |days:4 stop:1.0 879/7731 2010 = -10,85, 2011 = -11,80, 2012 = -9,49, 2013 = -10,20
                                |days:4 stop:0.5 879/7731 2010 = -11,00, 2011 = -12,54, 2012 = -3,90, 2013 = -9,71
                                |days:4 stop:0.25 879/7731 2010 = -11,48, 2011 = -6,19, 2012 = -2,29, 2013 = -5,10
                                |days:5 stop:1.0 446/7731 2010 = -10,05, 2011 = -3,81, 2012 = -5,97, 2013 = -2,59
                                |days:5 stop:0.5 446/7731 2010 = -9,63, 2011 = -7,08, 2012 = -2,35, 2013 = -4,02
                                |days:5 stop:0.25 446/7731 2010 = -12,12, 2011 = -1,31, 2012 = -1,86, 2013 = -2,36
                                |days:6 stop:1.0 232/7731 2010 = -7,24, 2011 = -3,42, 2012 = -0,89, 2013 = -2,08
                                |days:6 stop:0.5 232/7731 2010 = -6,98, 2011 = -3,46, 2012 = 1,44, 2013 = -1,93
                                |days:6 stop:0.25 232/7731 2010 = -5,86, 2011 = 1,31, 2012 = 2,67, 2013 = -1,00
                                |days:7 stop:1.0 122/7731 2010 = -4,78, 2011 = 2,11, 2012 = 1,37, 2013 = -0,60
                                |days:7 stop:0.5 122/7731 2010 = -5,25, 2011 = -0,15, 2012 = 1,94, 2013 = -0,17
                                |days:7 stop:0.25 122/7731 2010 = -4,24, 2011 = 3,62, 2012 = 1,40, 2013 = 0,05
                                |days:8 stop:1.0 60/7731 2010 = 1,45, 2011 = -1,85, 2012 = 2,35, 2013 = -0,63
                                |days:8 stop:0.5 60/7731 2010 = -1,17, 2011 = -0,37, 2012 = 2,35, 2013 = -1,01
                                |days:8 stop:0.25 60/7731 2010 = -1,70, 2011 = 1,57, 2012 = 1,34, 2013 = -0,30
                                |days:9 stop:1.0 27/7731 2010 = 0,53, 2011 = -0,67, 2012 = 1,50, 2013 = 1,49
                                |days:9 stop:0.5 27/7731 2010 = -0,37, 2011 = 0,04, 2012 = 1,50, 2013 = 1,49
                                |days:9 stop:0.25 27/7731 2010 = -0,09, 2011 = 0,48, 2012 = 1,07, 2013 = 0,95
                                |days:10 stop:1.0 8/7731 2010 = -0,10, 2011 = 0,74, 2013 = 0,92
                                |days:10 stop:0.5 8/7731 2010 = -1,00, 2011 = 0,74, 2013 = 0,92
                                |days:10 stop:0.25 8/7731 2010 = -0,50, 2011 = 0,43, 2013 = 0,38
                                |days:11 stop:1.0 2/7731 2010 = 0,17, 2013 = 0,88
                                |days:11 stop:0.5 2/7731 2010 = -0,50, 2013 = 0,88
                                |days:11 stop:0.25 2/7731 2010 = -0,25, 2013 = 0,88
                                |days:12 stop:1.0 0/7731
                                |days:12 stop:0.5 0/7731
                                |days:12 stop:0.25 0/7731""".stripMargin.split("\r\n")

        val input = for(trendyDay <- 2 to 12; stop <- List(1, 0.5, 0.25)) yield (trendyDay, stop)
        for(((trendyDay, stop), expectedString) <- input.zip(expectedStrings))
        {
            val tooPositive = zipWithProfit(tooTrendyCandles(data, _.buyProfit > 0, trendyDay), _.sellProfit, _.sellSlump, stop)
            val tooNegative = zipWithProfit(tooTrendyCandles(data, _.sellProfit > 0, trendyDay), _.buyProfit, _.buySlump, stop)
            assert(expectedString === getStringStatistics(tooPositive ++ tooNegative, "days:" + trendyDay + " stop:" + stop))
        }

//        for((trendyDay, stop) <- input)
//        {
//            val tooPositive = zipWithProfit(tooTrendyCandles(data, _.buyProfit > 0, trendyDay), _.sellProfit, _.sellSlump, stop)
//            val tooNegative = zipWithProfit(tooTrendyCandles(data, _.sellProfit > 0, trendyDay), _.buyProfit, _.buySlump, stop)
//            fastAnalyzeIdea(tooPositive ++ tooNegative, "days:" + trendyDay + " stop:" + stop)
//        }
    }
}