package tradingsystems

import scala.collection.GenTraversableOnce

object CandleCalculator
{
    type Profit = Double

    def balanceHistory(candleProfits: GenTraversableOnce[Profit]): Balance =
        candleProfits.foldLeft(Balance(0.0, Int.MaxValue, candleProfits.size, 0, 0))
        {
            case(balance, profit) =>
                val newProfit = balance.profit + profit
                val (newPositive, newNegative) = if(profit > 0)
                    (balance.positiveDeals + 1, balance.negativeDeals)
                else (balance.positiveDeals, balance.negativeDeals + 1)
                Balance(newProfit, java.lang.Math.min(newProfit, balance.slump), balance.daysCount, newPositive, newNegative)
        }
}

import CandleCalculator.Profit

case class Balance(profit: Profit, slump: Profit, daysCount: Int, positiveDeals: Int, negativeDeals: Int)