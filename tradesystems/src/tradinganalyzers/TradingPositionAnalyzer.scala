package tradinganalyzers

import tradingsystems.{YearProfit, MonthProfit, Candle}
import org.joda.time.LocalDate
import tradingsystems.CandleCalculator._
import util.TradingImplicits.toSeqImplicits

/**
 * @author alespuh
 * @since 23.06.13
 */

case class TradingPosition(candles: Vector[Candle])
{
    def this(candles: Candle*) = this(candles.toVector)

    val positionDate = candles.head.date
    val open = candles.head.open
    val (highCandle, highCandleIndex) = candles.view.zipWithIndex.maxBy{case (candle, index) => candle.high}
    val high = highCandle.high
    val (lowCandle, lowCandleIndex) = candles.view.zipWithIndex.minBy{case (candle, index) => candle.low}
    val low = lowCandle.low
    val close = candles.last.close
}

class TradingPositionAnalyzer(positions: Vector[(TradingPosition, TradingOp)])
{
    def getStatistics =
    {
        val datesProfits = positionDatesProfit
        val yearProfits = datesProfits
            .groupBy{case (date, _, _) => (date.getYear, date.getMonthOfYear)}.toVector
            .map{case ((year, month), profits) => (year, new MonthProfit(month, balanceHistory(profits.view.map(_._3))))}
            .groupBy{case (year, _) => year}.toVector
            .map
            {
                case (year, yearMonthProfits) =>
                    val monthProfits = yearMonthProfits.view.map(_._2).sortBy(_.month)
                    val yearBalanceHistory = balanceHistory(datesProfits.view.filter(_._1.getYear == year).map(_._3))
                    new YearProfit(year, yearBalanceHistory, avgPrice(positions, year), monthProfits.toVector)
            }
        yearProfits.toVector.sortBy(_.year)
    }

    def positionDatesProfit = positions.view.sortBy(_._1.positionDate.toDate)
        .map
        {
            case (position, op) =>
                val (profit, index) = op.profit(position)
                (position.positionDate, position.candles(index).date, profit)
        }
        .foldLeft(List[(LocalDate, LocalDate, Double)]())
        {
            case (list, (startDate, endDate, profit)) =>
                val previousEndDate = list.headOption.map(_._2)
                if(previousEndDate.isEmpty || previousEndDate.get.compareTo(startDate) < 0)
                    (startDate, endDate, profit) :: list
                else list
        }.reverse.toVector

    private def avgPrice(positions: Vector[(TradingPosition, TradingOp)], year: Int) =
        positions.view.filter(_._1.positionDate.getYear == year).avg(_._1.open)
}