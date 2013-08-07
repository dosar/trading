package ideas.williamsad.days

import tradinganalyzers.statistics.VolatileDaysStatisticalPrinter
import tradingideas.{WilliamsAdMin, WilliamsAdMax, TradingIdea}

/**
 * @author alespuh
 * @since 28.07.13
 */
class SimpleTest(val ticker: String, override val targetProfit: Double, daysAfterForMax: Int, daysAfterForMin: Int,
    override val checkDaysRange: Range = 3 to (15, 3)) extends VolatileDaysStatisticalPrinter
{
    override def getOp1Idea(periodForMax: Int, op1PositionDays: Int): TradingIdea =
        new WilliamsAdMax(periodForMax, op1PositionDays, daysAfterForMax)

    override def getOp2Idea(periodForMin: Int, op2PositionDays: Int): TradingIdea =
        new WilliamsAdMin(periodForMin, op2PositionDays, daysAfterForMin)
}
