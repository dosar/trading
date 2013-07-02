package tradingsystems

trait PeriodProfit;

case class MonthProfit(month: Int, profit: Double, slump: Double, daysCount: Int) extends PeriodProfit
{
    def this(month: Int, balance: Balance) = this(month, balance.profit, balance.slump, balance.daysCount)
}

case class YearProfit(year: Int, yearProfit: Double, yearSlump: Double, avgPrice: Double, monthProfits: Vector[MonthProfit])
    extends PeriodProfit
{
    def this(year: Int, balance: Balance, avgPrice: Double, monthProfits: Vector[MonthProfit]) =
        this(year, balance.profit, balance.slump, avgPrice, monthProfits)

    lazy val monthAverageProfit = monthProfits.map(_.profit).sum / monthProfits.size
    lazy val monthAverageSlump = monthProfits.map(_.slump).sum / monthProfits.size
    lazy val daysCount = monthProfits.map(_.daysCount).sum.formatted("%03d")

    def positiveProfit = yearProfit > 0

    def strictProfit(targetProfit: Double) = yearProfit / avgPrice > targetProfit

    override def toString: String = "year:" + year + " profit:" + yearProfit.formatted("%.2f") +
        " yearSlump:" + yearSlump.formatted("%.2f") + " monthAverageProfit:" + monthAverageProfit.formatted("%.2f") +
        " monthAverageSlump:" + monthAverageSlump.formatted("%.2f")

    def yearProfitString = year + "|" + daysCount + "|" + yearProfit.formatted("%7.2f") + "|(" + avgPrice.formatted("%7.2f") + ")"

    def avgSlumpString = year + ":avg = " + monthAverageSlump.formatted("%.2f")

    def monthSlumpsString = monthProfits
        .map(mp => mp.month + " | " + mp.daysCount.formatted("%03d") + " | " + mp.profit.formatted("%.2f") + "/" + mp.slump.formatted("%.2f")).mkString(" | ")
}