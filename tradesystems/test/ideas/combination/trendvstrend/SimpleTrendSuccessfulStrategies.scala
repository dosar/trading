package ideas.combination.trendvstrend

import tradingideas.{NegativeTrendCandles, PositiveTrendCandles, WilliamsAdMin, WilliamsAdMax}
import tradinganalyzers.statistics.{StandardImporter, StrategiesCombinator, StrategyIdeaData}
import tradinganalyzers.TradingOp._
import tradingsystems.TradingData
import org.scalatest.FunSuite
import ideas.combination.williamsvswilliams.WilliamsAdSuccessfulStrategies

/**
 * @author alespuh
 * @since 31.07.13
 */
trait SimpleTrendSuccessfulStrategies
{
    def checkCombination(thisData: TradingData, thisStrategies: Vector[(PositiveTrendCandles, NegativeTrendCandles)],
        thatData: TradingData, thatStrategies: Vector[(PositiveTrendCandles, NegativeTrendCandles)], targetProfit: Int)
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

    val gazpBestStrategies = Vector(
        (new PositiveTrendCandles(2, 7), new NegativeTrendCandles(2, 6)),
        (new PositiveTrendCandles(2, 6), new NegativeTrendCandles(2, 6)))

    val gmknBestStrategies = Vector(
        (new PositiveTrendCandles(2, 2), new NegativeTrendCandles(5, 7)),
        (new PositiveTrendCandles(2, 2), new NegativeTrendCandles(5, 6)),
        (new PositiveTrendCandles(2, 2), new NegativeTrendCandles(3, 5)),
        (new PositiveTrendCandles(2, 3), new NegativeTrendCandles(3, 5)),
        (new PositiveTrendCandles(2, 2), new NegativeTrendCandles(3, 6)),
        (new PositiveTrendCandles(2, 2), new NegativeTrendCandles(5, 5)))

    val lkohBestStrategies = Vector(
        (new PositiveTrendCandles(2, 7), new NegativeTrendCandles(2, 6)),
        (new PositiveTrendCandles(3, 7), new NegativeTrendCandles(4, 3)),
        (new PositiveTrendCandles(3, 7), new NegativeTrendCandles(5, 5)),
        (new PositiveTrendCandles(3, 7), new NegativeTrendCandles(5, 6)),
        (new PositiveTrendCandles(2, 6), new NegativeTrendCandles(2, 6)))

    val nvtkBestStrategies = Vector(
        (new PositiveTrendCandles(3, 4), new NegativeTrendCandles(3, 4)),
        (new PositiveTrendCandles(2, 5), new NegativeTrendCandles(3, 7)),
        (new PositiveTrendCandles(2, 5), new NegativeTrendCandles(2, 5)),
        (new PositiveTrendCandles(3, 7), new NegativeTrendCandles(3, 4)))

    val rosnBestStrategies = Vector(
        (new PositiveTrendCandles(2, 7), new NegativeTrendCandles(3, 7)),
        (new PositiveTrendCandles(2, 7), new NegativeTrendCandles(2, 4)),
        (new PositiveTrendCandles(2, 7), new NegativeTrendCandles(3, 6)),
        (new PositiveTrendCandles(2, 7), new NegativeTrendCandles(3, 7)),
        (new PositiveTrendCandles(2, 7), new NegativeTrendCandles(3, 4)),
        (new PositiveTrendCandles(2, 7), new NegativeTrendCandles(2, 7)))

    val rtkmBestStrategies = Vector(
        (new PositiveTrendCandles(4, 6), new NegativeTrendCandles(2, 4)),
        (new PositiveTrendCandles(2, 5), new NegativeTrendCandles(2, 7)),
        (new PositiveTrendCandles(2, 6), new NegativeTrendCandles(2, 7)),
        (new PositiveTrendCandles(2, 6), new NegativeTrendCandles(2, 2)),
        (new PositiveTrendCandles(3, 5), new NegativeTrendCandles(2, 4)),
        (new PositiveTrendCandles(3, 6), new NegativeTrendCandles(2, 4)),
        (new PositiveTrendCandles(3, 6), new NegativeTrendCandles(2, 6)),
        (new PositiveTrendCandles(3, 7), new NegativeTrendCandles(2, 4)),
        (new PositiveTrendCandles(4, 3), new NegativeTrendCandles(2, 4)),
        (new PositiveTrendCandles(4, 4), new NegativeTrendCandles(2, 4)),
        (new PositiveTrendCandles(4, 7), new NegativeTrendCandles(2, 4))
    )

    val sberBestStrategies = Vector(
        (new PositiveTrendCandles(2, 3), new NegativeTrendCandles(2, 7)),
        (new PositiveTrendCandles(3, 3), new NegativeTrendCandles(2, 7)),
        (new PositiveTrendCandles(2, 5), new NegativeTrendCandles(2, 7)))
}
