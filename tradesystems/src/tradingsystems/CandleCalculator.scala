package tradingsystems

import scala.collection.GenTraversableOnce
import org.joda.time.LocalDate

object CandleCalculator
{
    type Profit = Double
    type StartDate = LocalDate

    def balanceHistory(monthProfits: Vector[MonthProfit]): Balance =
    {
        val (profit, slump) = monthProfits.foldLeft((0.0, Double.MaxValue))
        {
            case((currentProfit, currentSlump), mp) =>
                val newProfit = currentProfit + mp.profit
                (newProfit, java.lang.Math.min(newProfit, currentSlump))
        }

        Balance(profit, slump, monthProfits.map(_.daysCount).sum, monthProfits.map(_.positiveDeals).sum,
            monthProfits.map(_.negativeDeals).sum,
            monthProfits.flatMap(_.balance.positiveStartDatePositions),
            monthProfits.flatMap(_.balance.negativeStartDatePositions))
    }

    def balanceHistoryP(candleProfits: Vector[(StartDate, LocalDate, Profit)]): Balance =
        balanceHistory(candleProfits.map(triple => (triple._1, triple._3)))

    def balanceHistory(candleProfits: GenTraversableOnce[(StartDate, Profit)]): Balance =
        candleProfits.foldLeft(Balance(0.0, 0, candleProfits.size, 0, 0, Vector.empty, Vector.empty))
        {
            case(b, (startDate, profit)) =>
                val newProfit = b.profit + profit
                if(profit > 0)
                    Balance(newProfit, java.lang.Math.min(newProfit, b.slump), b.daysCount, b.positiveDeals + 1,
                        b.negativeDeals, b.positiveStartDatePositions :+ startDate, b.negativeStartDatePositions)
                else
                    Balance(newProfit, java.lang.Math.min(newProfit, b.slump), b.daysCount, b.positiveDeals,
                        b.negativeDeals + 1, b.positiveStartDatePositions, b.negativeStartDatePositions :+ startDate)
        }
}

import CandleCalculator.Profit

case class Balance(profit: Profit, slump: Profit, daysCount: Int, positiveDeals: Int, negativeDeals: Int,
    positiveStartDatePositions: Vector[LocalDate], negativeStartDatePositions: Vector[LocalDate])