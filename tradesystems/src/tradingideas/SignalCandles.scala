package tradingideas

import tradingsystems.TradingData
import tradinganalyzers.TradingPosition

/**
 * @author alespuh
 * @since 25.07.13
 */
class SignalCandles(signal: Vector[Int], positionRange: Range) extends TradingIdea
{
    override val desc: String = signal.mkString("(", ",", ")")
    lazy val reversedSignal = signal.reverse
    lazy val maxDayNumber = positionRange.max

    def filterInterestingPositions(list: TradingData): Vector[TradingPosition] =
    {
        val candles = list.data
        val signalLength = signal.length
        val checkRange = 0 until signalLength

        def checkPreDaysCond(index: Int): Boolean =
        {
            for(checkInd <- checkRange)
            {
                if(reversedSignal(checkInd) > 0 && candles(index - checkInd).buyProfitPct <= 0)
                    return false
                if(reversedSignal(checkInd) < 0 && candles(index - checkInd).buyProfitPct > 0)
                    return false
            }
            true
        }

        val result = candles.zipWithIndex.slice(reversedSignal.length - 1, candles.size - maxDayNumber)
            .filter{case (_, index) => checkPreDaysCond(index)}
            .map{case (_, index) => new TradingPosition(positionRange.map(posInd => candles(index + posInd)).toArray)}
        result.toVector
    }
}
