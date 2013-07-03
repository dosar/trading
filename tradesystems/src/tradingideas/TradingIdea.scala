package tradingideas

import tradingsystems.Candle
import tradinganalyzers.{TradingPosition, TradingOp}

object TradingIdea
{
    def tooTrendyCandles(list: Vector[Candle], condition: Candle => Boolean, trendDays: Int) =
        new TooTrendyCandles(condition, trendDays).filterInterestingDays(list)
}

trait TradingIdea
{
    def filterInterestingDays(list: Vector[Candle]): Vector[TradingPosition]

    protected def checkCondition(list: Vector[Candle], condition: Candle => Boolean, range: Range): Boolean =
        range.forall(index => condition(list(index)))

    protected def checkCondition(list: Vector[Candle], condition: Candle => Boolean): Boolean =
        list.forall(condition)

    protected def shiftList(list: Vector[Candle], shift: Int) = list.zipWithIndex.drop(shift)
}
