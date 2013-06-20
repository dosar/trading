package tradingsystems

trait PeriodProfit;
case class MonthProfit(month: Int, profit: Double, slump: Double) extends PeriodProfit
case class YearProfit(year: Int, yearProfit: Double, yearSlump: Double, avgPrice: Double, monthProfits: List[MonthProfit]) extends PeriodProfit
{
    lazy val monthAverageProfit = monthProfits.map(_.profit).sum / monthProfits.size
    lazy val monthAverageSlump = monthProfits.map(_.slump).sum / monthProfits.size

    override def toString: String = "year:" + year + " profit:" + yearProfit.formatted("%.2f") +
        " yearSlump:" + yearSlump.formatted("%.2f") + " monthAverageProfit:" + monthAverageProfit.formatted("%.2f") +
        " monthAverageSlump:" + monthAverageSlump.formatted("%.2f")

    def yearProfitString = year + " = " + yearProfit.formatted("%7.2f") + "(" + avgPrice.formatted("%7.2f") + ")"

    def avgSlumpString = year + ": avg = " + monthAverageSlump.formatted("%.2f")

    def monthSlumpsString = monthProfits
        .map(mp => mp.month + "=" + mp.profit.formatted("%.2f") + "/" + mp.slump.formatted("%.2f")).mkString(", ")
}