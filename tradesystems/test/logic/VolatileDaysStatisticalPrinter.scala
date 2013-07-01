package logic

import tradinganalyzers.TradingOp
import tradingideas.VolatileCandles
import tradingsystems.Candle

trait VolatileDaysStatisticalPrinter extends AnalyticalStatisticsPrinter
{
    val data: Vector[Candle]

    def standardTest(initialStopPercent: Int = 1, initialTakeProfitPercent: Int = 1)
    {
        import TradingOp._
        val tp = initialTakeProfitPercent
        val sp = initialStopPercent
        val input = for((op1f, op2f) <- Vector((buy _, buy _), (buy _, sell _), (sell _, buy _), (sell _, sell _));
            op1CheckDays <- 2 to 5; op1PositionDays <- 2 to 5;
            op2CheckDays <- 2 to 5; op2PositionDays <- 2 to 5;
            op1Stop <- initialStopPercent to initialStopPercent + 5;
            op1TakeProfit <- initialTakeProfitPercent to initialTakeProfitPercent + 5;
            op2Stop <- initialStopPercent to initialStopPercent + 5;
            op2TakeProfit <- initialTakeProfitPercent to initialTakeProfitPercent + 5;
            op1 = op1f(op1Stop, op1TakeProfit); op2 = op2f(op2Stop, op2TakeProfit))
        yield (op1CheckDays, op1PositionDays, op1, op1Stop, op1TakeProfit, op2CheckDays, op2PositionDays, op2, op2Stop, op2TakeProfit)

        val statistics = for((op1CheckDays, op1PositionDays, op1, op1Stop, op1TakeProfit, op2CheckDays, op2PositionDays, op2, op2Stop, op2TakeProfit) <- input.par)
        yield
        {
            val op1Candles = VolatileCandles(op1CheckDays, op1PositionDays, _.buyProfit > 0).filterInterestingDays(data)
            val op2Candles = VolatileCandles(op2CheckDays, op2PositionDays, _.sellProfit > 0).filterInterestingDays(data)
            val op1Desc = op1CheckDays + " дня роста, " + op1PositionDays + " дня в " + op1.desc
            val op2Desc = op2CheckDays + " дня падения, " + op2PositionDays + " дня в " + op2.desc
            Vector(getStringStatistics(op1Desc + " " * (3 + op2Desc.size), (op1Candles, op1)),
                getStringStatistics(op2Desc + " " * (3 + op1Desc.size), (op2Candles, op2)),
                getStringStatistics(op1Desc + " и " + op2Desc, (op1Candles, op1), (op2Candles, op2))).filter(_ != null)
        }
        val result = statistics.flatten.toList
        result.sortBy(_.take(48)).foreach(println)
    }
}