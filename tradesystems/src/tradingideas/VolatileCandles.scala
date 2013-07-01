package tradingideas

import tradingsystems.Candle
import tradinganalyzers.{TradingPosition, TradingOp}

case class VolatileCandles(checkDays: Int, positionDays: Int, checkDaysCondition: Candle => Boolean) extends TradingIdea
{
    def filterInterestingDays(list: Vector[Candle]): Vector[TradingPosition] =
    {
        val listView = list.view
        val result = listView.zipWithIndex.slice(checkDays, list.size - positionDays + 1)
            .filter{case (candle, index) => listView.slice(index - checkDays, index).forall(checkDaysCondition)}
            .map{case (candle, index) => TradingPosition(listView.slice(index, index + positionDays).toVector)}
        result.toVector
    }
}
