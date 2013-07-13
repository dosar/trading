package ideas

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import tradinganalyzers.{TradingPositionAnalyzer, TradingOp}
import tradingideas.TradingIdea._
import tradingideas.LongTrendCandles
import tradingsystems.{YearProfit, Candle}
import org.joda.time.LocalDate
import tradinganalyzers.statistics.VolatileDaysStatisticalPrinter

@RunWith(classOf[JUnitRunner])
class Stocks1DayCandlesVolatileDays_Test extends FunSuite
{
    class SimpleTest(val ticker: String, override val targetProfit: Double = 19) extends VolatileDaysStatisticalPrinter

    test("sberbank 1 day candles percent stop, take profit")
    {
        new SimpleTest("SBER", 30)
        {
            override def isUsefulOutput(yearProfits: Vector[YearProfit]): Boolean = yearProfits.length == 4 &&
                yearProfits.forall(_.strictProfit(25))
            override def positiveCandle(c: Candle): Boolean = c.buyProfitPct > 0.25
            override def negativeCandle(c: Candle): Boolean = c.sellProfitPct > 0.25
        }.standardTest(stopMultiplierStep = 1)
    }
    test("gazprom 1 day candles percent stop, take profit") { new SimpleTest("GAZP", 35).standardTest(stopMultiplierStep = 1) }
    test("nornikel 1 day candles percent stop, take profit") { new SimpleTest("GMKN", 27).standardTest(stopMultiplierStep = 1) }
    test("lukoil 1 day candles percent stop, take profit") { new SimpleTest("LKOH").standardTest(stopMultiplierStep = 1) }
    test("novatek 1 day candles percent stop, take profit") { new SimpleTest("NVTK", 25).standardTest(stopMultiplierStep = 1) }
    test("rosneft 1 day candles percent stop, take profit") { new SimpleTest("ROSN", 25).standardTest(stopMultiplierStep = 1) }
    test("rostelecom 1 day candles percent stop, take profit") { new SimpleTest("RTKM", 35).standardTest(stopMultiplierStep = 1) }

    test("print negative position days")
    {
        new SimpleTest("SBER")
        {
            val op1positions = new LongTrendCandles(2, 5, _.buyProfitPct > 0.15).filterInterestingDays(data)
            val op2Positions = new LongTrendCandles(2, 5, _.sellProfitPct > 0).filterInterestingDays(data)
            val statistics: Vector[YearProfit] = new TradingPositionAnalyzer(data, (op1positions, TradingOp.sell(5, 8)),
                (op2Positions, TradingOp.buy(5, 8))).getStatistics
            println(statistics.flatMap(yp =>
                List(yp.yearProfitPct.formatted("%7.2f"), yp.yearSlumpPct.formatted("%7.2f"), yp.worstMonthSlumpString)).mkString(" | "))
            for(yp <- statistics)
            {
                println(List(yp.year, yp.negativeDeals, yp.positiveDeals).mkString(" | "))
                for(mp <- yp.monthProfits)
                {
                    println((mp.month :: mp.balance.negativeStartDatePositions.toList.map(d => d.getDayOfMonth)).mkString(" | "))
                    println((mp.month :: mp.balance.positiveStartDatePositions.toList.map(d => d.getDayOfMonth)).mkString(" | "))
                }
            }
        }
    }
}
