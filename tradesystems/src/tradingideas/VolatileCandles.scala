package tradingideas

import tradingsystems.{TradingData, Candle}
import tradinganalyzers.{TradingPosition, TradingOp}

case class VolatileCandles(checkDays: Int, positionDays: Int, checkDaysCondition: Candle => Boolean) extends TradingIdea
{
    def filterInterestingDays(list: TradingData): Vector[TradingPosition] =
    {
        val data = list.data
        val checkRange = 0 until checkDays
        val positionRange = 0 until positionDays
        def checkPreDaysCond(index: Int) = checkRange.forall(checkInd => checkDaysCondition(data(index + checkInd)))
        def toVector(index: Int) = positionRange.map(ind => data(index + ind)).toArray

        val result = data.zipWithIndex.slice(checkDays, data.size - positionDays + 1)
            .filter{case (_, index) => checkPreDaysCond(index - checkDays)}
            .map{case (_, index) => TradingPosition(toVector(index))}
        result.toVector
    }
}
