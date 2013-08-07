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
class WilliamsAdGazpVsAllCombination_Test extends FunSuite with WilliamsAdSuccessfulStrategies
{
    test("gazp VS gmkn"){ checkCombination(gmknBestStrategies, DayStandardImporter.gmkn, 60) }
    test("gazp VS lkoh"){ checkCombination(lkohBestStrategies, DayStandardImporter.lkoh, 60) }
    test("gazp VS nvtk"){ checkCombination(nvtkBestStrategies, DayStandardImporter.nvtk, 60) }
    test("gazp VS rosn"){ checkCombination(rosnBestStrategies, DayStandardImporter.rosn, 60) }
    test("gazp VS rtkm"){ checkCombination(rtkmBestStrategies, DayStandardImporter.rtkm, 60) }
//    |SBER|Min Williams A/D | 3|7|2|op:buy  p:11,00 s: 4,00|GAZP|Max Williams A/D |15|4|2|op:sell p:11,00 s: 4,00|SBER|Max Williams A/D | 3|3|2|op:sell p:11,00 s: 4,00|GAZP|Min Williams A/D | 3|5|1|op:buy  p:11,00 s: 4,00| | 2010|033|022|  77,03|-4,00|-10,39|2011|036|020|  72,35|-4,99| -8,00|2012|030|020|  65,19|0,00| -6,73|2013|014|009|  31,39|-0,25| -2,48 |  | 1 | 003 | 4,76 | -4,00 | 2 | 004 | 29,74 | 0,00 | 3 | 004 | 6,98 | 0,00 | 4 | 004 | -7,28 | -7,28 | 5 | 008 | 8,31 | -4,00 | 6 | 005 | -1,45 | -1,45 | 7 | 004 | 18,92 | 0,00 | 8 | 004 | -3,17 | -10,39 | 9 | 006 | 11,38 | -3,07 | 10 | 005 | 2,01 | -1,00 | 11 | 004 | 1,84 | -4,00 | 12 | 004 | 4,98 | 0,00| | 1 | 002 | 3,38 | 0,00 | 2 | 005 | -4,37 | -8,00 | 3 | 003 | 9,29 | -4,00 | 4 | 005 | 5,51 | -3,61 | 5 | 004 | 3,44 | -1,65 | 6 | 004 | 5,59 | -0,19 | 7 | 005 | -0,58 | -4,00 | 8 | 008 | 14,56 | -7,72 | 9 | 003 | 1,81 | -3,30 | 10 | 007 | 20,01 | 0,00 | 11 | 004 | 8,90 | 0,00 | 12 | 006 | 4,81 | 0,00| | 1 | 004 | 13,86 | 0,00 | 2 | 004 | 3,57 | -2,44 | 3 | 006 | -5,65 | -6,73 | 4 | 003 | 1,44 | 0,00 | 5 | 005 | -4,30 | -4,30 | 6 | 004 | 16,68 | 0,00 | 7 | 005 | 13,62 | 0,00 | 8 | 004 | 4,77 | 0,00 | 9 | 003 | 3,66 | 0,00 | 10 | 004 | 0,31 | 0,00 | 11 | 005 | 11,17 | 0,00 | 12 | 003 | 6,08 | -1,67| | 1 | 003 | 5,01 | -0,25 | 2 | 005 | 0,21 | -2,19 | 3 | 005 | 6,95 | -0,68 | 4 | 004 | 5,21 | -2,48 | 5 | 005 | 10,11 | 0,00 | 6 | 001 | 3,90 | 0,00
//    |SBER|Min Williams A/D | 3|7|2|op:buy  p:12,00 s: 4,00|GAZP|Max Williams A/D |15|4|2|op:sell p:12,00 s: 4,00|SBER|Max Williams A/D | 3|3|2|op:sell p:12,00 s: 4,00|GAZP|Min Williams A/D | 3|5|1|op:buy  p:12,00 s: 4,00| | 2010|033|020|  74,17|-4,00|-10,39|2011|036|019|  78,35|-4,99| -8,00|2012|030|020|  65,19|0,00| -6,73|2013|014|009|  31,39|-0,25| -2,48 |  | 1 | 003 | 4,76 | -4,00 | 2 | 004 | 29,74 | 0,00 | 3 | 004 | 6,98 | 0,00 | 4 | 004 | -7,28 | -7,28 | 5 | 007 | 8,62 | -4,00 | 6 | 005 | -1,45 | -1,45 | 7 | 004 | 18,92 | 0,00 | 8 | 004 | -3,17 | -10,39 | 9 | 006 | 8,52 | -3,07 | 10 | 004 | 1,71 | 0,00 | 11 | 004 | 1,84 | -4,00 | 12 | 004 | 4,98 | 0,00| | 1 | 002 | 3,38 | 0,00 | 2 | 005 | -4,37 | -8,00 | 3 | 003 | 9,29 | -4,00 | 4 | 005 | 5,51 | -3,61 | 5 | 004 | 3,44 | -1,65 | 6 | 004 | 5,59 | -0,19 | 7 | 005 | -0,58 | -4,00 | 8 | 008 | 15,56 | -7,72 | 9 | 003 | 1,81 | -3,30 | 10 | 006 | 25,01 | 0,00 | 11 | 004 | 8,90 | 0,00 | 12 | 006 | 4,81 | 0,00| | 1 | 004 | 13,86 | 0,00 | 2 | 004 | 3,57 | -2,44 | 3 | 006 | -5,65 | -6,73 | 4 | 003 | 1,44 | 0,00 | 5 | 005 | -4,30 | -4,30 | 6 | 004 | 16,68 | 0,00 | 7 | 005 | 13,62 | 0,00 | 8 | 004 | 4,77 | 0,00 | 9 | 003 | 3,66 | 0,00 | 10 | 004 | 0,31 | 0,00 | 11 | 005 | 11,17 | 0,00 | 12 | 003 | 6,08 | -1,67| | 1 | 003 | 5,01 | -0,25 | 2 | 005 | 0,21 | -2,19 | 3 | 005 | 6,95 | -0,68 | 4 | 004 | 5,21 | -2,48 | 5 | 005 | 10,11 | 0,00 | 6 | 001 | 3,90 | 0,00
//    |SBER|Min Williams A/D | 3|7|2|op:buy  p:13,00 s: 4,00|GAZP|Max Williams A/D |15|4|2|op:sell p:13,00 s: 4,00|SBER|Max Williams A/D | 3|3|2|op:sell p:13,00 s: 4,00|GAZP|Min Williams A/D | 3|5|1|op:buy  p:13,00 s: 4,00| | 2010|033|020|  75,17|-4,00|-10,39|2011|036|019|  76,46|-4,99| -8,00|2012|030|020|  65,19|0,00| -6,73|2013|014|009|  31,39|-0,25| -2,48 |  | 1 | 003 | 4,76 | -4,00 | 2 | 004 | 29,74 | 0,00 | 3 | 004 | 6,98 | 0,00 | 4 | 004 | -7,28 | -7,28 | 5 | 007 | 9,62 | -4,00 | 6 | 005 | -1,45 | -1,45 | 7 | 004 | 18,92 | 0,00 | 8 | 004 | -3,17 | -10,39 | 9 | 006 | 8,52 | -3,07 | 10 | 004 | 1,71 | 0,00 | 11 | 004 | 1,84 | -4,00 | 12 | 004 | 4,98 | 0,00| | 1 | 002 | 3,38 | 0,00 | 2 | 005 | -4,37 | -8,00 | 3 | 003 | 9,29 | -4,00 | 4 | 005 | 5,51 | -3,61 | 5 | 004 | 3,44 | -1,65 | 6 | 004 | 5,59 | -0,19 | 7 | 005 | -0,58 | -4,00 | 8 | 008 | 12,68 | -7,72 | 9 | 003 | 1,81 | -3,30 | 10 | 006 | 26,01 | 0,00 | 11 | 004 | 8,90 | 0,00 | 12 | 006 | 4,81 | 0,00| | 1 | 004 | 13,86 | 0,00 | 2 | 004 | 3,57 | -2,44 | 3 | 006 | -5,65 | -6,73 | 4 | 003 | 1,44 | 0,00 | 5 | 005 | -4,30 | -4,30 | 6 | 004 | 16,68 | 0,00 | 7 | 005 | 13,62 | 0,00 | 8 | 004 | 4,77 | 0,00 | 9 | 003 | 3,66 | 0,00 | 10 | 004 | 0,31 | 0,00 | 11 | 005 | 11,17 | 0,00 | 12 | 003 | 6,08 | -1,67| | 1 | 003 | 5,01 | -0,25 | 2 | 005 | 0,21 | -2,19 | 3 | 005 | 6,95 | -0,68 | 4 | 004 | 5,21 | -2,48 | 5 | 005 | 10,11 | 0,00 | 6 | 001 | 3,90 | 0,00
    test("gazp VS sber"){ checkCombination(sberBestStrategies, DayStandardImporter.sber, 62) }

    def checkCombination(otherStrategies: Vector[(WilliamsAdMax, WilliamsAdMin)], otherData: TradingData, targetProfit: Int)
    {
        checkCombination(DayStandardImporter.gazp, gazpBestStrategies, otherData, otherStrategies, targetProfit)
    }
}
