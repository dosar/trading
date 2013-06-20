package tradingsystems

import scala.collection.GenTraversableOnce

object CandleCalculator
{
    type Profit = Double

    def balanceHistory(candles: GenTraversableOnce[Profit]): Balance = candles.foldLeft(Balance(0.0, List[Profit]()))
    {
        case(balance, profit) =>
            val newProfit = balance.profit + profit
            Balance(newProfit, newProfit :: balance.history)
    }
}

import CandleCalculator.Profit

case class Balance(profit: Profit, history: List[Profit])
{
    lazy val slump = history.min
}