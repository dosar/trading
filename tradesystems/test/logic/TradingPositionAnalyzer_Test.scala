package logic

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import tradinganalyzers.{TradingOp, TradingPosition, TradingPositionAnalyzer}
import tradingsystems._
import org.joda.time.LocalDate
import tradingideas.LongTrendCandles
import tradingsystems.Balance
import tradingsystems.MonthProfit
import tradingsystems.YearProfit
import tradinganalyzers.statistics.AnalyticalStatisticsPrinter

@RunWith(classOf[JUnitRunner])
class TradingPositionAnalyzer_Test extends FunSuite with TestUtils with AnalyticalStatisticsPrinter
{
    val ticker: String = null
    override lazy val data = new TradingData(AnalyticalStatisticsPrinter.standardImportSber
        .data.filter(_.date.getYear == 2013))
    val sell = TradingOp.sell(1, 5)
    val tradingPositions = new LongTrendCandles(3, 3, _.buyProfit > 0).filterInterestingDays(data)
    val profits = new TradingPositionAnalyzer((tradingPositions, sell)).positionDatesProfit
    val statistics = new TradingPositionAnalyzer((tradingPositions, sell)).getStatistics
    val yearProfit = statistics(0)
    val monthProfits = yearProfit.monthProfits
    val profitsIterator = profits.iterator

    def testPositionProfit(startDate: (Year, Month, Day), endDate: (Year, Month, Day), profit: (Start, Change))
    {
        val start = new LocalDate(startDate._1, startDate._2, startDate._3)
        val end = new LocalDate(endDate._1, endDate._2, endDate._3)
        val actualProfit = profitsIterator.next()
        assert(start === actualProfit._1)
        assert(end === actualProfit._2)
        assert(profit._1 === actualProfit._3.start)
        assert(profit._2.nonStrict === actualProfit._3.change)
    }

    def testMonth(monthNumber: Int, profits: Profits, positiveStartDatePositions: Dates, negativeStartDatePositions: Dates)
    {
        val profitIndex = monthNumber - 1
        assert(monthNumber === monthProfits(profitIndex).month)
        testBalance(monthProfits(profitIndex).balance, profits, positiveStartDatePositions, negativeStartDatePositions)
    }

    def testBalance(balance: Balance, profits: Profits, positiveStartDatePositions: Dates, negativeStartDatePositions: Dates)
    {
        for((expectedProfit, actualProfit) <- profits.map(p => Profit(p._1, p._2)).zipAll(balance.profit.profits, null, null))
        {
            assert(expectedProfit.start === actualProfit.start)
            assert(expectedProfit.change.nonStrict === actualProfit.change)
        }
        assert(positiveStartDatePositions.map(d => new LocalDate(d._1, d._2, d._3)) === balance.positiveStartDatePositions)
        assert(negativeStartDatePositions.map(d => new LocalDate(d._1, d._2, d._3)) === balance.negativeStartDatePositions)
    }

    test("1 day"){ testPositionProfit((2013, 1, 15), (2013, 1, 17), (100.7, -0.45)) }
    test("2 day"){ testPositionProfit((2013, 1, 21), (2013, 1, 23), (103.55, 0.10)) }
    test("3 day"){ testPositionProfit((2013, 2, 13), (2013, 2, 14), (109.17, -1.0917)) }
    test("4 day"){ testPositionProfit((2013, 3, 7), (2013, 3, 11), (105.35, -1.0535)) }
    test("5 day"){ testPositionProfit((2013, 4, 5), (2013, 4, 9), (100.02, -1.0002)) }
    test("6 day"){ testPositionProfit((2013, 4, 11), (2013, 4, 12), (103.62, 5.181)) }
    test("7 day"){ testPositionProfit((2013, 5, 6), (2013, 5, 7), (103.35, -1.0335)) }
    test("8 day"){ testPositionProfit((2013, 5, 22), (2013, 5, 22), (108.85, -1.08)) }
    test("9 day"){ testPositionProfit((2013, 5, 23), (2013, 5, 24), (108.84, 5.442)) }

    test("1th month"){testMonth(1, Vector((100.7, -0.45), (103.55, 0.10)), Vector((2013, 1, 21)), Vector((2013, 1, 15)))}
    test("2th month"){testMonth(2, Vector((109.17, -1.0917)), Vector(), Vector((2013, 2, 13)))}
    test("3th month"){testMonth(3, Vector((105.35, -1.0535)), Vector(), Vector((2013, 3, 07)))}
    test("4th month"){testMonth(4, Vector((100.02, -1.0002), (103.62, 5.181)), Vector((2013, 4, 11)), Vector((2013, 4, 5)))}
    test("5th month"){testMonth(5, Vector((103.35, -1.0335), (108.85, -1.08), (108.84, 5.442)), Vector((2013, 5, 23)), Vector((2013, 5, 6), (2013, 5, 22)))}

    test("test profit statistics year")
    {
        assert(2013 === yearProfit.year)
        testBalance(
            yearProfit.balance,
            Vector((100.7, -0.45), (103.55, 0.10), (109.17, -1.0917), (105.35, -1.0535), (100.02, -1.0002),
                (103.62, 5.181), (103.35, -1.0335), (108.85, -1.08), (108.84, 5.442)),
            Vector((2013, 1, 21), (2013, 4, 11), (2013, 5, 23)),
            Vector((2013, 1, 15), (2013, 2, 13), (2013, 3, 7), (2013, 4, 5), (2013, 5, 6), (2013, 5, 22))
        )
    }
}
