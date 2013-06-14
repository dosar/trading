package tradingsystems

import scala.io.Source
import org.joda.time.LocalDate

object CandleCalculator
{
    type Profit = Double

    def balanceHistory(candles: List[(Candle, Profit)]) = candles.foldLeft(Balance(0.0, List[Profit]()))
    {
        case(balance, (candle, profit)) =>
            val newProfit = balance.profit + profit
            Balance(newProfit, newProfit :: balance.history)
    }
}

import CandleCalculator.Profit

case class Balance(profit: Profit, history: List[Profit])
{
    lazy val slump = history.min
}