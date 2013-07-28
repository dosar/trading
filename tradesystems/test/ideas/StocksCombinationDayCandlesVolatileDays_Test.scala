package ideas

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import tradingsystems.YearProfit
import tradinganalyzers.statistics.VolatileDaysCombinationStatisticalPrinter
import tradinganalyzers.TradingPositionAnalyzer
import tradingideas.MergingFigureCandles

@RunWith(classOf[JUnitRunner])
class StocksCombinationDayCandlesVolatileDays_Test extends FunSuite with VolatileDaysCombinationStatisticalPrinter
{
    override val targetProfit: Double = 0
    override val strategiesFile: String = "trading_stabile_ideas.txt"

    test("brute force all two strategies combinations"){ test2StrategiesCombination() }

    test("brute force all three strategies combinations"){ test3StrategiesCombination() }

    test("sber volatile vs merging strategies")
    {
        import tradinganalyzers.TradingOp._
        val strategy1 = CombinationElement("SBER", sell(3, 8), buy(2, 8), 2, 5, 2, 7)
        val strategy2 = new CombinationElement("SBER", buy(4, 6), sell(1, 8), 3, 4, 2, 4)
        {
            override def getPositionAnalyzer: TradingPositionAnalyzer = new TradingPositionAnalyzer(Vector(
                (new MergingFigureCandles(op1CheckDays, op1PositionDays, 1, _.buyProfit > 0)
                    .filterInterestingPositions(data(ticker)), op1),
                (new MergingFigureCandles(op2CheckDays, op2PositionDays, 1, _.sellProfit > 0)
                    .filterInterestingPositions(data(ticker)), op2)))
        }
        println(getYearProfitsStatistics(strategy1, strategy2))
        println(getYearProfitsStatistics(strategy2, strategy1))
    }

    test("sber volatile vs gmkn merging strategies")
    {
        import tradinganalyzers.TradingOp._
        val strategy1 = CombinationElement("SBER", sell(3, 8), buy(2, 8), 2, 5, 2, 7)
        val strategy2 = new CombinationElement("GMKN", buy(4, 4), sell(4, 8), 2, 6, 2, 5)
        {
            override def getPositionAnalyzer: TradingPositionAnalyzer = new TradingPositionAnalyzer(Vector(
                (new MergingFigureCandles(op1CheckDays, op1PositionDays, 1, _.buyProfit > 0)
                    .filterInterestingPositions(data(ticker)), op1),
                (new MergingFigureCandles(op2CheckDays, op2PositionDays, 1, _.sellProfit > 0)
                    .filterInterestingPositions(data(ticker)), op2)))
        }
        println(getYearProfitsStatistics(strategy1, strategy2))
        println(getYearProfitsStatistics(strategy2, strategy1))
    }
}
