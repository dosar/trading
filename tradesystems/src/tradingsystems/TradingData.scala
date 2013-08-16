package tradingsystems

import scala.math._
import util.TradingImplicits.{toDoubleImplicits, toAnyImplicits}
import scala.collection.mutable

/**
 * @author alespuh
 * @since 08.07.13
 */
case class TradingData(data: Vector[Candle], ticker: String)
{
    def this(anotherData: TradingData) = this(anotherData.data, anotherData.ticker)
    def apply(index: Int) = data(index)

    type MovingRange = Range

    def movingFunc(period: Int, func: MovingRange => Double): Vector[Double] =
    {
        for(candleInd <- (0 until data.length).toVector)
            yield func(max(candleInd - (period - 1), 0) to candleInd)
    }

    def desc = ticker
    lazy val length = data.length
    lazy val atr14: Vector[Double] = AverageTrueRange(this).values
    lazy val williamsAD: Vector[Double] = WilliamsAD(this).values
}

// в курсе он рассказывает о другой стратегии, берем 14 дневное среднее из цен close - open
// и если оно растет а цена падает, значит скоро разворот
// и наоборот если оно падает а цена растет значит скоро разворот
// фракталы из 3 свечек. это когда вокруг свечи лоу выше слева и справа или хаи ниже слева и справа. это краткосрочные хаи и лоу
// средне срочные хаи это когда слева и справа краткосрочные хаи меньше
// внутренние свечи - зло, не участвуют в определении краткосорочных лоу и хаев

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
        else if(candles(i).close < candles(i - 1).close) candles(i).close - trueRangeHigh(i)
        else 0.0

    def trueRangeHigh(i: Int) = max(candles(i).high, candles(i - 1).close)
    def trueRangeLow(i: Int) = min(candles(i).low, candles(i - 1).close)
}

case class SimpleMovingAverage(data: Vector[Double], period: Int = 14)
{
    val values: Vector[Double] = for(ind <- (0 until data.length).toVector) yield
    {
        val range = max(ind - (period - 1), 0) to ind
        range.map(data).sum / range.length
    }
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