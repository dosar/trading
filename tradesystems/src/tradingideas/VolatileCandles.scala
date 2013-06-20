package tradingideas

import tradingsystems.Candle
import tradinganalyzers.TradingOp

class VolatileCandles(checkDays: Int, positionDays: Int, op: TradingOp, condition: Candle => Boolean) extends TradingIdea
{
    def filterInterestingDays(list: List[Candle]): List[(Candle, TradingOp)] =
    {
        for((candle, index) <- shiftList(list, checkDays)
            if checkCondition(list, condition, index - checkDays to index - 1) && list.size - index >= positionDays;
            posCandles = list.slice(index, index + positionDays)
        )
        yield (Candle(candle.date, candle.open, posCandles.map(_.high).max, posCandles.map(_.low).min, posCandles.last.close), op)
    }
}
