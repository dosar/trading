package tradingsystems

import util.TradingImplicits.toAnyImplicits

trait PeriodProfit
case class MonthProfit(month: Int, profit: Double, slump: Double) extends PeriodProfit
case class YearProfit(year: Int, yearProfit: Double, yearSlump: Double, monthProfits: List[MonthProfit]) extends PeriodProfit
{
    lazy val monthAverageProfit = monthProfits.map(_.profit).sum / monthProfits.size
    lazy val monthAverageSlump = monthProfits.map(_.slump).sum / monthProfits.size

    override def toString: String = "year:" + year + " profit:" + yearProfit.formatted("%.2f") +
        " yearSlump:" + yearSlump.formatted("%.2f") + " monthAverageProfit:" + monthAverageProfit.formatted("%.2f") +
        " monthAverageSlump:" + monthAverageSlump.formatted("%.2f")

    def yearProfitString = year + " = " + yearProfit.formatted("%.2f")

    def avgSlumpString = year + ": avg = " + monthAverageSlump.formatted("%.2f")

    def monthSlumpsString = monthProfits
        .map(mp => mp.month + "=" + mp.profit.formatted("%.2f") + "/" + mp.slump.formatted("%.2f")).mkString(", ")
}

object TradingAnalytics
{
    import CandleCalculator._

    def zipWithProfit(candles: List[Candle], profit: Candle => Profit): List[(Candle, Profit)] =
        candles.zip(candles.map(profit))

    def zipWithProfit(candles: List[Candle], profit: Candle => Profit, loose: Candle => Profit, stop: Profit = 1): List[(Candle, Profit)] =
        candles.zip(candles.map
        {
            candle =>
                val loss = loose(candle)
                val plus = profit(candle)
                if(loss > stop) -stop else plus
        })

    def analyzeProfit(profitCandles: List[(Candle, Profit)]) =
        profitCandles.groupBy{case (candle, _ ) => (candle.date.getYear, candle.date.getMonthOfYear)}
            .toList.sortBy{case (pair, _) => pair}.map
        {
            case ((year, month), monthCandles) =>
                val history = CandleCalculator.balanceHistory(monthCandles)
                (year, MonthProfit(month, history.profit, history.slump))
        }.groupBy{case (year, monthProfit) => year}.toList.sortBy{case (year, _) => year}.flatMap
        {
            case (year, list) =>
                val monthProfits = list.map(_._2)
                val monthProfitValues = monthProfits.map(_.profit)
                YearProfit(year, monthProfitValues.sum, monthProfitValues.min, monthProfits) :: monthProfits
        }
}
