package tradingsystems

trait PeriodProfit;

case class MonthProfit(month: Int, balance: Balance) extends PeriodProfit
{
    lazy val profit = balance.profit
    lazy val slump = balance.slump
    lazy val daysCount = balance.daysCount
    lazy val positiveDeals = balance.positiveDeals
    lazy val negativeDeals = balance.negativeDeals
}

case class YearProfit(year: Int, yearProfit: Double, yearSlump: Double, avgPrice: Double, monthProfits: Vector[MonthProfit])
    extends PeriodProfit
{
    def this(year: Int, balance: Balance, avgPrice: Double, monthProfits: Vector[MonthProfit]) =
        this(year, balance.profit, balance.slump, avgPrice, monthProfits)

    lazy val monthAverageProfit = monthProfits.map(_.profit).sum / monthProfits.size
    lazy val monthAverageSlump = monthProfits.map(_.slump).sum / monthProfits.size
    lazy val daysCount = monthProfits.map(_.daysCount).sum.formatted("%03d")
    lazy val positiveDeals = monthProfits.map(_.positiveDeals).sum.formatted("%03d")
    lazy val negativeDeals = monthProfits.map(_.negativeDeals).sum.formatted("%03d")

    def positiveProfit = yearProfit > 0

    def strictProfit(targetProfit: Double) = yearProfit / avgPrice > targetProfit

    override def toString: String = "year:" + year + " profit:" + yearProfit.formatted("%.2f") +
        " yearSlump:" + yearSlump.formatted("%.2f") + " monthAverageProfit:" + monthAverageProfit.formatted("%.2f") +
        " monthAverageSlump:" + monthAverageSlump.formatted("%.2f")

    def yearProfitString = List(year, daysCount, positiveDeals + "/" + negativeDeals, yearProfit.formatted("%7.2f"),
        "(" + avgPrice.formatted("%7.2f") + ")").mkString("|")

    def avgSlumpString = year + ":avg = " + monthAverageSlump.formatted("%.2f")

    def monthSlumpsString = monthProfits
        .map(mp => mp.month + " | " + mp.daysCount.formatted("%03d") + " | " + mp.profit.formatted("%.2f") + "/" + mp.slump.formatted("%.2f")).mkString(" | ")
}