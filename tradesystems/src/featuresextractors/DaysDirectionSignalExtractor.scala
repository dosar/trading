package featuresextractors

import tradingsystems.{Candle, TradingData}
import scala.collection.immutable.IndexedSeq
import util.TradingImplicits.toVectorImplicits

//positive - число позитивных движений в рамках тренда
//negative - число негативных движений в рамках тренда
case class TrendStat(positive: Double, negative: Double)
{
    def sumGreater(limit: Int) = total > limit

    lazy val total = positive + negative

    lazy val ratio = if(positive > negative) positive / total else -negative / total

    override lazy val toString = ratio.formatted("%5.2f")
}

//signal - массив из направлений свечей, 1 если рынок вверх и -1 наоборот
//statistics - массив массивов статистики по количеству положительности / отрицательности трендов после сигнала
//каждый внутренний массив это тренды
//начиная с (индекс внешнего массива) дня и до текущего (индекс во внутреннем массиве) дня
case class SignalStatistics(signal: Vector[Int], statistics: Vector[Vector[TrendStat]])
{
    lazy val trendFrom1DayStats = statistics(0)
    lazy val trendFrom2DayStats = statistics(1)
    lazy val trendFrom3DayStats = statistics(2)
    lazy val trendFrom4DayStats = statistics(3)
    lazy val trendFrom5DayStats = statistics(4)
    lazy val trendFrom6DayStats = statistics(5)
    lazy val trendFrom7DayStats = statistics(6)
}

/**
 * @author alespuh
 * @since 18.07.13
 */
//  считаем тренд до 7 дней включительно, в сигнале используем до 11 дней
//  фильтруем те, у которых точноть ниже 70%
class DaysDirectionSignalExtractor(data: TradingData)
{
    final val maxTrendLength = 7
    final val signalLengthRange = 2 to 11
    final val trendLengthRange = 0 until maxTrendLength
    lazy val candles = data.data

    def getSignalStatistics: Vector[SignalStatistics] =
    {
        val candlesLength = candles.length
        (for(ind <- 0 until candlesLength) yield
        {
            val trendStatistics = getTrendStatistics(candles, ind)
            for(signalLength <- signalLengthRange if ind - signalLength >= 0) yield
                (getSignal(candles, ind, signalLength), trendStatistics)
        }).flatten.groupBy{case (signal, _) => signal}
            .map{case (signal, groupedTrends) => (signal, sum(groupedTrends.map(_._2)))}
            .toVector.map{case (signal, statistics) => SignalStatistics(signal, statistics)}.sortBy(_.signal.length)
    }

    def sum(trends:IndexedSeq[Vector[Vector[(Int, Int)]]]): Vector[Vector[TrendStat]] =
    {
        def addStatistics(left: (Int, Int), right: (Int, Int)) = (left._1 + right._1, left._2 + right._2)

        trends.foldLeft[Vector[Vector[(Int, Int)]]](Vector.empty)
        {
            (acc, curr) => acc.sumWith(curr, _.sumWith(_, addStatistics, (0, 0)), Vector.empty)
        }.map(_.map{case (positive, negative) => TrendStat(positive, negative)})

//        for(trendLength <- trendLengthRange) yield
//            trends.map(trend => if(trendLength < trend.length) trend(trendLength) else (0, 0)).foldLeft((0, 0))(addStatistics)
    }

    def getSignal(candles: Vector[Candle], currentIndex: Int, signalLength: Int): Vector[Int] =
        (currentIndex - signalLength until currentIndex).map(candles.apply).map(c => if(c.buyProfitPct > 0) 1 else -1).toVector

    //собираем +/- по трендам начиная со startIndex
    def getTrendStatistics(candles: Vector[Candle], startIndex: Int): Vector[Vector[(Int, Int)]] =
    {
        for(trendLength <- trendLengthRange.toVector; currentIndex = startIndex + trendLength if currentIndex < candles.length) yield
        {
            val origin = candles(currentIndex)
            (0 until maxTrendLength - trendLength).map(_ + currentIndex).filter(_ < candles.length)
                .map(trendIndex => if (candles(trendIndex).close - origin.open > 0) (1, 0) else (0, 1)).toVector
        }
    }
}