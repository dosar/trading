package statistics

import org.scalatest.FunSuite
import logic.TestUtils
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import tradinganalyzers.statistics.VolatileDaysCombinationStatisticalPrinter
import tradinganalyzers.TradingOp
import tradinganalyzers.TradingOp._
import tradingsystems.YearProfit
import org.joda.time.LocalDate

/**
 * @author alespuh
 * @since 11.07.13
 */
@RunWith(classOf[JUnitRunner])
class VolatileDaysCombinationStatisticalPrinter_Test extends FunSuite with TestUtils
{
    test("hasIntersection")
    {
        new VolatileDaysCombinationStatisticalPrinter
        {
            def hasIntersection(oneLeft: (Year, Month, Day), oneRight: (Year, Month, Day), twoLeft: (Year, Month, Day), twoRight: (Year, Month, Day)):Boolean =
                hasIntersection((oneLeft.toLocalDate, oneRight.toLocalDate, ""), (twoLeft.toLocalDate, twoRight.toLocalDate, ""))

            assert(true === hasIntersection((2013, 1, 10), (2013, 1, 15), (2013, 1, 15), (2013, 1, 15)))
            assert(true === hasIntersection((2013, 1, 10), (2013, 1, 15), (2013, 1, 5), (2013, 1, 10)))
            assert(true === hasIntersection((2013, 1, 10), (2013, 1, 15), (2013, 1, 10), (2013, 1, 15)))
            assert(true === hasIntersection((2013, 1, 10), (2013, 1, 15), (2013, 1, 11), (2013, 1, 14)))
            assert(true === hasIntersection((2013, 1, 10), (2013, 1, 15), (2013, 1, 11), (2013, 1, 16)))
            assert(true === hasIntersection((2013, 1, 10), (2013, 1, 15), (2013, 1, 1), (2013, 1, 11)))
            assert(false === hasIntersection((2013, 1, 10), (2013, 1, 15), (2013, 1, 1), (2013, 1, 9)))
            assert(false === hasIntersection((2013, 1, 10), (2013, 1, 15), (2013, 1, 16), (2013, 1, 20)))
        }
    }
    test("sber + gazp combination")
    {
        //must output
        // SBER  | 4 | 2 | sell |  5,00 |  1,00 | 2 | 5 | buy  |  3,00 |  2,00 |  over GAZP  | 2 | 5 | sell |  5,00 |  1,00 | 2 | 3 | sell |  3,00 |  2,00 |  | 2010 |   32,18 |   -1,00 |   -6,65 | 2011 |   20,46 |  -15,30 |  -12,00 | 2012 |   44,33 |   -7,62 |   -8,78 | 2013 |   50,95 |   -1,31 |   -7,00
        // GAZP  | 2 | 5 | sell |  5,00 |  1,00 | 2 | 3 | sell |  3,00 |  2,00 |  over SBER  | 4 | 2 | sell |  5,00 |  1,00 | 2 | 5 | buy  |  3,00 |  2,00 |  | 2010 |   43,56 |   -0,50 |   -8,65 | 2011 |   14,21 |  -16,90 |  -10,00 | 2012 |   34,37 |  -11,64 |   -9,60 | 2013 |   52,80 |    0,00 |   -9,00

        new VolatileDaysCombinationStatisticalPrinter
        {

            println(getYearProfitsStatistics(CombinationElement("SBER", sell(1, 5), buy(2, 3), 4, 2, 2, 5), CombinationElement("GAZP", sell(1, 5), sell(2, 3), 2, 5, 2, 3)))
            println(getYearProfitsStatistics(CombinationElement("GAZP", sell(1, 5), sell(2, 3), 2, 5, 2, 3), CombinationElement("SBER", sell(1, 5), buy(2, 3), 4, 2, 2, 5)))

            override def isUsefulOutput(yps: Vector[YearProfit]): Boolean = true
        }
    }

    test("parseInput one simple input")
    {
        new VolatileDaysCombinationStatisticalPrinter
        {
            val parsedInput = parseInput(Array("SBER",
                "2 дня роста, 3 дня в op:sell p: 6,00 s: 2,00 и 2 дня падения, 7 дня в op:buy  p: 8,00 s: 5,00").iterator)
            assert(Map(("SBER", Vector(CombinationElement("SBER", sell(2, 6), buy(5, 8), 2, 3, 2, 7)))) === parsedInput)
        }
    }

    test("parseInput complex test")
    {
        new VolatileDaysCombinationStatisticalPrinter
        {
            val parsedInput = parseInput(Array("SBER",
                "2 дня роста, 3 дня в op:sell p: 6,00 s: 2,00 и 2 дня падения, 7 дня в op:buy  p: 8,00 s: 5,00",
                "3 дня роста, 7 дня в op:sell p: 8,00 s: 4,00 и 3 дня падения, 7 дня в op:buy  p: 7,00 s: 3,00 | 2010|013|018|   2,",
                "GAZP",
                "2 дня роста, 3 дня в op:sell p: 6,00 s: 2,00 и 2 дня падения, 5 дня в op:sell p: 6,00 s: 2,00 | 2010|032|037|  ",
                "2 дня роста, 3 дня в op:sell p: 6,00 s: 3,00 и 2 дня падения, 5 дня в op:sell p: 6,00 s: 2,00 |",
                "2 дня роста, 3 дня в op:sell p: 6,00 s: 5,00 и 2 дня падения, 5 дня в op:sell p: 6,00 s: 3,00"
            ).iterator)

            def checkCombinationElement(ticker: String, index: Int, op1: TradingOp, op2: TradingOp, op1cd: Int, op1pd: Int, op2cd: Int, op2pd: Int) =
                assert(CombinationElement(ticker, op1, op2, op1cd, op1pd, op2cd, op2pd) === parsedInput(ticker)(index))

            assert(2 === parsedInput("SBER").length)
            assert(3 === parsedInput("GAZP").length)
            checkCombinationElement("SBER", 0, sell(2, 6), buy(5, 8), 2, 3, 2, 7)
            checkCombinationElement("SBER", 1, sell(4, 8), buy(3, 7), 3, 7, 3, 7)
            checkCombinationElement("GAZP", 0, sell(2, 6), sell(2, 6), 2, 3, 2, 5)
            checkCombinationElement("GAZP", 1, sell(3, 6), sell(2, 6), 2, 3, 2, 5)
            checkCombinationElement("GAZP", 2, sell(5, 6), sell(3, 6), 2, 3, 2, 5)
        }
    }
}
