package ideas.combination.williamsvswilliams

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import tradingideas.{WilliamsAdMin, WilliamsAdMax}
import tradingsystems.TradingData
import tradinganalyzers.statistics.StandardImporter

/**
 * @author alespuh
 * @since 31.07.13
 */
@RunWith(classOf[JUnitRunner])
class WilliamsAdRtkmVsAllCombination_Test extends FunSuite with WilliamsAdSuccessfulStrategies
{
    test("rtkm VS gazp"){ checkCombination(gazpBestStrategies, StandardImporter.importGazp, 60) }
    test("rtkm VS gmkn"){ checkCombination(gmknBestStrategies, StandardImporter.importGmkn, 60) }
    test("rtkm VS lkoh"){ checkCombination(lkohBestStrategies, StandardImporter.importLkoh, 60) }
    test("rtkm VS nvtk"){ checkCombination(nvtkBestStrategies, StandardImporter.importNvtk, 60) }
    test("rtkm VS rosn"){ checkCombination(rosnBestStrategies, StandardImporter.importRosn, 60) }
    test("rtkm VS rtkm"){ checkCombination(rtkmBestStrategies, StandardImporter.importRtkm, 60) }
    test("rtkm VS sber"){ checkCombination(sberBestStrategies, StandardImporter.importSber, 60) }

    def checkCombination(otherStrategies: Vector[(WilliamsAdMax, WilliamsAdMin)], otherData: TradingData, targetProfit: Int)
    {
        checkCombination(StandardImporter.importRtkm, rtkmBestStrategies, otherData, otherStrategies, targetProfit)
    }
}