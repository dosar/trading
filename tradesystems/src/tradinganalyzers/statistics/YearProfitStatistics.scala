package tradinganalyzers.statistics

import tradingsystems.{TradingData, YearProfit}
import scala.math._
import scala.Array
import tradinganalyzers.TradingOp._
import tradinganalyzers.{TradingPositionAnalyzer, TradingOp}
import tradingideas.TradingIdea
import org.joda.time.LocalDateTime

object YearProfitStatistics
{
    type Prefix = String
    type TradingOpFunc = (Double, Double) => TradingOp

    def exportData(dataIdeas: (StrategyIdeaData, TradingOp)*)
    {
        val positionOps = dataIdeas.toVector.map{case(ideaData, op) => (ideaData.filterInterestingPositions, op)}
        val yps = new TradingPositionAnalyzer(positionOps).getStatistics
        val profits = yps.flatMap(_.balance.profit.profits.map(_.profit * 100))
        val dateTimes = yps.flatMap(yp => yp.balance.positiveStartDatePositions ++ yp.balance.negativeStartDatePositions)
            .sortBy(_.toDate)
        for((date, profit) <- dateTimes.zipAll(profits, LocalDateTime.now(), 0.0))
        {
            val year = date.getYear
            val month = date.getMonthOfYear
            val day = date.getDayOfMonth
            val hour = date.getHourOfDay
            val minute = date.getMinuteOfHour
            println(Vector(year, month, day, hour, minute, profit.toString.replace(",", ".")).mkString(","))
        }
    }

    def runIdea(stops: Vector[Double], takeProfits: Vector[Double], targetProfit: Double, ideasWithOp: (StrategyIdeaData, TradingOpFunc)*)
    {
        val positionOpFunc = ideasWithOp
            .flatMap{case (idea, opFunc) => idea.filterInterestingPositions.map((_, opFunc))}
            .sortBy(_._1.positionDate.toDate)
        val statistics = (for(stop <- stops; takeProfit <- takeProfits) yield
        {
            val positionOps = positionOpFunc.map{case (position, opf) => (position, opf(stop, takeProfit))}
            val yps = new TradingPositionAnalyzer(positionOps.toArray).getStatistics
            if(matchTargetProfit(yps.toVector, targetProfit))
            {
                val prefix = ideasWithOp
                    .map{case (ideaData, opf) => (ideaData, opf(stop, takeProfit))}
                    .map{case (ideaData, op) => ideaData.desc + op.desc}.mkString("|", "|", "|")
                (prefix, yps)
            }
            else null
        }).filter(_ != null).toVector
        YearProfitStatistics.printStatistics(statistics)
    }

    def minProfit(yps: Array[YearProfit]) = min(yps.take(3).map(_.yearProfitPct).min, yps(3).yearProfitPct * 2)

    def printStatistics(statistics: Vector[(Prefix, Array[YearProfit])]) =
    {
        for ((prefix, yps) <- statistics.sortBy{case (prefix, yps) => minProfit(yps)})
            println(new YearProfitStatistics(yps).compactStat(prefix))
    }

    def matchTargetProfit(yps: Vector[YearProfit], targetProfit: Double) =
        yps.length == 4 && yps.take(3).forall(_.strictProfit(targetProfit)) && yps(3).strictProfit(targetProfit / 2)
}

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