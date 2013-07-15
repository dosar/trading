package featuresextractors

import tradingsystems.{Candle, TradingData}
import scala.Array

/**
 * @author alespuh
 * @since 15.07.13
 */
class SimpleFeatureExtractor
{
    def buyProfit(c: Candle) = if(c.buyProfit > 0) 1 else 0

    //1 step. linear regression predicting close - open
    //2 step. feature set. for 10 days before
    // day of week,
    // week of year
    // month of year
    // close - open,
    // volume
    def generateFor(data: TradingData): Array[Array[Double]] =
    {
//        Array.empty
        val candles = data.data
        val result = for(index <- previousDaysCount until candles.length - 1)
            yield
        {
            val previousDays = for(dayIndex <- index - previousDaysCount to index - 1; day = candles(dayIndex))
                yield Array(day.date.getDayOfWeek, day.date.getWeekOfWeekyear, day.date.getMonthOfYear, day.buyProfit, day.volume)
            previousDays.flatten.toArray :+ candles(index).buyProfit
        }
        result.toArray
    }

    private final val previousDaysCount = 26
}
