package ideas.combination.trendvstrend

import org.scalatest.FunSuite
import tradinganalyzers.statistics.{StrategyIdeaData, StrategiesCombinator, DayStandardImporter}
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
    test("gazp VS gmkn"){ checkCombination(gmknBestStrategies, DayStandardImporter.gmkn, 30) }
    test("gazp VS lkoh"){ checkCombination(lkohBestStrategies, DayStandardImporter.lkoh, 30) }
    test("gazp VS nvtk"){ checkCombination(nvtkBestStrategies, DayStandardImporter.nvtk, 30) }
    test("gazp VS rosn"){ checkCombination(rosnBestStrategies, DayStandardImporter.rosn, 30) }
    test("gazp VS rtkm"){ checkCombination(rtkmBestStrategies, DayStandardImporter.rtkm, 30) }
    test("gazp VS sber"){ checkCombination(sberBestStrategies, DayStandardImporter.sber, 30) }

    def checkCombination(otherStrategies: Vector[(PositiveTrendCandles, NegativeTrendCandles)], otherData: TradingData, targetProfit: Int)
    {
        checkCombination(DayStandardImporter.gazp, gazpBestStrategies, otherData, otherStrategies, targetProfit)
    }
}
