package ideas.combination.trendvstrend

import org.scalatest.FunSuite
import tradinganalyzers.statistics.{StrategyIdeaData, StrategiesCombinator, StandardImporter}
import tradingideas.{WilliamsAdMin, WilliamsAdMax, NegativeTrendCandles, PositiveTrendCandles}
import tradingsystems.TradingData
import tradinganalyzers.TradingOp._
import tradingsystems.TradingData
import tradinganalyzers.statistics.StrategyIdeaData

/**
 * @author alespuh
 * @since 03.08.13
 */
class SimpleTrendGazpVsAllCombination_Test extends FunSuite with SimpleTrendSuccessfulStrategies
{
    test("gazp VS gmkn"){ checkCombination(gmknBestStrategies, StandardImporter.importGmkn, 30) }
    test("gazp VS lkoh"){ checkCombination(lkohBestStrategies, StandardImporter.importLkoh, 30) }
    test("gazp VS nvtk"){ checkCombination(nvtkBestStrategies, StandardImporter.importNvtk, 30) }
    test("gazp VS rosn"){ checkCombination(rosnBestStrategies, StandardImporter.importRosn, 30) }
    test("gazp VS rtkm"){ checkCombination(rtkmBestStrategies, StandardImporter.importRtkm, 30) }
    test("gazp VS sber"){ checkCombination(sberBestStrategies, StandardImporter.importSber, 30) }

    def checkCombination(otherStrategies: Vector[(PositiveTrendCandles, NegativeTrendCandles)], otherData: TradingData, targetProfit: Int)
    {
        checkCombination(StandardImporter.importGazp, gazpBestStrategies, otherData, otherStrategies, targetProfit)
    }
}
