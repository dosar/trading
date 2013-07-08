package tradinganalyzers

import tradingsystems.{TradingData, YearProfit, MonthProfit, Candle}
import org.joda.time.LocalDate
import tradingsystems.CandleCalculator._
import util.TradingImplicits.toSeqImplicits

/**
 * @author alespuh
 * @since 23.06.13
 */

case class TradingPosition(candles: Array[Candle])
{
    def this(candles: Candle*) = this(candles.toArray)

    val positionDate = candles.head.date
    val open = candles.head.open
    val (highCandle, highCandleIndex) = candles.zipWithIndex.maxBy{case (candle, index) => candle.high}
    val high = highCandle.high
    val (lowCandle, lowCandleIndex) = candles.zipWithIndex.minBy{case (candle, index) => candle.low}
    val low = lowCandle.low
    val close = candles.last.close
}

class TradingPositionAnalyzer(data: TradingData, positions: Vector[(TradingPosition, TradingOp)])
{
    def this(data: TradingData, positionOps: (Vector[TradingPosition], TradingOp)*) =
        this(data, positionOps.flatMap{case (positionsOnly, op) => positionsOnly.map((_, op))}.toVector.sortBy(_._1.positionDate.toDate))

    def getStatistics: Vector[YearProfit] = getStatistics(positionDatesProfit)

    def getStatistics(datesProfits: Vector[(LocalDate, LocalDate, Double)]): Vector[YearProfit] =
    {
        val yearProfits = datesProfits
            .groupBy{case (date, _, _) => (date.getYear, date.getMonthOfYear)}.toArray
            .map{case ((year, month), profits) => (year, new MonthProfit(month, balanceHistoryP(profits)))}
            .groupBy{case (year, _) => year}.toArray
            .map
            {
                case (year, yearMonthProfits) =>
                    val monthProfits = yearMonthProfits.map(_._2).sortBy(_.month)
                    val yearBalanceHistory = balanceHistoryP(datesProfits.filter(_._1.getYear == year))
                    new YearProfit(year, yearBalanceHistory, avgPrice(year), monthProfits.toVector)
            }
        yearProfits.sortBy(_.year).toVector
    }

    def positionDatesProfit =
    {
        positions.map
        {
            case (position, op) =>
                val (profit, index) = op.profit(position)
                (position.positionDate, position.candles(index).date, profit)
        }.foldLeft(List[(LocalDate, LocalDate, Double)]())
        {
            case (list, (startDate, endDate, profit)) =>
                val previousEndDate = list.headOption.map(_._2)
                if(previousEndDate.isEmpty || previousEndDate.get.compareTo(startDate) < 0)
                    (startDate, endDate, profit) :: list
                else list
        }.reverse.toVector
    }

    private def avgPrice(year: Int) = data.avgPrice(year)
}