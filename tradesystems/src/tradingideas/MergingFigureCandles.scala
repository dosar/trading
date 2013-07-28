package tradingideas

import tradingsystems.{TradingData, Candle}
import tradinganalyzers.TradingPosition

/**
 * @author alespuh
 * @since 25.07.13
 */
class MergingFigureCandles(checkDays: Int, positionDays: Int, checkOppositeDays: Int,
    checkDaysCondition: Candle => Boolean, prefix: String = "бычье") extends TradingIdea
{
    override val desc: String = prefix + " поглощение " + checkDays + "/" + checkOppositeDays + " " + positionDays + " дня в "

    def filterInterestingPositions(list: TradingData): Vector[TradingPosition] =
    {
        val data = list.data
        val checkRange = 0 until checkDays
        val checkOppositeRange = 0 until checkOppositeDays
        val positionRange = 0 until positionDays
        def checkPreDaysCond(index: Int) =
        {
            checkRange.forall(checkInd => checkDaysCondition(data(index + checkInd))) &&
                checkOppositeRange.forall(checkInd => !checkDaysCondition(data(index + checkDays + checkInd)))
        }
        def toVector(index: Int) = positionRange.map(ind => data(index + ind)).toArray

        val result = data.zipWithIndex.slice(checkDays + 1, data.size - positionDays + 1)
            .filter{case (_, index) => checkPreDaysCond(index - checkDays - 1)}
            .map{case (_, index) => new TradingPosition(toVector(index))}
        result.toVector
    }
}
