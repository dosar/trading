package tradingsystems

import org.joda.time.LocalDate

object BalanceCalculator
{
    type StartDate = LocalDate

    def balanceHistoryM(monthProfits: Vector[MonthProfit]): Balance =
    {
        Balance(AccumulatedProfit.Accumulator(monthProfits.flatMap(_.balance.profit.profits)),
            monthProfits.flatMap(_.balance.positiveStartDatePositions),
            monthProfits.flatMap(_.balance.negativeStartDatePositions))
    }

    def balanceHistoryP(positionsProfits: Vector[(StartDate, _, Profit)]): Balance =
        balanceHistory(positionsProfits.map(triple => (triple._1, triple._3)))

    def balanceHistory(positionsProfits: Vector[(StartDate, Profit)]): Balance =
    {
        val (positiveDeals, negativeDeals) = positionsProfits.partition{case (_, profit) => profit.profit > 0}
        val accProfit = AccumulatedProfit.Accumulator(positionsProfits.map(_._2))
        Balance(accProfit, positiveDeals.map(_._1), negativeDeals.map(_._1))
    }
}

case class Balance(profit: AccumulatedProfit, positiveStartDatePositions: Vector[LocalDate], negativeStartDatePositions: Vector[LocalDate])
{
    lazy val daysCount = profit.profits.length
    lazy val positiveDeals = positiveStartDatePositions.length
    lazy val negativeDeals = negativeStartDatePositions.length
}