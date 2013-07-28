package tradingsystems

import scala.math._
import util.TradingImplicits.{toDoubleImplicits, toAnyImplicits}
import scala.collection.mutable

/**
 * @author alespuh
 * @since 08.07.13
 */
case class TradingData(data: Vector[Candle])
{
    def this(anotherData: TradingData) = this(anotherData.data)
    def apply(index: Int) = data(index)

    type MovingRange = Range

    def movingFunc(period: Int, func: MovingRange => Double): Vector[Double] =
    {
        for(candleInd <- (0 until data.length).toVector)
            yield func(max(candleInd - (period - 1), 0) to candleInd)
    }

    lazy val length = data.length
    lazy val atr14: Vector[Double] = AverageTrueRange(this, 14).values
    lazy val williamsAD: Vector[Double] = WilliamsAD(this).values
}

case class WilliamsAD(candles: TradingData)
{
    val values: Vector[Double] = candles.data.zipWithIndex.drop(1).foldLeft(mutable.Queue(0.0))
    {
        case(queue, (_, ind)) =>
            val previous = queue.last
            queue :+ (accumulationDistribution(ind) + previous)
    }.map(_.roundP(2)).toVector

    def accumulationDistribution(i: Int) =
        if(candles(i).close > candles(i - 1).close) candles(i).close - trueRangeLow(i)
        else if(candles(i).close < candles(i - 1).close) candles(i).close - trueRangeHigh (i)
        else 0.0

    def trueRangeHigh(i: Int) = max(candles(i).high, candles(i - 1).close)
    def trueRangeLow(i: Int) = min(candles(i).low, candles(i - 1).close)
}

case class AverageTrueRange(data: TradingData, period: Int = 14)
{
    val values: Vector[Double] =
        data.movingFunc(period, trRange => trRange.map(trueRange).sum / trRange.length)//считаем среднее за 14 дней

    private def trueRange(index: Int): Double =
    {
        val previous = if(index > 0) data(index - 1) else null
        val current = data(index)
        Vector(current.volatility,
            previous.mapOr(c => abs(current.high - c.close), 0.0),
            previous.mapOr(c => abs(current.low - c.close), 0.0)).max
    }
}