package tradingideas

import tradingsystems.{TradingData, Candle}
import tradinganalyzers.TradingPosition

/**
 * @author alespuh
 * @since 12.07.13
 */
trait TrendCandles extends TradingIdea
{
    def checkDaysCondition: Candle => Boolean
    def checkDays: Int
    def positionDays: Int

    def filterInterestingPositions(list: TradingData): Vector[TradingPosition] =
    {
        val data = list.data
        val checkRange = 0 until checkDays
        val positionRange = 0 until positionDays
        def checkPreDaysCond(index: Int) = checkRange.forall(checkInd => checkDaysCondition(data(index + checkInd)))
        def toVector(index: Int) = positionRange.map(ind => data(index + ind)).toArray

        val result = data.zipWithIndex.slice(checkDays, data.size - positionDays + 1)
            .filter{case (_, index) => checkPreDaysCond(index - checkDays)}
            .map{case (_, index) => new TradingPosition(toVector(index))}
        result.toVector
    }
}

class LongTrendCandles(override val checkDays: Int, override val positionDays: Int,
    override val checkDaysCondition: Candle => Boolean, direction: String) extends TrendCandles
{
    override val desc: String = checkDays + " дня " + direction + ", " + positionDays + " дня в "
}

class PositiveTrendCandles(checkDays: Int, positionDays: Int)
    extends LongTrendCandles(checkDays, positionDays, _.buyProfit > 0, "роста")
class NegativeTrendCandles(checkDays: Int, positionDays: Int)
    extends LongTrendCandles(checkDays, positionDays, _.sellProfit > 0, "падения")
class PositiveEnoughTrendCandles(checkDays: Int, positionDays: Int)
    extends LongTrendCandles(checkDays, positionDays, _.buyProfitPct > 0.15, "уверенного роста")
class NegativeEnoughTrendCandles(checkDays: Int, positionDays: Int)
    extends LongTrendCandles(checkDays, positionDays, _.sellProfitPct > 0.15, "уверенного падения")