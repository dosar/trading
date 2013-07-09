package tradingsystems

trait PeriodProfit

case class MonthProfit(month: Int, balance: Balance) extends PeriodProfit
{
    lazy val profitPct = balance.profit.profitPct
    lazy val slumpPct = balance.profit.slumpPct
    lazy val daysCount = balance.daysCount
    lazy val positiveDeals = balance.positiveDeals
    lazy val negativeDeals = balance.negativeDeals
}

case class YearProfit(year: Int, balance: Balance, avgPrice: Double, monthProfits: Vector[MonthProfit])
    extends PeriodProfit
{
    lazy val yearProfitPct = balance.profit.profitPct
    lazy val yearSlumpPct = balance.profit.slumpPct
    lazy val monthAverageProfitPct = monthProfits.map(_.profitPct).sum / monthProfits.size
    lazy val worstMonthSlumpPct = monthProfits.minBy(_.slumpPct).slumpPct
    lazy val daysCount = balance.daysCount.formatted("%03d")
    lazy val positiveDeals = balance.positiveDeals.formatted("%03d")
    lazy val negativeDeals = balance.negativeDeals.formatted("%03d")

    def positiveProfit = yearProfitPct > 0

    def strictProfit(targetProfitPct: Double) = yearProfitPct > targetProfitPct

    override def toString: String = "year:" + year + " profit:" + yearProfitPct.formatted("%.2f") +
        " yearSlump:" + yearSlumpPct.formatted("%.2f") + " monthAverageProfit:" + monthAverageProfitPct.formatted("%.2f") +
        " monthAverageSlump:" + worstMonthSlumpPct.formatted("%.2f")

    def yearProfitString = List(year, positiveDeals, negativeDeals, yearProfitPct.formatted("%7.2f"),
        yearSlumpPct.formatted("%.2f"), worstMonthSlumpString, avgPrice.formatted("%7.2f")).mkString("|")

    def worstMonthSlumpString = worstMonthSlumpPct.formatted("%6.2f")

    def monthSlumpsString = monthProfits
//        .map(mp => "|" + mp.month + " | " + mp.daysCount.formatted("%03d") + " | " + mp.profit.formatted("%.2f") + "/" + mp.slump.formatted("%.2f")).mkString(" | ")
        .flatMap(mp => Array(mp.month, mp.daysCount.formatted("%03d"), mp.profitPct.formatted("%.2f"), mp.slumpPct.formatted("%.2f"))).mkString(" | ", " | ", "")
}