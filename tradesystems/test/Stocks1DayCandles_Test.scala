import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import tradingideas.TradingIdea
import tradingsystems.Candle
import TradingIdea._

/**
 * @author alespuh
 * @since 15.06.13
 */
@RunWith(classOf[JUnitRunner])
class Stocks1DayCandles_Test extends FunSuite
{
    class SimpleTest(tiker: String) extends AnalyticalStatisticsPrinter
    {
        val data: List[Candle] = standardImport("g:\\work\\trademachine\\" + tiker + "_2010_2013_1day.txt")

        override def standardTest(initialStopPercent: Double, initialTakeProfitPercent: Double)
        {
            println(tiker)
            super.standardTest(initialStopPercent, initialTakeProfitPercent)
        }

        override def detailedTest(stop: Double, trendyDays: Int, takeProfit: Double)
        {
            println(tiker)
            super.detailedTest(stop, trendyDays, takeProfit)
        }
    }

    test("sberbank 1 day candles percent stop, take profit") { new SimpleTest("SBER").standardTest(5, 1) }
    test("gazprom 1 day candles percent stop, take profit") { new SimpleTest("GAZP").standardTest(5, 1) }
    test("nornikel 1 day candles percent stop, take profit") { new SimpleTest("GMKN").standardTest(5, 1) }
    test("lukoil 1 day candles percent stop, take profit") { new SimpleTest("LKOH").standardTest(5, 1) }
    test("novatek 1 day candles percent stop, take profit") { new SimpleTest("NVTK").standardTest(5, 1) }
    test("rosneft 1 day candles percent stop, take profit") { new SimpleTest("ROSN").standardTest(5, 1) }
    test("rostelecom 1 day candles percent stop, take profit") { new SimpleTest("RTKM").standardTest(5, 1) }

    test("best settings")
    {
        new SimpleTest("SBER").detailedTest(0.25, 2, 2) // the best
        new SimpleTest("GAZP").detailedTest(0.25, 2, 4)
    }

    test("different tool intersections")
    {
        val sberDays = new SimpleTest("SBER")
        {
            val tradingDays = tooTrendyCandles(data, _.buyProfit > 0, 3) ++ tooTrendyCandles(data, _.sellProfit > 0, 3)
        }.tradingDays.map(_.date)//18.33 at jump/stop 5/4
        val gmknDays = new SimpleTest("GMKN")
        {
            val tradingDays = tooTrendyCandles(data, _.buyProfit > 0, 2) ++ tooTrendyCandles(data, _.sellProfit > 0, 2)
        }.tradingDays.map(_.date)//1007.64 at jump/stop 4/4
        for(year <- 2010 to 2013; yearSberDays = sberDays.filter(_.getYear == year); yearGmknDays = gmknDays.filter(_.getYear == year))
        {
            val intersection = yearSberDays.filter(d => yearGmknDays.contains(d)).size
            println("gmknDays:" + (yearGmknDays.size - intersection) + " intersection:" + intersection + " sberDays:" + (yearSberDays.size - intersection))
        }
    }

    test("detailed test")
    {
        new SimpleTest("SBER").detailedTest(5, 3, 4)
        new SimpleTest("GMKN").detailedTest(5, 2, 4)
    }
}