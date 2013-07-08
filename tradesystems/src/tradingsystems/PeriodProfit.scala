package tradingsystems

trait PeriodProfit

case class MonthProfit(month: Int, balance: Balance) extends PeriodProfit
{
    lazy val profit = balance.profit
    lazy val slump = balance.slump
    lazy val daysCount = balance.daysCount
    lazy val positiveDeals = balance.positiveDeals
    lazy val negativeDeals = balance.negativeDeals
}

case class YearProfit(year: Int, balance: Balance, avgPrice: Double, monthProfits: Vector[MonthProfit])
    extends PeriodProfit
{
    lazy val yearProfit = balance.profit
    lazy val yearSlump = balance.slump
    lazy val monthAverageProfit = monthProfits.map(_.profit).sum / monthProfits.size
    lazy val worstMonthSlump = monthProfits.minBy(_.slump).slump
    lazy val daysCount = balance.daysCount.formatted("%03d")
    lazy val positiveDeals = balance.positiveDeals.formatted("%03d")
    lazy val negativeDeals = balance.negativeDeals.formatted("%03d")

    def positiveProfit = yearProfit > 0

    def strictProfit(targetProfit: Double) = yearProfit / avgPrice > targetProfit

    override def toString: String = "year:" + year + " profit:" + yearProfit.formatted("%.2f") +
        " yearSlump:" + yearSlump.formatted("%.2f") + " monthAverageProfit:" + monthAverageProfit.formatted("%.2f") +
        " monthAverageSlump:" + worstMonthSlump.formatted("%.2f")

    def yearProfitString = List(year, positiveDeals, negativeDeals, yearProfit.formatted("%7.2f"),
        yearSlump.formatted("%.2f"), worstMonthSlumpString, avgPrice.formatted("%7.2f")).mkString("|")

    def worstMonthSlumpString = worstMonthSlump.formatted("%6.2f")

    def monthSlumpsString = monthProfits
//        .map(mp => "|" + mp.month + " | " + mp.daysCount.formatted("%03d") + " | " + mp.profit.formatted("%.2f") + "/" + mp.slump.formatted("%.2f")).mkString(" | ")
        .flatMap(mp => Array(mp.month, mp.daysCount.formatted("%03d"), mp.profit.formatted("%.2f"), mp.slump.formatted("%.2f"))).mkString(" | ", " | ", "")
}