package exports

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import logic.TestUtils
import tradinganalyzers.statistics.{StrategyIdeaData, YearProfitStatistics}
import tradinganalyzers.statistics.StandardImporter._
import tradinganalyzers.TradingOp._
import tradingideas.{NegativeTrendCandles, WilliamsAdMin, PositiveTrendCandles, WilliamsAdMax}

/**
 * @author alespuh
 * @since 06.08.13
 */
@RunWith(classOf[JUnitRunner])
class ExportIdeaProfitGraphic_Test extends FunSuite with TestUtils
{
    test("64% williams vs trend")
    {
        YearProfitStatistics.exportData((StrategyIdeaData(importSber, new WilliamsAdMax(3, 3, 2)), sell(4, 9)),
            (StrategyIdeaData(importRtkm, new PositiveTrendCandles(4, 3)), sell(4, 9)),
            (StrategyIdeaData(importSber, new WilliamsAdMin(3, 7, 2)), buy(4, 9)),
            (StrategyIdeaData(importRtkm, new NegativeTrendCandles(2, 4)), buy(4, 9)))
    }

    test("60% trend vs trend")
    {
// |SBER|2 дня роста, 3 дня в op:sell p:11,00 s: 4,00|NVTK|2 дня роста, 5 дня в op:sell p:11,00 s: 4,00
// |SBER|2 дня падения, 7 дня в op:buy p:11,00 s: 4,00|NVTK|3 дня падения, 7 дня в op:buy p:11,00 s: 4,00
        YearProfitStatistics.exportData((StrategyIdeaData(importSber, new PositiveTrendCandles(2, 3)), sell(4, 11)),
            (StrategyIdeaData(importNvtk, new PositiveTrendCandles(2, 5)), sell(4, 11)),
            (StrategyIdeaData(importSber, new NegativeTrendCandles(2, 7)), buy(4, 11)),
            (StrategyIdeaData(importNvtk, new NegativeTrendCandles(3, 7)), buy(4, 11)))
    }

    test("62% williams vs williams")
    {
// |SBER|Min Williams A/D | 3|7|2|op:buy p:13,00 s: 4,00|GAZP|Max Williams A/D |15|4|2|op:sell p:13,00 s: 4,00
// |SBER|Max Williams A/D | 3|3|2|op:sell p:13,00 s: 4,00|GAZP|Min Williams A/D | 3|5|1|op:buy p:13,00 s: 4,00
        YearProfitStatistics.exportData((StrategyIdeaData(importSber, new WilliamsAdMin(3, 7, 2)), buy(4, 13)),
            (StrategyIdeaData(importGazp, new WilliamsAdMax(15, 4, 2)), sell(4, 13)),
            (StrategyIdeaData(importSber, new WilliamsAdMax(3, 3, 2)), sell(4, 13)),
            (StrategyIdeaData(importNvtk, new WilliamsAdMin(3, 5, 1)), buy(4, 13)))
    }
}
