package tradingideas

import tradingsystems.{TradingData, Candle}
import tradinganalyzers.{TradingPosition, TradingOp}

class TooTrendyCandles(condition: Candle => Boolean, trendDays: Int) extends TradingIdea
{
    def filterInterestingPositions(list: TradingData): Vector[TradingPosition] =
    {
        for((candle, index) <- list.data.zipWithIndex.drop(trendDays)
            if (index - trendDays to index - 1).forall(i => condition(list.data(i))))
        yield new TradingPosition(candle)
    }

    def desc: String = "тренд в " + trendDays + " дней"
}