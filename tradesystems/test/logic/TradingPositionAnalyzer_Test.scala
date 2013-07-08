package logic

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import tradinganalyzers.{TradingOp, TradingPosition, TradingPositionAnalyzer}
import tradingsystems._
import tradingideas.VolatileCandles
import org.joda.time.LocalDate
import tradingideas.VolatileCandles
import tradingsystems.Balance
import tradingsystems.MonthProfit
import tradingsystems.YearProfit

@RunWith(classOf[JUnitRunner])
class TradingPositionAnalyzer_Test extends FunSuite with TestUtils with AnalyticalStatisticsPrinter
{
    val ticker: String = null
    override lazy val data = new TradingData(standardImport("g:\\work\\trademachine\\SBER_2010_2013_1day.txt")
        .data.filter(_.date.getYear == 2013))
    val sell = TradingOp.sell(1, 5)
    val tradingPositions = new VolatileCandles(3, 3, _.buyProfit > 0).filterInterestingDays(data)
    val profit = new TradingPositionAnalyzer(data, (tradingPositions, sell)).positionDatesProfit
    val statistics = new TradingPositionAnalyzer(data, (tradingPositions, sell)).getStatistics
    val yearProfit = statistics(0)
    val monthProfits = yearProfit.monthProfits

    test("1 day"){ assert((new LocalDate(2013, 1, 15), new LocalDate(2013, 1, 17), -0.45) === profit(0)) }
    test("2 day"){ assert((new LocalDate(2013, 1, 21), new LocalDate(2013, 1, 23), 0.10) === profit(1)) }
    test("3 day"){ assert((new LocalDate(2013, 2, 13), new LocalDate(2013, 2, 14), -1.09) === profit(2)) }
    test("4 day"){ assert((new LocalDate(2013, 3, 7), new LocalDate(2013, 3, 11), -1.05) === profit(3)) }
    test("5 day"){ assert((new LocalDate(2013, 4, 5), new LocalDate(2013, 4, 9), -1) === profit(4)) }
    test("6 day"){ assert((new LocalDate(2013, 4, 11), new LocalDate(2013, 4, 12), 5.15) === profit(5)) }
    test("7 day"){ assert((new LocalDate(2013, 5, 6), new LocalDate(2013, 5, 7), -1.03) === profit(6)) }
    test("8 day"){ assert((new LocalDate(2013, 5, 22), new LocalDate(2013, 5, 22), -1.08) === profit(7)) }
    test("9 day"){ assert((new LocalDate(2013, 5, 23), new LocalDate(2013, 5, 24), 5.442) === profit(8)) }

    test("test profit statistics 1 month"){assert(MonthProfit(1, Balance(-0.35, -0.45, 2, 1, 1, Vector(new LocalDate(2013, 1, 25)), Vector(new LocalDate(2013, 1, 21)))) === monthProfits(0))}
    test("test profit statistics 2 month"){assert(MonthProfit(2, Balance(-1.09, -1.09, 1, 0, 1, Vector(), Vector(new LocalDate(2013, 2, 13)))) === monthProfits(1))}
    test("test profit statistics 3 month"){assert(MonthProfit(3, Balance(-1.05, -1.05, 1, 0, 1, Vector(new LocalDate(2013, 3, 7)), Vector())) === monthProfits(2))}
    test("test profit statistics 4 month"){assert(MonthProfit(4, Balance(4.15, -1, 2, 1, 1, Vector(new LocalDate(2013, 4, 5)), Vector(new LocalDate(2013, 4, 11)))) === monthProfits(3))}
    test("test profit statistics 5 month"){assert(MonthProfit(5, Balance(3.332, -2.11, 3, 1, 2, Vector(new LocalDate(2013, 5, 23)), Vector(new LocalDate(2013, 5, 6), new LocalDate(2013, 5, 22)))) === monthProfits(4))}
    test("test profit statistics year"){ assert(YearProfit(2013, Balance(4.992, -3.49, 9, 3, 6,
        Vector(new LocalDate(2013, 1, 21), new LocalDate(2013, 4, 11), new LocalDate(2013, 5, 23)),
        Vector(new LocalDate(2013, 1, 15), new LocalDate(2013, 2, 13), new LocalDate(2013, 3, 7), new LocalDate(2013, 4, 5), new LocalDate(2013, 5, 6), new LocalDate(2013, 5, 22))),
        100, monthProfits) === yearProfit) }
}
