package ideas.combination.williamsvswilliams

import tradingideas.{WilliamsAdMin, WilliamsAdMax}
import tradingsystems.TradingData
import tradinganalyzers.statistics.{StrategyIdeaData, StrategiesCombinator, StandardImporter}
import tradinganalyzers.TradingOp._
import tradingsystems.TradingData
import tradinganalyzers.statistics.StrategyIdeaData

/**
 * @author alespuh
 * @since 31.07.13
 */
trait WilliamsAdSuccessfulStrategies
{
    def checkCombination(thisData: TradingData, thisStrategies: Vector[(WilliamsAdMax, WilliamsAdMin)],
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

    val gazpBestStrategies = Vector(
        (new WilliamsAdMax(9, 5, 1), new WilliamsAdMin(3, 4, 1)),
        (new WilliamsAdMax(3, 7, 1), new WilliamsAdMin(9, 2, 2)),
        (new WilliamsAdMax(15, 3, 1), new WilliamsAdMin(3, 6, 2)),
        (new WilliamsAdMax(3, 7, 1), new WilliamsAdMin(6, 2, 2)),
        (new WilliamsAdMax(6, 4, 1), new WilliamsAdMin(3, 2, 3)),
        (new WilliamsAdMax(15, 4, 2), new WilliamsAdMin(3, 5, 1)),
        (new WilliamsAdMax(15, 4, 2), new WilliamsAdMin(15, 6, 2)),
        (new WilliamsAdMax(15, 6, 2), new WilliamsAdMin(15, 6, 2)),
        (new WilliamsAdMax(12, 6, 2), new WilliamsAdMin(3, 7, 2)),
        (new WilliamsAdMax(3, 5, 2), new WilliamsAdMin(9, 5, 2)),
        (new WilliamsAdMax(15, 6, 2), new WilliamsAdMin(6, 2, 3)),
        (new WilliamsAdMax(6, 5, 2), new WilliamsAdMin(3, 2, 3)),
        (new WilliamsAdMax(3, 7, 3), new WilliamsAdMin(15, 7, 1)),
        (new WilliamsAdMax(6, 4, 3), new WilliamsAdMin(15, 2, 2)),
        (new WilliamsAdMax(6, 2, 3), new WilliamsAdMin(3, 2, 3)))

    val gmknBestStrategies = Vector(
        (new WilliamsAdMax(3, 5, 1), new WilliamsAdMin(3, 4, 1)),
        (new WilliamsAdMax(3, 5, 1), new WilliamsAdMin(3, 3, 2)),
        (new WilliamsAdMax(3, 6, 1), new WilliamsAdMin(3, 7, 3)),
        (new WilliamsAdMax(3, 2, 2), new WilliamsAdMin(3, 4, 1)),
        (new WilliamsAdMax(3, 4, 2), new WilliamsAdMin(3, 4, 2)),
        (new WilliamsAdMax(3, 5, 2), new WilliamsAdMin(6, 6, 3)),
        (new WilliamsAdMax(3, 3, 3), new WilliamsAdMin(9, 4, 1)),
        (new WilliamsAdMax(3, 4, 3), new WilliamsAdMin(6, 5, 2)),
        (new WilliamsAdMax(3, 4, 3), new WilliamsAdMin(3, 6, 3)))

    val lkohBestStrategies = Vector(
        (new WilliamsAdMax(3, 6, 1), new WilliamsAdMin(3, 5, 1)),
        (new WilliamsAdMax(6, 6, 1), new WilliamsAdMin(3, 4, 2)),
        (new WilliamsAdMax(6, 6, 1), new WilliamsAdMin(3, 3, 3)),
        (new WilliamsAdMax(3, 5, 2), new WilliamsAdMin(3, 4, 1)),
        (new WilliamsAdMax(3, 5, 2), new WilliamsAdMin(6, 4, 1)),
        (new WilliamsAdMax(3, 4, 2), new WilliamsAdMin(6, 5, 1)),
        (new WilliamsAdMax(3, 6, 2), new WilliamsAdMin(6, 5, 1)),
        (new WilliamsAdMax(3, 5, 2), new WilliamsAdMin(6, 5, 1)),
        (new WilliamsAdMax(6, 2, 2), new WilliamsAdMin(3, 2, 2)),
        (new WilliamsAdMax(9, 5, 2), new WilliamsAdMin(6, 4, 2)),
        (new WilliamsAdMax(9, 5, 2), new WilliamsAdMin(6, 6, 2)),
        (new WilliamsAdMax(3, 6, 2), new WilliamsAdMin(3, 4, 3)),
        (new WilliamsAdMax(3, 4, 3), new WilliamsAdMin(9, 5, 1)),
        (new WilliamsAdMax(3, 5, 3), new WilliamsAdMin(6, 5, 2)),
        (new WilliamsAdMax(9, 4, 3), new WilliamsAdMin(3, 4, 3)))

    val nvtkBestStrategies = Vector(
        (new WilliamsAdMax(6, 7, 1), new WilliamsAdMin(6, 5, 1)),
        (new WilliamsAdMax(6, 7, 1), new WilliamsAdMin(6, 7, 2)),
        (new WilliamsAdMax(6, 6, 1), new WilliamsAdMin(6, 6, 3)),
        (new WilliamsAdMax(6, 5, 2), new WilliamsAdMin(6, 7, 1)),
        (new WilliamsAdMax(3, 4, 2), new WilliamsAdMin(6, 6, 2)),
        (new WilliamsAdMax(3, 7, 2), new WilliamsAdMin(6, 6, 3)),
        (new WilliamsAdMax(3, 2, 3), new WilliamsAdMin(6, 7, 1)),
        (new WilliamsAdMax(6, 7, 3), new WilliamsAdMin(6, 7, 1)),
        (new WilliamsAdMax(6, 7, 3), new WilliamsAdMin(9, 7, 1)),
        (new WilliamsAdMax(3, 2, 3), new WilliamsAdMin(6, 6, 2)),
        (new WilliamsAdMax(6, 5, 3), new WilliamsAdMin(6, 7, 3)))

    val rosnBestStrategies = Vector(
        (new WilliamsAdMax(3, 7, 1), new WilliamsAdMin(15, 2, 1)),
        (new WilliamsAdMax(3, 5, 1), new WilliamsAdMin(6, 3, 2)),
        (new WilliamsAdMax(3, 6, 1), new WilliamsAdMin(3, 3, 3)),
        (new WilliamsAdMax(6, 6, 2), new WilliamsAdMin(12, 2, 1)),
        (new WilliamsAdMax(3, 2, 2), new WilliamsAdMin(12, 4, 2)),
        (new WilliamsAdMax(6, 4, 2), new WilliamsAdMin(12, 5, 2)),
        (new WilliamsAdMax(3, 4, 2), new WilliamsAdMin(12, 3, 3)),
        (new WilliamsAdMax(3, 3, 3), new WilliamsAdMin(3, 5, 1)),
        (new WilliamsAdMax(3, 3, 3), new WilliamsAdMin(15, 4, 2)),
        (new WilliamsAdMax(3, 3, 3), new WilliamsAdMin(15, 4, 3)))

    val rtkmBestStrategies = Vector(
        (new WilliamsAdMax(9, 5, 1), new WilliamsAdMin(3, 4, 1)),
        (new WilliamsAdMax(9, 5, 1), new WilliamsAdMin(3, 7, 2)),
        (new WilliamsAdMax(15, 7, 1), new WilliamsAdMin(3, 5, 2)),
        (new WilliamsAdMax(9, 5, 1), new WilliamsAdMin(3, 4, 2)),
        (new WilliamsAdMax(9, 3, 1), new WilliamsAdMin(6, 6, 3)),
        (new WilliamsAdMax(9, 4, 1), new WilliamsAdMin(6, 6, 3)),
        (new WilliamsAdMax(3, 6, 2), new WilliamsAdMin(3, 3, 1)),
        (new WilliamsAdMax(12, 2, 2), new WilliamsAdMin(3, 6, 1)),
        (new WilliamsAdMax(12, 3, 2), new WilliamsAdMin(3, 7, 2)),
        (new WilliamsAdMax(9, 7, 2), new WilliamsAdMin(3, 7, 3)),
        (new WilliamsAdMax(15, 2, 3), new WilliamsAdMin(3, 2, 1)),
        (new WilliamsAdMax(12, 7, 3), new WilliamsAdMin(3, 3, 2)),
        (new WilliamsAdMax(9, 5, 3), new WilliamsAdMin(6, 7, 3)))

    val sberBestStrategies = Vector(
        (new WilliamsAdMax(6, 4, 1), new WilliamsAdMin(6, 4, 1)),
        (new WilliamsAdMax(6, 6, 1), new WilliamsAdMin(6, 6, 2)),
        (new WilliamsAdMax(6, 6, 1), new WilliamsAdMin(12, 6, 3)),
        (new WilliamsAdMax(3, 3, 1), new WilliamsAdMin(3, 5, 3)),
        (new WilliamsAdMax(6, 3, 2), new WilliamsAdMin(6, 4, 1)),
        (new WilliamsAdMax(3, 3, 2), new WilliamsAdMin(3, 7, 2)),
        (new WilliamsAdMax(3, 5, 2), new WilliamsAdMin(6, 7, 3)),
        (new WilliamsAdMax(9, 3, 2), new WilliamsAdMin(12, 6, 3)),
        (new WilliamsAdMax(3, 2, 3), new WilliamsAdMin(6, 5, 1)),
        (new WilliamsAdMax(3, 3, 3), new WilliamsAdMin(15, 7, 2)),
        (new WilliamsAdMax(12, 7, 3), new WilliamsAdMin(6, 7, 3)),
        (new WilliamsAdMax(3, 3, 3), new WilliamsAdMin(12, 7, 3)))
}
