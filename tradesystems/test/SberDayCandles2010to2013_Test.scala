import org.joda.time.LocalDate
import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import scala.io.Source
import tradingsystems._
import tradingsystems.Candle
import tradingsystems.TradingIdea._
import tradingsystems.YearProfit

/**
 * @author alespuh
 * @since 12.06.13
 */
@RunWith(classOf[JUnitRunner])
class SberDayCandles2010to2013_Test extends FunSuite with AnalyticalStatisticsPrinter
{
    import TradingIdea._
    import TradingAnalytics._

    lazy val data =
    {
        (for(line <- Source.fromFile("g:\\work\\octave_assignments\\oracul\\sber.txt").getLines(); cells = line.split(',');
            date = cells(0); open = cells(1); high = cells(2); low = cells(3); close = cells(4))
        yield
        {
            Candle(date = new LocalDate(date.take(4).toInt, date.take(6).drop(4).toInt, date.take(8).drop(6).toInt),
                open.toDouble, high.toDouble, low.toDouble, close.toDouble)
        }).toList
    }

    test("2 day plus"){ analyzeIdea(zipWithProfit(tooPositive2Days(data), _.sellProfit)) }
    test("2 day minus"){ analyzeIdea(zipWithProfit(tooNegative2Days(data), _.buyProfit)) }

    test("3 day plus"){ analyzeIdea(zipWithProfit(tooPositive3Days(data), _.sellProfit)) }
    test("3 day minus"){ analyzeIdea(zipWithProfit(tooNegative3Days(data), _.buyProfit)) }

    test("4 day plus"){ analyzeIdea(zipWithProfit(tooPositive4Days(data), _.sellProfit)) }
    test("4 day minus"){ analyzeIdea(zipWithProfit(tooNegative4Days(data), _.buyProfit)) }

    test("2 day plus/2 day minus without stop")
    {
        //2010 = 7,59, 2011 = 38,23, 2012 = 23,87, 2013 = 9,75
        val tooPositive = zipWithProfit(tooPositive2Days(data), _.sellProfit)
        val tooNegative = zipWithProfit(tooNegative2Days(data), _.buyProfit)
        analyzeIdea(tooPositive ++ tooNegative)
    }

    test("n day plus/n day minus with different stops")
    {
        val expectedStrings = """days:2 stop:1.0 410/852 2010 = 1,59, 2011 = 29,37, 2012 = 29,25, 2013 = 11,37
                                |days:2 stop:0.5 410/852 2010 = -0,66, 2011 = 25,68, 2012 = 14,67, 2013 = 4,95
                                |days:2 stop:0.25 410/852 2010 = -6,39, 2011 = 7,46, 2012 = 10,30, 2013 = 1,75
                                |days:3 stop:1.0 179/852 2010 = -1,86, 2011 = 11,83, 2012 = 18,75, 2013 = 14,40
                                |days:3 stop:0.5 179/852 2010 = -4,23, 2011 = 10,88, 2012 = 3,54, 2013 = 13,02
                                |days:3 stop:0.25 179/852 2010 = -9,68, 2011 = 5,88, 2012 = 0,14, 2013 = 6,24
                                |days:4 stop:1.0 84/852 2010 = -0,50, 2011 = 17,82, 2012 = 16,88, 2013 = 2,64
                                |days:4 stop:0.5 84/852 2010 = -3,56, 2011 = 14,71, 2012 = 9,88, 2013 = 3,64
                                |days:4 stop:0.25 84/852 2010 = -8,00, 2011 = 7,21, 2012 = 6,33, 2013 = 4,14""".stripMargin.split("\r\n")
        val input = for(trendyDay <- 2 to 4; stop <- List(1, 0.5, 0.25)) yield (trendyDay, stop)

        for(((trendyDay, stop), expectedString) <- input.zip(expectedStrings))
        {
            val tooPositive = zipWithProfit(tooTrendyCandles(data, _.buyProfit > 0, trendyDay), _.sellProfit, _.sellSlump, stop)
            val tooNegative = zipWithProfit(tooTrendyCandles(data, _.sellProfit > 0, trendyDay), _.buyProfit, _.buySlump, stop)
            assert(expectedString === getStringStatistics(tooPositive ++ tooNegative, "days:" + trendyDay + " stop:" + stop))
        }
    }

    test("n day plus/n day minus analytics")
    {
        val input = for(trendyDay <- 2 to 4; stop <- List(1, 0.5, 0.25)) yield (trendyDay, stop)
        for((trendyDay, stop) <- input)
        {
            val tooPositive = zipWithProfit(tooTrendyCandles(data, _.buyProfit > 0, trendyDay), _.sellProfit, _.sellSlump, stop)
            val tooNegative = zipWithProfit(tooTrendyCandles(data, _.sellProfit > 0, trendyDay), _.buyProfit, _.buySlump, stop)
            analyzeIdea(tooPositive ++ tooNegative)
        }
    }
}
