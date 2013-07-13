package tradinganalyzers.statistics

import tradinganalyzers.{TradingPosition, TradingPositionAnalyzer, TradingOp}
import tradingideas.{NegativeTrendCandles, PositiveTrendCandles, LongTrendCandles}
import tradingsystems.Candle

trait VolatileDaysStatisticalPrinter extends AnalyticalStatisticsPrinter
{
    val oneStrategyDays = for(checkDays <- 2 to 7; positionDays <- 2 to 7) yield (checkDays, positionDays)
    val twoStrategiesDays =
        for((op1CheckDays, op1PositionDays) <- oneStrategyDays; (op2CheckDays, op2PositionDays) <- oneStrategyDays)
            yield (op1CheckDays, op1PositionDays, op2CheckDays, op2PositionDays)

    def positiveCandle(c: Candle): Boolean = c.buyProfit > 0
    def negativeCandle(c: Candle): Boolean = c.sellProfit > 0

    def standardTest(stopMultiplierSteps: Int = 5, stopMultiplierStep: Double = 0.1, takeProfitStart: Int = 3)
    {
        println(ticker)

        val takeProfitRange = takeProfitStart to takeProfitStart + 5
        val stopRange = (1 to stopMultiplierSteps).map(_ * stopMultiplierStep)
        val statistics = twoStrategiesStatistics(stopRange, takeProfitRange) ++
            oneStrategyStatistics(stopRange, takeProfitRange, positiveCandle, "роста", " " * 30) ++
            oneStrategyStatistics(stopRange, takeProfitRange, negativeCandle, "падения", " " * 27)

        val result = statistics.flatten.toList
        result.sortBy(_.take(48)).foreach(println)
    }

    import TradingOp._

    def oneStrategyStatistics(stopRange: IndexedSeq[Double], takeProfitRange: Range,
        checkDaysCondition: (Candle) => Boolean, checkConditionDescription: String, opDescPostfix: String) =
    {
        for((checkDays, daysInPosition) <- oneStrategyDays.par) yield
        {
            val positionDays = new LongTrendCandles(checkDays, daysInPosition, checkDaysCondition).filterInterestingDays(data)
            (for(opf <- Array(sell _, buy _); stop <- stopRange; takeProfit <- takeProfitRange) yield
            {
                val op = opf(stop, takeProfit)
                val opDesc = checkDays + " дня " + checkConditionDescription + ", " + daysInPosition + " дня в " + op.desc
                getStringStatistics(opDesc + opDescPostfix, getYearProfits(positionDays.map((_, op))))

            }).filter(_ != null).toArray
        }
    }

    def twoStrategiesStatistics(stopRange: IndexedSeq[Double], takeProfitRange: Range.Inclusive) =
    {
        for((op1CheckDays, op1PositionDays, op2CheckDays, op2PositionDays) <- twoStrategiesDays.par)
        yield
        {
            val daysOp1 = new LongTrendCandles(op1CheckDays, op1PositionDays, positiveCandle).filterInterestingDays(data)
                .zipAll(Vector(), null, 1)
            val daysOp2 = new LongTrendCandles(op2CheckDays, op2PositionDays, negativeCandle).filterInterestingDays(data)
                .zipAll(Vector(), null, 2)
            val allDaysOp = (daysOp1 ++ daysOp2).sortBy(_._1.positionDate.toDate)

            val result = (for((op1f, op2f) <- Array((sell _, buy _), (sell _, sell _), (buy _, buy _), (buy _, sell _));
                op1Stop <- stopRange; op1TakeProfit <- takeProfitRange;
                op2Stop <- stopRange; op2TakeProfit <- takeProfitRange) yield
            {
                val op1 = op1f(op1Stop, op1TakeProfit)
                val op2 = op2f(op2Stop, op2TakeProfit)
                val op1Desc = op1CheckDays + " дня роста, " + op1PositionDays + " дня в " + op1.desc
                val op2Desc = op2CheckDays + " дня падения, " + op2PositionDays + " дня в " + op2.desc
                val yearProfits = getYearProfits(allDaysOp.map{case (position, opType) => (position, if(opType == 1) op1 else op2)})
                getStringStatistics(op1Desc + " и " + op2Desc, yearProfits)
            }).filter(_ != null)
            result
        }
    }

    def getYearProfits(tradingPositionOps: Vector[(TradingPosition, TradingOp)]) =
        new TradingPositionAnalyzer(data, tradingPositionOps).getStatistics
}