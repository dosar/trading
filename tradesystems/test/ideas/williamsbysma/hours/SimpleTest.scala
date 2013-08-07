package ideas.williamsbysma.hours

import tradinganalyzers.statistics.{HourStandardImporter, VolatileDaysStatisticalPrinter}
import tradingideas._
import tradingsystems.{YearProfit, TradingData}
import tradingsystems.TradingData
import tradingsystems.YearProfit

/**
 * @author alespuh
 * @since 28.07.13
 */
class SimpleTest(val ticker: String, override val targetProfit: Double, daysAfterForMax: Int, daysAfterForMin: Int,
    smaPeriod: Int, smaRiseFall: Boolean = true, override val checkDaysRange: Range = 3 to(15, 3)) extends VolatileDaysStatisticalPrinter
{
    override def getOp1Idea(periodForMax: Int, op1PositionDays: Int): TradingIdea =
        if(smaRiseFall) new WilliamsAdMaxBySmaRising(periodForMax, op1PositionDays, daysAfterForMax, smaPeriod)
        else new WilliamsAdMaxBySmaFalling(periodForMax, op1PositionDays, daysAfterForMax, smaPeriod)

    override def getOp2Idea(periodForMin: Int, op2PositionDays: Int): TradingIdea =
        if(smaRiseFall) new WilliamsAdMinBySmaFalling(periodForMin, op2PositionDays, daysAfterForMin, smaPeriod)
        else new WilliamsAdMinBySmaRising(periodForMin, op2PositionDays, daysAfterForMin, smaPeriod)

    override def isUsefulOutput(yearProfits: Vector[YearProfit]): Boolean =
        super.isUsefulOutput(yearProfits) && yearProfits.forall(yp => yp.worstMonthSlumpPct >= -7 && yp.yearSlumpPct >= -7)

    override lazy val data: TradingData = HourStandardImporter.standardImport(ticker)
}
