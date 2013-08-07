package ideas.combination.williamsvswilliams

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import tradingideas.{WilliamsAdMin, WilliamsAdMax}
import tradingsystems.TradingData
import tradinganalyzers.statistics.DayStandardImporter

/**
 * @author alespuh
 * @since 31.07.13
 */
@RunWith(classOf[JUnitRunner])
class WilliamsAdRtkmVsAllCombination_Test extends FunSuite with WilliamsAdSuccessfulStrategies
{
    test("rtkm VS gazp"){ checkCombination(gazpBestStrategies, DayStandardImporter.gazp, 60) }
    test("rtkm VS gmkn"){ checkCombination(gmknBestStrategies, DayStandardImporter.gmkn, 60) }
    test("rtkm VS lkoh"){ checkCombination(lkohBestStrategies, DayStandardImporter.lkoh, 60) }
    test("rtkm VS nvtk"){ checkCombination(nvtkBestStrategies, DayStandardImporter.nvtk, 60) }
    test("rtkm VS rosn"){ checkCombination(rosnBestStrategies, DayStandardImporter.rosn, 60) }
    test("rtkm VS rtkm"){ checkCombination(rtkmBestStrategies, DayStandardImporter.rtkm, 60) }
    test("rtkm VS sber"){ checkCombination(sberBestStrategies, DayStandardImporter.sber, 60) }

    def checkCombination(otherStrategies: Vector[(WilliamsAdMax, WilliamsAdMin)], otherData: TradingData, targetProfit: Int)
    {
        checkCombination(DayStandardImporter.rtkm, rtkmBestStrategies, otherData, otherStrategies, targetProfit)
    }
}