package ideas

import org.scalatest.FunSuite
import logic.TestUtils
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import tradingsystems.TradingData
import tradingideas.SignalCandles
import tradinganalyzers.{TradingOp, TradingPositionAnalyzer}
import tradinganalyzers.statistics.{StandardImporter, YearProfitStatistics}

/**
 * @author alespuh
 * @since 19.07.13
 */
@RunWith(classOf[JUnitRunner])
class Stocks1DayCandlesSignalDays_Test extends FunSuite with TestUtils
{
    type Stop = Double; type TakeProfit = Double
    def bruteForceSignal(signal: Vector[Int], positionRange: Range, data: TradingData,
        tradeOp: (Stop, TakeProfit) => TradingOp, target: Double)
    {
        val positions = new SignalCandles(signal, positionRange).filterInterestingPositions(data).toArray
        val prefix = signal.mkString("|", "|", "|") + (positionRange.min + "-" + positionRange.max) + "|"
        for(stop <- 1 to 5; takeProfit <- 8 to 20) //для разных стопов и профитов проверяем годовой профит
        {
            //берем статистику, и если она достаточно хороша отображаем
            val op = tradeOp(stop, takeProfit)
            val statistics = new TradingPositionAnalyzer(positions.map((_, op))).getStatistics
            val stat = new YearProfitStatistics(statistics).compactStat(target, prefix + "s:" + stop + "p:" + takeProfit)
            if(stat != null)
                println(stat)
        }
    }

    test("SBER -1|-1|-1       1-6, 1-7 на покупку 2-4, 2-6, 2-7 на покупку")
    {
        val signal = Vector(-1, -1, -1)
        bruteForceSignal(signal, 1 to 6, StandardImporter.importSber, TradingOp.buy, 18)
        bruteForceSignal(signal, 1 to 7, StandardImporter.importSber, TradingOp.buy, 18)
        bruteForceSignal(signal, 2 to 4, StandardImporter.importSber, TradingOp.buy, 0)
        bruteForceSignal(signal, 2 to 6, StandardImporter.importSber, TradingOp.buy, 7)
        bruteForceSignal(signal, 2 to 7, StandardImporter.importSber, TradingOp.buy, 3)
    }

    test("SBER 1|1|-1|-1       3-3 на продажу, 3-7 на покупку")
    {
        val signal = Vector(1, 1, -1, -1)
        bruteForceSignal(signal, 3 to 3, StandardImporter.importSber, TradingOp.sell, 0)
        bruteForceSignal(signal, 3 to 7, StandardImporter.importSber, TradingOp.buy, 1)
        bruteForceSignal(signal, 1 to 3, StandardImporter.importSber, TradingOp.buy, 0)
        bruteForceSignal(Vector(-1, -1, 1, 1), 1 to 4, StandardImporter.importSber, TradingOp.sell, 0)

    }

    test("SBER -1|-1|-1|-1    1-3, 1-7 на покупку")
    {
        val signal = Vector(-1, -1, -1, -1)
//        слишком мало сигналов для 2013
        bruteForceSignal(signal, 1 to 3, StandardImporter.importSber, TradingOp.buy, 2)
        bruteForceSignal(signal, 1 to 7, StandardImporter.importSber, TradingOp.buy, 2)
    }

    test("SBER -1|-1|1|-1     6-6, 6-7 на продажу")
    {
        val signal = Vector(-1, -1, 1, -1)
        // странный сигнал какой-то
        bruteForceSignal(signal, 6 to 6, StandardImporter.importSber, TradingOp.sell, 0)
        bruteForceSignal(signal, 6 to 7, StandardImporter.importSber, TradingOp.sell, 5)
    }

    test("ROSN -1|-1|1|-1     2-2, 4-4, 4-5 на покупку")
    {
        val signal = Vector(-1, -1, 1, -1)
        bruteForceSignal(signal, 2 to 2, StandardImporter.importRosn, TradingOp.buy, 0)
        bruteForceSignal(signal, 4 to 4, StandardImporter.importRosn, TradingOp.buy, 0)
        bruteForceSignal(signal, 4 to 5, StandardImporter.importRosn, TradingOp.buy, 0)
    }

    test("LKOH -1|-1|1|1     5-7 на покупку")
    {
        bruteForceSignal(Vector(-1, -1, 1, 1), 5 to 7, StandardImporter.importLkoh, TradingOp.buy, 0)
    }

    test("LKOH 1|-1|-1|-1    2-5 на покупку")
    {
        bruteForceSignal(Vector(1, -1, -1, -1), 2 to 5, StandardImporter.importLkoh, TradingOp.buy, 3)
    }

    test("LKOH 1|-1|1|-1     1-7, 3-4 на покупку")
    {
        val signal = Vector(1, -1, 1, -1)
        bruteForceSignal(signal, 1 to 7, StandardImporter.importLkoh, TradingOp.buy, 0)
        bruteForceSignal(signal, 3 to 4, StandardImporter.importLkoh, TradingOp.buy, 1)
    }

    test("GMKN 1|1|-1|-1      3-7, 4-7 на покупку")
    {
        val signal = Vector(1, 1, -1, -1)
        bruteForceSignal(signal, 3 to 7, StandardImporter.importGmkn, TradingOp.buy, 0)
        bruteForceSignal(signal, 4 to 7, StandardImporter.importGmkn, TradingOp.buy, 0)
    }

    test("GMKN -1|-1|-1|-1    1-4 на покупку")
    {
        val signal = Vector(-1, -1, -1, -1)
        bruteForceSignal(signal, 1 to 4, StandardImporter.importGmkn, TradingOp.buy, 0)
    }

    test("GAZP -1|-1|-1|-1    1-6 на покупку")
    {
        val signal = Vector(-1, -1, -1, -1)
        bruteForceSignal(signal, 1 to 6, StandardImporter.importGazp, TradingOp.buy, 0)
    }

    test("нужно оттестировать сигналы и торговые позиции")
    {
// индексы 1-based
// по сберу
// -1|-1|-1       1-6, 1-7 на покупку
//                2-4, 2-6, 2-7 на покупку
// 1|1|-1|-1      3-3 на продажу
//                3-7 на покупку
// -1|-1|-1|-1    1-3, 1-7 на покупку
//                2-2, 2-4, 2-5, 2-6, 2-7 на покупку
// -1|-1|1|-1     6-6, 6-7 на продажу
// по роснефти
// -1|1|-1|-1     2-2 на покупку
//                4-4, 4-5 на покупку
// по лукойлу
// -1|-1|1|1      5-7 на покупку
// 1|-1|-1|-1     2-5 на покупку
// 1|-1|1|-1      1-7 на покупку
//                3-4 на покупку
// по норникелю
// 1|1|-1|-1      3-7 на покупку
//                4-7 на покупку
// -1|-1|-1|-1    1-4 на покупку
// по газпрому
// -1|-1|-1|-1    1-6 на покупку
    }
}
