package tradingsystems

object TradingIdea
{
    def tooPositive2Days(list: List[Candle]) = tooTrendyCandles(list, _.buyProfit > 0, 2)
    def tooPositive3Days(list: List[Candle]) = tooTrendyCandles(list, _.buyProfit > 0, 3)
    def tooPositive4Days(list: List[Candle]) = tooTrendyCandles(list, _.buyProfit > 0, 4)

    def tooNegative2Days(list: List[Candle]) = tooTrendyCandles(list, _.sellProfit > 0, 2)
    def tooNegative3Days(list: List[Candle]) = tooTrendyCandles(list, _.sellProfit > 0, 3)
    def tooNegative4Days(list: List[Candle]) = tooTrendyCandles(list, _.sellProfit > 0, 4)

    def tooTrendyCandles(list: List[Candle], condition: Candle => Boolean, trendDays: Int) =
        for((candle, index) <- shiftList(list, trendDays) if checkCondition(list, condition, index - trendDays to index - 1))
        yield candle

    private def checkCondition(list: List[Candle], condition: Candle => Boolean, range: Range): Boolean =
        range.forall(index => condition(list(index)))

    private def shiftList(list: List[Candle], shift: Int) = list.zipWithIndex.drop(shift)
}
