package tradinganalyzers.statistics

import tradingsystems.YearProfit

/**
 * @author alespuh
 * @since 20.07.13
 */
class YearProfitStatistics(yearProfits: Vector[YearProfit], years: Int = 4)
{
    def this(yearProfits: Array[YearProfit]) = this(yearProfits.toVector)

    def compactStat(targetProfit: Double, prefix: String): String =
    {
        val needDesc = yearProfits.length == years && yearProfits.take(3).forall(_.strictProfit(targetProfit)) &&
            yearProfits(3).strictProfit(targetProfit / 2)
        compactStat(needDesc, prefix)
    }

    def compactStat(needStat: Boolean, prefix: String): String =
    {
        if(needStat) compactStat(prefix)
        else null
    }

    def compactStat(prefix: String): String =
    {
        val yearProfitsDescription: String = yearProfits.map(_.yearProfitString).mkString("|")
        //            val monthAvgs = yearProfits.map(yp => yp.worstMonthSlumpString +
        //                yp.balance.negativeStartDatePositions.mkString("|", "|", "|") + yp.monthSlumpsString).mkString("|")
        val monthAvgs = yearProfits.map(_.monthSlumpsString).mkString("|")
        prefix + " | " + yearProfitsDescription + " | " + monthAvgs
    }
}
