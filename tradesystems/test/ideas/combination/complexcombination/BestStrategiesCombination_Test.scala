package ideas.combination.complexcombination

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import logic.TestUtils
import tradinganalyzers.statistics.{StrategyIdeaData, YearProfitStatistics}
import tradinganalyzers.statistics.DayStandardImporter._
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
            (StrategyIdeaData(rtkm, new PositiveTrendCandles(4, 3)), sell),
            (StrategyIdeaData(sber, new WilliamsAdMax(3, 3, 2)), sell),
            (StrategyIdeaData(sber, new PositiveTrendCandles(2, 5)), sell),
            (StrategyIdeaData(nvtk, new PositiveTrendCandles(2, 5)), sell),
            (StrategyIdeaData(sber, new WilliamsAdMin(3, 7, 2)), buy),
            (StrategyIdeaData(rtkm, new NegativeTrendCandles(2, 4)), buy),
            (StrategyIdeaData(nvtk, new NegativeTrendCandles(2, 5)), buy),
            (StrategyIdeaData(sber, new NegativeTrendCandles(2, 7)), buy)
        )
    }
}