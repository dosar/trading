package tradinganalyzers

import tradingsystems.{YearProfit, MonthProfit, CandleCalculator, Candle}
import util.TradingImplicits.toSeqImplicits

/**
 * @author alespuh
 * @since 15.06.13
 */
case class TradingDaysAnalyzer(candleOps: (List[Candle], TradingOp)*)
{
    import CandleCalculator._

    def getStatistics =
    {
        val ymProfits = for(((year, month), ymCandles) <- getCandleOps.sortBy(_._1.date.toDate)
            .groupBy{case (c, _) => c.date.getYear -> c.date.getMonthOfYear}.toList.sortBy{case (gr, list) => gr};
            balance = balanceHistory(ymCandles.map{case (candle, op) => op.profit(candle)}))
        yield (year, MonthProfit(month, balance.profit, balance.slump), ymCandles.map(_._1))

        for((year, list) <- ymProfits.groupBy{case (year, _, _) => year}.toList.sortBy(_._1);
            monthProfits = list.map(_._2); balance = balanceHistory(monthProfits.map(_.profit)); candles = list.flatMap(_._3))
        yield YearProfit(year, balance.profit, balance.slump, candles.avg(_.open), monthProfits)
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
        for((candles, op) <- candleOps; candle <- candles) yield (candle, op)
}
