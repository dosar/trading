package logic

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import tradinganalyzers.statistics.{StandardImporter, StrategyElement, StrategiesCombinator, CombinationBruteForces}
import tradingideas.{WilliamsAdMin, WilliamsAdMax, NegativeTrendCandles, PositiveTrendCandles}

/**
 * @author alespuh
 * @since 26.07.13
 */
@RunWith(classOf[JUnitRunner])
class CombinationBruteForces_Test extends FunSuite with TestUtils
{
    test("SBER positive trend vs RTKM williams ad")
    {
        val sber = StandardImporter.importSber
        val rtkm = StandardImporter.importRtkm
        val lkoh = StandardImporter.importLkoh
        val nvtk = StandardImporter.importNvtk
        new StrategiesCombinator
        {
            override lazy val targetProfit: Int = 60
        }.bruteForceCombinations(
            StrategyElement(sber, new PositiveTrendCandles(2, 5), new NegativeTrendCandles(2, 7)),
//            StrategyElement(rtkm, new WilliamsAdMax(10, 2, 1), new WilliamsAdMin(5, 2, 1)),
            StrategyElement(nvtk, new WilliamsAdMax(5, 6, 2), new WilliamsAdMin(5, 7, 2)))
    }

    test("complete enumerate different items")
    {
        val result = new CombinationBruteForces().completeEnumeration(Vector(1, 2), 3)
        val iterator = result.iterator
        assert(8 === result.length)
        assert(Vector(1, 1, 1) === iterator.next)
        assert(Vector(1, 1, 2) === iterator.next)
        assert(Vector(1, 2, 1) === iterator.next)
        assert(Vector(1, 2, 2) === iterator.next)
        assert(Vector(2, 1, 1) === iterator.next)
        assert(Vector(2, 1, 2) === iterator.next)
        assert(Vector(2, 2, 1) === iterator.next)
        assert(Vector(2, 2, 2) === iterator.next)
    }

    test("rearrange different items")
    {
        val result = new CombinationBruteForces().rearrange(Vector(1, 2, 3, 4))
        assert(24 === result.length)
        val iterator = result.iterator
        assert(Vector(1, 2, 3, 4) === iterator.next)
        assert(Vector(1, 2, 4, 3) === iterator.next)
        assert(Vector(1, 3, 2, 4) === iterator.next)
        assert(Vector(1, 3, 4, 2) === iterator.next)
        assert(Vector(1, 4, 2, 3) === iterator.next)
        assert(Vector(1, 4, 3, 2) === iterator.next)

        assert(Vector(2, 1, 3, 4) === iterator.next)
        assert(Vector(2, 1, 4, 3) === iterator.next)
        assert(Vector(2, 3, 1, 4) === iterator.next)
        assert(Vector(2, 3, 4, 1) === iterator.next)
        assert(Vector(2, 4, 1, 3) === iterator.next)
        assert(Vector(2, 4, 3, 1) === iterator.next)

        assert(Vector(3, 1, 2, 4) === iterator.next)
        assert(Vector(3, 1, 4, 2) === iterator.next)
        assert(Vector(3, 2, 1, 4) === iterator.next)
        assert(Vector(3, 2, 4, 1) === iterator.next)
        assert(Vector(3, 4, 1, 2) === iterator.next)
        assert(Vector(3, 4, 2, 1) === iterator.next)

        assert(Vector(4, 1, 2, 3) === iterator.next)
        assert(Vector(4, 1, 3, 2) === iterator.next)
        assert(Vector(4, 2, 1, 3) === iterator.next)
        assert(Vector(4, 2, 3, 1) === iterator.next)
        assert(Vector(4, 3, 1, 2) === iterator.next)
        assert(Vector(4, 3, 2, 1) === iterator.next)
    }
}
