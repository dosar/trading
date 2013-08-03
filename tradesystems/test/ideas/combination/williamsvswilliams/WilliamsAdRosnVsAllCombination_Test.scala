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
class WilliamsAdRosnVsAllCombination_Test extends FunSuite with WilliamsAdSuccessfulStrategies
{
    test("rosn VS gazp"){ checkCombination(gazpBestStrategies, StandardImporter.importGazp, 60) }
    test("rosn VS gmkn"){ checkCombination(gmknBestStrategies, StandardImporter.importGmkn, 60) }
    test("rosn VS lkoh"){ checkCombination(lkohBestStrategies, StandardImporter.importLkoh, 60) }
    test("rosn VS nvtk"){ checkCombination(nvtkBestStrategies, StandardImporter.importNvtk, 60) }
    test("rosn VS rosn"){ checkCombination(rosnBestStrategies, StandardImporter.importRosn, 60) }
    test("rosn VS rtkm"){ checkCombination(rtkmBestStrategies, StandardImporter.importRtkm, 60) }
    test("rosn VS sber"){ checkCombination(sberBestStrategies, StandardImporter.importSber, 60) }

    def checkCombination(otherStrategies: Vector[(WilliamsAdMax, WilliamsAdMin)], otherData: TradingData, targetProfit: Int)
    {
        checkCombination(StandardImporter.importRosn, rosnBestStrategies, otherData, otherStrategies, targetProfit)
    }
}