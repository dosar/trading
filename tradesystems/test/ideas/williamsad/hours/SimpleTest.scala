package ideas.williamsad.hours

import tradinganalyzers.statistics.{HourStandardImporter, VolatileDaysStatisticalPrinter}
import tradingideas.{WilliamsAdMin, WilliamsAdMax, TradingIdea}
import tradingsystems.{YearProfit, TradingData}

/**
 * @author alespuh
 * @since 28.07.13
 */
class SimpleTest(val ticker: String, override val targetProfit: Double, daysAfterForMax: Int, daysAfterForMin: Int,
    override val checkDaysRange: Range = 3 to(15, 3)) extends VolatileDaysStatisticalPrinter
{
    override def getOp1Idea(periodForMax: Int, op1PositionDays: Int): TradingIdea =
        new WilliamsAdMax(periodForMax, op1PositionDays, daysAfterForMax)

    override def getOp2Idea(periodForMin: Int, op2PositionDays: Int): TradingIdea =
        new WilliamsAdMin(periodForMin, op2PositionDays, daysAfterForMin)

    override def isUsefulOutput(yearProfits: Vector[YearProfit]): Boolean =
        super.isUsefulOutput(yearProfits) && yearProfits.forall(yp => yp.worstMonthSlumpPct >= -7 && yp.yearSlumpPct >= -7)

    override lazy val data: TradingData = HourStandardImporter.standardImport(ticker)
}
