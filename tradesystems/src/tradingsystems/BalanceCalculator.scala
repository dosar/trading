package tradingsystems

import org.joda.time.LocalDateTime

object BalanceCalculator
{
    type StartDate = LocalDateTime; type EndDate = LocalDateTime

    def balanceHistoryM(monthProfits: Array[MonthProfit]): Balance =
    {
        Balance(AccumulatedProfit.Accumulator(monthProfits.flatMap(_.balance.profit.profits)),
            monthProfits.flatMap(_.balance.positiveStartDatePositions),
            monthProfits.flatMap(_.balance.negativeStartDatePositions))
    }

    def balanceHistoryP(positionsProfits: Array[(StartDate, EndDate, Profit)]): Balance =
        balanceHistory(positionsProfits.map(triple => (triple._1, triple._3)))

    def balanceHistory(positionsProfits: Array[(StartDate, Profit)]): Balance =
    {
        val (positiveDeals, negativeDeals) = positionsProfits.partition{case (_, profit) => profit.profit > 0}
        val accProfit = AccumulatedProfit.Accumulator(positionsProfits.map(_._2))
        Balance(accProfit, positiveDeals.map(_._1), negativeDeals.map(_._1))
    }
}

case class Balance(profit: AccumulatedProfit, positiveStartDatePositions: Array[LocalDateTime], negativeStartDatePositions: Array[LocalDateTime])
{
    lazy val daysCount = profit.profits.length
    lazy val positiveDeals = positiveStartDatePositions.length
    lazy val negativeDeals = negativeStartDatePositions.length
}