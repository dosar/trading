package ideas.combination.trendvstrend

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import tradinganalyzers.statistics.StandardImporter
import tradingideas.{NegativeTrendCandles, PositiveTrendCandles, WilliamsAdMin, WilliamsAdMax}
import tradingsystems.TradingData

/**
 * @author alespuh
 * @since 30.07.13
 */
@RunWith(classOf[JUnitRunner])
class SimpleTrendSberVsAllCombination_Test extends FunSuite with SimpleTrendSuccessfulStrategies
{
    test("sber VS gazp"){ checkCombination(gazpBestStrategies, StandardImporter.importGazp, 30) }
    test("sber VS gmkn"){ checkCombination(gmknBestStrategies, StandardImporter.importGmkn, 53) }
    test("sber VS lkoh"){ checkCombination(lkohBestStrategies, StandardImporter.importLkoh, 36) }
    test("sber VS nvtk"){ checkCombination(nvtkBestStrategies, StandardImporter.importNvtk, 60) }
    test("sber VS rosn"){ checkCombination(rosnBestStrategies, StandardImporter.importRosn, 45) }
    test("sber VS rtkm"){ checkCombination(rtkmBestStrategies, StandardImporter.importRtkm, 47) }
    test("sber VS sber"){ checkCombination(sberBestStrategies, StandardImporter.importRtkm, 30) }

    def checkCombination(otherStrategies: Vector[(PositiveTrendCandles, NegativeTrendCandles)], otherData: TradingData, targetProfit: Int)
    {
        checkCombination(StandardImporter.importSber, sberBestStrategies, otherData, otherStrategies, targetProfit)
    }
}
