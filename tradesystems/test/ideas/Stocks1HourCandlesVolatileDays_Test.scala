package ideas

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import tradinganalyzers.TradingPositionAnalyzer
import tradinganalyzers.TradingOp._
import tradingideas._
import tradinganalyzers.statistics.{HourStandardImporter, VolatileDaysStatisticalPrinter}
import tradingsystems.{YearProfit, TradingData}

@RunWith(classOf[JUnitRunner])
class Stocks1HourCandlesVolatileDays_Test extends FunSuite
{
    class SimpleTest(val ticker: String, override val targetProfit: Double = 25) extends VolatileDaysStatisticalPrinter
    {
        override lazy val data: TradingData = HourStandardImporter.standardImport(ticker)

        override def isUsefulOutput(yearProfits: Vector[YearProfit]): Boolean =
            super.isUsefulOutput(yearProfits) && yearProfits.forall(yp => yp.worstMonthSlumpPct >= -7 && yp.yearSlumpPct >= -7)
    }

    test("sberbank 1 hour") { new SimpleTest("SBER", 34).standardTestSP(Vector(0.5, 1, 2), Vector(5, 8, 13)) }

    test("gazprom 1 hour") { new SimpleTest("GAZP", 25).standardTestSP(Vector(0.5, 1, 2), Vector(5, 8, 13)) }

    test("nornikel 1 hour") { new SimpleTest("GMKN", 15).standardTestSP(Vector(0.5, 1, 2), Vector(5, 8, 13)) }

    test("lukoil 1 hour") { new SimpleTest("LKOH", 15).standardTestSP(Vector(0.5, 1, 2), Vector(5, 8, 13)) }

    test("novatek 1 hour") { new SimpleTest("NVTK", 22).standardTestSP(Vector(0.5, 1, 2), Vector(5, 8, 13)) }

    test("rosneft 1 hour") { new SimpleTest("ROSN", 26).standardTestSP(Vector(0.5, 1, 2), Vector(5, 8, 13)) }

    test("rostelecom 1 hour") { new SimpleTest("RTKM", 24).standardTestSP(Vector(0.5, 1, 2), Vector(5, 8, 13)) }
}
