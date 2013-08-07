package ideas.combination.trendvstrend

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import tradinganalyzers.statistics.DayStandardImporter
import tradingideas.{NegativeTrendCandles, PositiveTrendCandles, WilliamsAdMin, WilliamsAdMax}
import tradingsystems.TradingData

/**
 * @author alespuh
 * @since 30.07.13
 */
@RunWith(classOf[JUnitRunner])
class SimpleTrendSberVsAllCombination_Test extends FunSuite with SimpleTrendSuccessfulStrategies
{
    test("sber VS gazp"){ checkCombination(gazpBestStrategies, DayStandardImporter.gazp, 30) }
    test("sber VS gmkn"){ checkCombination(gmknBestStrategies, DayStandardImporter.gmkn, 53) }
    test("sber VS lkoh"){ checkCombination(lkohBestStrategies, DayStandardImporter.lkoh, 36) }
    test("sber VS nvtk"){ checkCombination(nvtkBestStrategies, DayStandardImporter.nvtk, 60) }
    test("sber VS rosn"){ checkCombination(rosnBestStrategies, DayStandardImporter.rosn, 45) }
    test("sber VS rtkm"){ checkCombination(rtkmBestStrategies, DayStandardImporter.rtkm, 47) }
    test("sber VS sber"){ checkCombination(sberBestStrategies, DayStandardImporter.rtkm, 30) }

    def checkCombination(otherStrategies: Vector[(PositiveTrendCandles, NegativeTrendCandles)], otherData: TradingData, targetProfit: Int)
    {
        checkCombination(DayStandardImporter.sber, sberBestStrategies, otherData, otherStrategies, targetProfit)
    }
}
