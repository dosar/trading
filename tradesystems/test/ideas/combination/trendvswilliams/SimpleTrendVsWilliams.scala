package ideas.combination.trendvswilliams

import ideas.combination.williamsvswilliams.WilliamsAdSuccessfulStrategies
import tradingsystems.TradingData
import tradingideas.{WilliamsAdMin, WilliamsAdMax, NegativeTrendCandles, PositiveTrendCandles}
import tradinganalyzers.statistics.{StrategyIdeaData, StrategiesCombinator}
import tradinganalyzers.TradingOp._

/**
 * @author alespuh
 * @since 04.08.13
 */
trait SimpleTrendVsWilliams
{
    lazy val williams = new WilliamsAdSuccessfulStrategies{}
    def trendData: TradingData
    def trendStrategies: Vector[(PositiveTrendCandles, NegativeTrendCandles)]

    def checkCombination(otherStrategies: Vector[(WilliamsAdMax, WilliamsAdMin)], otherData: TradingData, targetProfit: Int)
    {
        checkCombination(trendData, trendStrategies, otherData, otherStrategies, targetProfit)
    }

    def checkCombination(thisData: TradingData, thisStrategies: Vector[(PositiveTrendCandles, NegativeTrendCandles)],
        thatData: TradingData, thatStrategies: Vector[(WilliamsAdMax, WilliamsAdMin)], targetProfit: Int)
    {
        val target = targetProfit
        for(thatStrategy <- thatStrategies.par; thisStrategy <- thisStrategies)
        {
            new StrategiesCombinator{override lazy val targetProfit = target}.rearrangeStrategies(
                (StrategyIdeaData(thatData, thatStrategy._1), sell),
                (StrategyIdeaData(thatData, thatStrategy._2), buy),
                (StrategyIdeaData(thisData, thisStrategy._1), sell),
                (StrategyIdeaData(thisData, thisStrategy._2), buy))
        }
    }
}