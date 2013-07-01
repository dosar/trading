package tradingsystems

import scala.collection.GenTraversableOnce

object CandleCalculator
{
    type Profit = Double

    def balanceHistory(candleProfits: GenTraversableOnce[Profit]): Balance =
        candleProfits.foldLeft(Balance(0.0, Int.MaxValue, candleProfits.size))
        {
            case(history, profit) =>
                val newProfit = history.profit + profit
                Balance(newProfit, java.lang.Math.min(newProfit, history.slump), history.daysCount)
        }
}

import CandleCalculator.Profit

case class Balance(profit: Profit, slump: Profit, daysCount: Int)