package tradinganalyzers

import tradingsystems._
import org.joda.time.LocalDate
import tradingsystems.BalanceCalculator._
import util.TradingImplicits.toSeqImplicits
import tradingsystems.TradingData
import tradingsystems.MonthProfit
import tradingsystems.Candle
import tradingsystems.YearProfit
import scala.collection.immutable.{Seq, Queue}
import scala.collection.{AbstractSeq, LinearSeqLike}

/**
 * @author alespuh
 * @since 23.06.13
 */

case class TradingPosition(candles: Array[Candle])
{
    def this(candles: Candle*) = this(candles.toArray)

    def this(candles: Vector[Candle]) = this(candles.toArray)

    lazy val candlesRange = 0 until candles.length
    val positionDate = candles.head.date
    val open = candles.head.open
    val (highCandle, highCandleIndex) = candles.zipWithIndex.maxBy{case (candle, index) => candle.high}
    val high = highCandle.high
    val (lowCandle, lowCandleIndex) = candles.zipWithIndex.minBy{case (candle, index) => candle.low}
    val low = lowCandle.low
    val close = candles.last.close
}

class TradingPositionAnalyzer(positions: Array[(TradingPosition, TradingOp)])
{
    def this(positionOps: Vector[(Vector[TradingPosition], TradingOp)]) =
        this(positionOps.flatMap{case (positionsOnly, op) => positionsOnly.map((_, op))}
            .sortBy(_._1.positionDate.toDate).toArray)

    def getStatistics: Array[YearProfit] = getStatistics(positionDatesProfit)

    def getStatistics(datesProfits: Array[(LocalDate, LocalDate, Profit)]): Array[YearProfit] =
    {
        val yearProfits = datesProfits
            .groupBy{case (date, _, _) => (date.getYear, date.getMonthOfYear)}.toVector
            .map{case ((year, month), profits) => (year, new MonthProfit(month, balanceHistoryP(profits)))}
            .groupBy{case (year, _) => year}.toVector
            .map
            {
                case (year, yearMonthProfits) =>
                    val monthProfits = yearMonthProfits.map(_._2).sortBy(_.month)
                    val yearBalanceHistory = balanceHistoryP(datesProfits.filter(_._1.getYear == year))
                    new YearProfit(year, yearBalanceHistory, monthProfits.toVector)
            }
        yearProfits.sortBy(_.year).toArray
    }

    def positionDatesProfit: Array[(LocalDate, LocalDate, Profit)] =
    {
        //считаем профиты по позициям и определяем период в течение которого позиция по факту удерживалась
        val profitPositions = positions.map
        {
            case (position, op) =>
                val (profit, index) = op.profit(position)
                (position.positionDate, position.candles(index).date, profit)
        }
        filterOverlappedPositions(profitPositions)
    }

    //фильтруем пересекающиеся позиции, оставляя более ранние
    def filterOverlappedPositions(profitPositions: Array[(LocalDate, LocalDate, Profit)]) =
    {
        profitPositions.foldLeft(Vector[(LocalDate, LocalDate, Profit)]())
        {
            case (vector, (startDate, endDate, profit)) =>
                val previousEndDate = vector.lastOption.map(_._2)
                if(previousEndDate.isEmpty || previousEndDate.get.compareTo(startDate) < 0)
                    vector :+ (startDate, endDate, profit)
                else vector
        }.toArray
    }
}