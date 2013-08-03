package ideas.combination.williamsvswilliams

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import tradinganalyzers.statistics.StandardImporter
import tradingideas.{WilliamsAdMin, WilliamsAdMax}
import tradingsystems.TradingData

/**
 * @author alespuh
 * @since 31.07.13
 */
@RunWith(classOf[JUnitRunner])
class WilliamsAdLkohVsAllCombination_Test extends FunSuite with WilliamsAdSuccessfulStrategies
{
    test("lkoh VS gazp"){ checkCombination(gazpBestStrategies, StandardImporter.importGazp, 60) }
    test("lkoh VS gmkn"){ checkCombination(gmknBestStrategies, StandardImporter.importGmkn, 60) }
    test("lkoh VS nvtk"){ checkCombination(nvtkBestStrategies, StandardImporter.importNvtk, 60) }
    test("lkoh VS rosn"){ checkCombination(rosnBestStrategies, StandardImporter.importRosn, 60) }
    test("lkoh VS rtkm"){ checkCombination(rtkmBestStrategies, StandardImporter.importRtkm, 60) }
    test("lkoh VS sber"){ checkCombination(sberBestStrategies, StandardImporter.importSber, 60) }

    def checkCombination(otherStrategies: Vector[(WilliamsAdMax, WilliamsAdMin)], otherData: TradingData, targetProfit: Int)
    {
        checkCombination(StandardImporter.importLkoh, lkohBestStrategies, otherData, otherStrategies, targetProfit)
    }
}
