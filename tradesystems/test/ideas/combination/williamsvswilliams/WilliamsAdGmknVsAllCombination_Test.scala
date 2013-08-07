package ideas.combination.williamsvswilliams

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import tradinganalyzers.statistics.DayStandardImporter
import tradingideas.{WilliamsAdMin, WilliamsAdMax}
import tradingsystems.TradingData

/**
 * @author alespuh
 * @since 31.07.13
 */
@RunWith(classOf[JUnitRunner])
class WilliamsAdGmknVsAllCombination_Test extends FunSuite with WilliamsAdSuccessfulStrategies
{
    test("gmkn VS gazp"){ checkCombination(gazpBestStrategies, DayStandardImporter.gazp, 60) }
    test("gmkn VS lkoh"){ checkCombination(lkohBestStrategies, DayStandardImporter.lkoh, 60) }
    test("gmkn VS nvtk"){ checkCombination(nvtkBestStrategies, DayStandardImporter.nvtk, 60) }
    test("gmkn VS rosn"){ checkCombination(rosnBestStrategies, DayStandardImporter.rosn, 60) }
    test("gmkn VS rtkm"){ checkCombination(rtkmBestStrategies, DayStandardImporter.rtkm, 60) }
    test("gmkn VS sber"){ checkCombination(sberBestStrategies, DayStandardImporter.sber, 60) }

    def checkCombination(otherStrategies: Vector[(WilliamsAdMax, WilliamsAdMin)], otherData: TradingData, targetProfit: Int)
    {
        checkCombination(DayStandardImporter.gmkn, gmknBestStrategies, otherData, otherStrategies, targetProfit)
    }
}
