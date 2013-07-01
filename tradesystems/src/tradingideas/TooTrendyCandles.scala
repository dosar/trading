package tradingideas

import tradingsystems.Candle
import tradinganalyzers.{TradingPosition, TradingOp}

class TooTrendyCandles(condition: Candle => Boolean, trendDays: Int) extends TradingIdea
{
    def filterInterestingDays(list: Vector[Candle]): Vector[TradingPosition] =
    {
        for((candle, index) <- shiftList(list, trendDays)
            if checkCondition(list, condition, index - trendDays to index - 1))
        yield TradingPosition(Vector(candle))
    }
}