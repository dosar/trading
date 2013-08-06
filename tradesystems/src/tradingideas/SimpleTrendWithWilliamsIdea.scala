package tradingideas

import tradingsystems.TradingData
import tradinganalyzers.TradingPosition
import util.TradingImplicits.toAnyImplicits

class SimpleTrendPlusWilliamsIdea(williams: WilliamsAdIdea, simpleTrend: LongTrendCandles) extends TradingIdea
{
    def desc: String = "t + w|" + williams.desc + "|" + simpleTrend.desc

    def filterInterestingPositions(list: TradingData): Vector[TradingPosition] =
        williams.filterInterestingPositions(list) ++ simpleTrend.filterInterestingPositions(list)
}

/**
 * @author alespuh
 * @since 05.08.13
 */
class SimpleTrendWithWilliamsIdea(williams: WilliamsAdIdea, simpleTrend: LongTrendCandles) extends TradingIdea
{
    def desc: String = "t ^ w|" + williams.desc + "|" + simpleTrend.desc

    def filterInterestingPositions(list: TradingData): Vector[TradingPosition] =
    {
        val wlPositions = williams.filterInterestingPositions(list)
        val trendPositions = simpleTrend.filterInterestingPositions(list)
        val commonDates = wlPositions.map(_.positionDate).intersect(trendPositions.map(_.positionDate))
        wlPositions.filter(_.positionDate in commonDates)
    }
}

class SimpleWilliamsWithoutTrendIdea(williams: WilliamsAdIdea, simpleTrend: LongTrendCandles) extends TradingIdea
{
    def desc: String = "w - t|" + williams.desc + "|" + simpleTrend.desc

    def filterInterestingPositions(list: TradingData): Vector[TradingPosition] =
    {
        val wlPositions = williams.filterInterestingPositions(list)
        val trendPositions = simpleTrend.filterInterestingPositions(list)
        val trendDays = trendPositions.map(_.positionDate)
        val wlMinusTrendDates = wlPositions.map(_.positionDate).filterNot(_ in trendDays)
        wlPositions.filter(_.positionDate in wlMinusTrendDates)
    }
}

class SimpleTrendWithoutWilliamsIdea(williams: WilliamsAdIdea, simpleTrend: LongTrendCandles) extends TradingIdea
{
    def desc: String = "t - w|" + williams.desc + "|" + simpleTrend.desc

    def filterInterestingPositions(list: TradingData): Vector[TradingPosition] =
    {
        val wlPositions = williams.filterInterestingPositions(list)
        val trendPositions = simpleTrend.filterInterestingPositions(list)
        val wlDays = wlPositions.map(_.positionDate)
        val trendMinusWlDates = trendPositions.map(_.positionDate).filterNot(_ in wlDays)
        wlPositions.filter(_.positionDate in trendMinusWlDates)
    }
}
