package tradingideas

import tradingsystems.{TradingData, Candle}
import tradinganalyzers.{TradingPosition, TradingOp}

object TradingIdea
{
    def tooTrendyCandles(list: TradingData, condition: Candle => Boolean, trendDays: Int) =
        new TooTrendyCandles(condition, trendDays).filterInterestingPositions(list)

    final val Up = "роста"
    final val Down = "падения"
}

trait TradingIdea
{
    def filterInterestingPositions(list: TradingData): Vector[TradingPosition]

    def desc: String
}
