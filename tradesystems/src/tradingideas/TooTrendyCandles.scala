package tradingideas

import tradingsystems.Candle
import tradinganalyzers.TradingOp

class TooTrendyCandles(condition: Candle => Boolean, trendDays: Int, op: TradingOp) extends TradingIdea
{
    def filterInterestingDays(list: List[Candle]): List[(Candle, TradingOp)] =
    {
        for((candle, index) <- shiftList(list, trendDays)
            if checkCondition(list, condition, index - trendDays to index - 1))
        yield (candle, op)
    }
}