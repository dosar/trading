package tradingideas

import tradingsystems.Candle
import tradinganalyzers.{TradingPosition, TradingOp}

case class VolatileCandles(checkDays: Int, positionDays: Int, checkDaysCondition: Candle => Boolean) extends TradingIdea
{
    def filterInterestingDays(list: Vector[Candle]): Vector[TradingPosition] =
    {
        val checkRange = 0 until checkDays
        val positionRange = 0 until positionDays
        def checkPreDaysCond(index: Int) = checkRange.forall(checkInd => checkDaysCondition(list(index + checkInd)))
        def toVector(index: Int) = positionRange.map(ind => list(index + ind)).toArray

        val result = list.zipWithIndex.slice(checkDays, list.size - positionDays + 1)
            .filter{case (_, index) => checkPreDaysCond(index - checkDays)}
            .map{case (_, index) => TradingPosition(toVector(index))}
        result.toVector
    }
}
