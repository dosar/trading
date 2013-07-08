package tradingideas

import tradingsystems.{TradingData, Candle}
import tradinganalyzers.{TradingPosition, TradingOp}

class TooTrendyCandles(condition: Candle => Boolean, trendDays: Int) extends TradingIdea
{
    def filterInterestingDays(list: TradingData): Vector[TradingPosition] =
    {
        for((candle, index) <- shiftList(list.data, trendDays)
            if checkCondition(list.data, condition, index - trendDays to index - 1))
        yield new TradingPosition(candle)
    }
}