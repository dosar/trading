package tradingideas

import tradingsystems.TradingData
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

class WilliamsAdMax(periodForMax: Int, positionDays: Int, daysAfter: Int) extends
    WilliamsAdIdea(periodForMax, positionDays, daysAfter, (r, d) => r.map(d.williamsAD).max)
{
    override def desc: String = "Max " + super.desc
}

class WilliamsAdMin(periodForMin: Int, positionDays: Int, daysAfter: Int) extends
    WilliamsAdIdea(periodForMin, positionDays, daysAfter, (r, d) => r.map(d.williamsAD).min)
{
    override def desc: String = "Min " + super.desc
}