package tradingideas

import tradingsystems.{SimpleMovingAverage, TradingData}
import tradinganalyzers.TradingPosition

/**
 * @author alespuh
 * @since 25.07.13
 */

class WilliamsAdIdea(periodForMovingFunc: Int, positionDays: Int, daysAfter: Int, movingFunc: (Range, TradingData) => Double) extends TradingIdea
{
    def filterInterestingPositions(data: TradingData): Vector[TradingPosition] =
    {
        val williamsAdMovingFunc = data.movingFunc(periodForMovingFunc, r => movingFunc(r, data))
        val positionRange = 0 until positionDays
        (for(i <- periodForMovingFunc until data.length - positionDays - (daysAfter - 1) if data.williamsAD(i) == williamsAdMovingFunc(i))
            yield new TradingPosition(positionRange.map(_ + i + daysAfter).map(data(_)).toArray)).toVector
    }

    def desc: String = "Williams A/D " + List(periodForMovingFunc.formatted("%2d"), positionDays, daysAfter).mkString("|", "|", "|")
}

class WilliamsAdMaxBySmaRising(periodForMax: Int, positionDays: Int, daysAfter: Int, smaPeriod: Int) extends TradingIdea
{
    def filterInterestingPositions(data: TradingData): Vector[TradingPosition] =
    {
        val williamsAdMax = data.movingFunc(smaPeriod, r => r.map(data.williamsAD).max)
        val medianSma = SimpleMovingAverage(data, c => (c.open + c.close) / 2, smaPeriod).values
        val positionRange = 0 until positionDays
        (for(i <- periodForMax until data.length - positionDays - (daysAfter - 1)
            if data.williamsAD(i) == williamsAdMax(i) && medianSma(i) > medianSma(i - 1))
        yield new TradingPosition(positionRange.map(_ + i + daysAfter).map(data(_)).toArray)).toVector
    }

    def desc: String = "WsMax Sma↑ " + List(periodForMax.formatted("%2d"), positionDays, daysAfter, smaPeriod).mkString("|", "|", "|")
}

class WilliamsAdMaxBySmaFalling(periodForMax: Int, positionDays: Int, daysAfter: Int, smaPeriod: Int) extends TradingIdea
{
    def filterInterestingPositions(data: TradingData): Vector[TradingPosition] =
    {
        val williamsAdMax = data.movingFunc(smaPeriod, r => r.map(data.williamsAD).max)
        val medianSma = SimpleMovingAverage(data, c => (c.open + c.close) / 2, smaPeriod).values
        val positionRange = 0 until positionDays
        (for(i <- periodForMax until data.length - positionDays - (daysAfter - 1)
            if data.williamsAD(i) == williamsAdMax(i) && medianSma(i) < medianSma(i - 1))
        yield new TradingPosition(positionRange.map(_ + i + daysAfter).map(data(_)).toArray)).toVector
    }

    def desc: String = "WsMax Sma↓ " + List(periodForMax.formatted("%2d"), positionDays, daysAfter, smaPeriod).mkString("|", "|", "|")
}


class WilliamsAdMinBySmaRising(periodForMin: Int, positionDays: Int, daysAfter: Int, smaPeriod: Int) extends TradingIdea
{
    def filterInterestingPositions(data: TradingData): Vector[TradingPosition] =
    {
        val williamsAdMin = data.movingFunc(smaPeriod, r => r.map(data.williamsAD).min)
        val medianSma = SimpleMovingAverage(data, c => (c.open + c.close) / 2, smaPeriod).values
        val positionRange = 0 until positionDays
        (for(i <- periodForMin until data.length - positionDays - (daysAfter - 1)
            if data.williamsAD(i) == williamsAdMin(i) && medianSma(i) > medianSma(i - 1))
        yield new TradingPosition(positionRange.map(_ + i + daysAfter).map(data(_)).toArray)).toVector
    }

    def desc: String = "WsMin Sma↓ " + List(periodForMin.formatted("%2d"), positionDays, daysAfter, smaPeriod).mkString("|", "|", "|")
}

class WilliamsAdMinBySmaFalling(periodForMin: Int, positionDays: Int, daysAfter: Int, smaPeriod: Int) extends TradingIdea
{
    def filterInterestingPositions(data: TradingData): Vector[TradingPosition] =
    {
        val williamsAdMin = data.movingFunc(smaPeriod, r => r.map(data.williamsAD).min)
        val medianSma = SimpleMovingAverage(data, c => (c.open + c.close) / 2, smaPeriod).values
        val positionRange = 0 until positionDays
        (for(i <- periodForMin until data.length - positionDays - (daysAfter - 1)
            if data.williamsAD(i) == williamsAdMin(i) && medianSma(i) < medianSma(i - 1))
        yield new TradingPosition(positionRange.map(_ + i + daysAfter).map(data(_)).toArray)).toVector
    }

    def desc: String = "WsMin Sma↓ " + List(periodForMin.formatted("%2d"), positionDays, daysAfter, smaPeriod).mkString("|", "|", "|")
}

case class WilliamsAdMax(periodForMax: Int, positionDays: Int, daysAfter: Int) extends
    WilliamsAdIdea(periodForMax, positionDays, daysAfter, (r, d) => r.map(d.williamsAD).max)
{
    override def desc: String = "Max " + super.desc
}

case class WilliamsAdMin(periodForMin: Int, positionDays: Int, daysAfter: Int) extends
    WilliamsAdIdea(periodForMin, positionDays, daysAfter, (r, d) => r.map(d.williamsAD).min)
{
    override def desc: String = "Min " + super.desc
}