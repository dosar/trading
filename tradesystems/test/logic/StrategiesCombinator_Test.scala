package logic

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import tradinganalyzers.statistics.{StrategyElement, StandardImporter, StrategiesCombinator}
import tradingideas.{NegativeTrendCandles, PositiveTrendCandles, WilliamsAdMin, WilliamsAdMax}
import tradinganalyzers.TradingOp._

/**
 * @author alespuh
 * @since 26.07.13
 */
@RunWith(classOf[JUnitRunner])
class StrategiesCombinator_Test extends FunSuite with TestUtils
{
    test("bruteForce for several isntrument")
    {
        val sber = StandardImporter.importSber
        new StrategiesCombinator{}.rearrangeStrategiesOps(
            StrategyElement(sber, new WilliamsAdMax(10, 5, 1), new WilliamsAdMin(10, 5, 1)),
            StrategyElement(sber, new PositiveTrendCandles(2, 5), new NegativeTrendCandles(2, 7)))
    }
}
