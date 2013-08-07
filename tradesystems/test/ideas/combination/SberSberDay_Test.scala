package ideas.combination

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import tradinganalyzers.statistics.{StrategyElement, StrategiesCombinator, DayStandardImporter}
import tradingideas._
import tradinganalyzers.statistics.StrategyElement

class SimpleTest(positiveDaysCount: Vector[Int], positiveDaysPosition: Vector[Int],
    negativeDaysCount: Vector[Int], negativeDaysPosition: Vector[Int],
    periodForMaxVector: Vector[Int], positionForMaxVector: Vector[Int], afterDaysForMaxVector: Vector[Int],
    periodForMinVector: Vector[Int], positionForMinVector: Vector[Int], afterDaysForMinVector: Vector[Int])
{
    def combine()
    {
        val sber = DayStandardImporter.sber
        //на каждом for'е фильтруем дни
        //во внутреннем цикле переставляем дни
        //перебираем операции
        for(positiveDay <- positiveDaysCount; positivePosition <- positiveDaysPosition;
            negativeDay <- negativeDaysCount; negativePosition <- negativeDaysPosition;
            periodForMax <- periodForMaxVector; positionForMax <- positionForMaxVector; afterDaysForMax <- afterDaysForMaxVector;
            periodForMin <- periodForMinVector; positionForMin <- positionForMinVector; afterDaysForMin <- afterDaysForMinVector)
        {
            val positive = new PositiveTrendCandles(positiveDay, positivePosition)
            val negative = new NegativeTrendCandles(negativeDay, negativePosition)
            val williamsMax = new WilliamsAdMax(periodForMax, positionForMax, afterDaysForMax)
            val williamsMin = new WilliamsAdMin(periodForMin, positionForMin, afterDaysForMin)
            new StrategiesCombinator{override lazy val targetProfit = 70}.rearrangeStrategiesOps(
                StrategyElement(sber, positive, negative, williamsMax, williamsMin))
        }
    }
}

/**
 * @author alespuh
 * @since 29.07.13
 */
@RunWith(classOf[JUnitRunner])
class SberSberDay_Test extends FunSuite
{
    test("pos/neg days + williams ad")
    {
        new SimpleTest((2 to 3).toVector, (3 to 5).toVector, (2 to 2).toVector, (5 to 7).toVector,
            Vector(3, 6, 12), Vector(3, 4, 5, 6, 7), Vector(1, 2, 3),
            Vector(3, 6, 12), Vector(3, 4, 5, 6, 7), Vector(1, 2, 3)).combine()
    }
}