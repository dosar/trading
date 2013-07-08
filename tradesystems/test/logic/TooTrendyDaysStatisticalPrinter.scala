package logic

import tradinganalyzers.TradingOp
import tradinganalyzers.TradingOp._
import tradingideas.TradingIdea
import tradingsystems.Candle

trait TooTrendyDaysStatisticalPrinter extends AnalyticalStatisticsPrinter
{
    import TradingIdea._
    def standardTest(initialStopPercent: Double = 1, initialTakeProfitPercent: Double = 1)
    {
        println(ticker)
        import TradingOp._
        val tp = initialTakeProfitPercent
        val sp = initialStopPercent
        val ops = List((buy _, buy _), (buy _, sell _), (sell _, buy _), (sell _, sell _))
        val input = for(op1Days <- 2 to 5; op2Days <- 2 to 5; (op1f, op2f) <- ops;
            op1Stop <- (1 to 5).map(ind => if(sp - ind > 0) sp - ind else sp / ind);
            op1TakeProfit <- List(tp, tp * 2, tp * 3, tp * 4, tp * 5);
            op2Stop <- (1 to 5).map(ind => if(sp - ind > 0) sp - ind else sp / ind);
            op2TakeProfit <- List(tp, tp * 2, tp * 3, tp * 4, tp * 5);
            op1 = op1f(op1Stop, op1TakeProfit); op2 = op2f(op2Stop, op2TakeProfit))
        yield (op1Days, op1, op1Stop, op1TakeProfit, op2Days, op2, op2Stop, op2TakeProfit)

        val statistics = for((op1Days, op1, op1Stop, op1TakeProfit, op2Days, op2, op2Stop, op2TakeProfit) <- input.par)
        yield
        {
            val tooPositive = (tooTrendyCandles(data, _.buyProfit > 0, op1Days), op1)
            val tooNegative = (tooTrendyCandles(data, _.sellProfit > 0, op2Days), op2)
            val op1Desc = op1.desc(op1Days)
            val op2Desc = op2.desc(op2Days)
            getStringStatistics(op1Desc + "||" + op2Desc, tooPositive, tooNegative)
        }
        statistics.filter(_ != null).toList.sortBy(_.take(48)).foreach(println)
    }

    def detailedTest(stop: Double = 1, takeProfit: Double = 1, positiveDays: Int = 2, negativeDays: Int = 3)
    {
        println(ticker)
        val tooPositive = (tooTrendyCandles(data, _.buyProfit > 0, positiveDays), TradingOp.sell(stop, takeProfit))
        val tooNegative = (tooTrendyCandles(data, _.sellProfit > 0, negativeDays), TradingOp.buy(stop, takeProfit))
        analyzeIdea(tooPositive, tooNegative)
    }
}
