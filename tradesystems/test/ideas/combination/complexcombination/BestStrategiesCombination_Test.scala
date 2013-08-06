package ideas.combination.complexcombination

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import logic.TestUtils
import tradinganalyzers.statistics.{StrategyIdeaData, YearProfitStatistics}
import tradinganalyzers.statistics.StandardImporter._
import tradinganalyzers.statistics.StrategyIdeaData
import tradingideas.{NegativeTrendCandles, WilliamsAdMin, PositiveTrendCandles, WilliamsAdMax}
import tradinganalyzers.TradingOp._
import tradinganalyzers.statistics.StrategyIdeaData

/**
 * @author alespuh
 * @since 06.08.13
 */
@RunWith(classOf[JUnitRunner])
class BestStrategiesCombination_Test extends FunSuite with TestUtils
{
    test("62% vs 64%")
    {
        YearProfitStatistics.runIdea(Vector(1, 2, 3, 4, 5), Vector(8, 9, 10, 11, 12), 60,
            (StrategyIdeaData(importRtkm, new PositiveTrendCandles(4, 3)), sell),
            (StrategyIdeaData(importSber, new WilliamsAdMax(3, 3, 2)), sell),
            (StrategyIdeaData(importSber, new PositiveTrendCandles(2, 5)), sell),
            (StrategyIdeaData(importNvtk, new PositiveTrendCandles(2, 5)), sell),
            (StrategyIdeaData(importSber, new WilliamsAdMin(3, 7, 2)), buy),
            (StrategyIdeaData(importRtkm, new NegativeTrendCandles(2, 4)), buy),
            (StrategyIdeaData(importNvtk, new NegativeTrendCandles(2, 5)), buy),
            (StrategyIdeaData(importSber, new NegativeTrendCandles(2, 7)), buy)
        )
    }
}