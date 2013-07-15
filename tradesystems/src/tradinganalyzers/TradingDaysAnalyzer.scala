package tradinganalyzers

import tradingsystems.{YearProfit, MonthProfit, BalanceCalculator, Candle}
import util.TradingImplicits.toSeqImplicits

/**
 * @author alespuh
 * @since 15.06.13
 */
case class TradingDaysAnalyzer(candleOps: (Vector[Candle], TradingOp)*)
{
    import BalanceCalculator._

    def getStatistics =
    {
        val groupedCandleOps = getCandleOps.sortBy(_._1.date.toDate)
            .groupBy{case (c, _) => (c.date.getYear, c.date.getMonthOfYear)}
        val ymProfits =
        (for(((year, month), ymCandles) <- groupedCandleOps;
            balance = balanceHistory(ymCandles.map{case (candle, op) => (candle.date, op.profit(candle))}))
        yield (year, new MonthProfit(month, balance), ymCandles.map(_._1)))
            .toVector.sortBy{case (year, mp, _) => (year, mp.month) }

        val yps = for((year, list) <- ymProfits.groupBy{case (year, _, _) => year};
            monthProfits = list.map(_._2);
            balance = balanceHistoryM(monthProfits);
            candles = list.flatMap(_._3))
        yield YearProfit(year, balance, monthProfits.toVector)
        yps.toVector.sortBy(_.year)
//        getCandleOps.map{case (c, op) => (c, op.profit(c))}.sortBy(_._1.date.toDate)
//            .groupBy{case (c, _) => c.date.getYear -> c.date.getMonthOfYear}.toList.sortBy{case (gr, list) => gr}
//            .map{case (ym, monthCandle) => ym -> balanceHistory(monthCandle.map(_._2))}
//            .map{case ((year, month), balance) => year -> MonthProfit(month, balance.profit, balance.slump)}
//            .groupBy{case (year, monthProfit) => year}.toList.sortBy{case (year, _) => year}
//            .map{case (year, monthProfits) => (year, monthProfits.map(_._2), balanceHistory(monthProfits.map(_._2.profit)))}
//            .flatMap{case (y, mProfits, b) => YearProfit(y, b.profit, b.slump, mProfits) :: mProfits}
    }

    def getSharpeRatio =
    {
        val dailyReturns = getCandleOps.map{case (candle, op) => op.ret(candle)}
//        sqrt(250) * mean(dailyReturns) / stdev(dailyReturns)
    }

    private def getCandleOps =
        for((candles, op) <- candleOps.toVector; candle <- candles) yield (candle, op)
}
