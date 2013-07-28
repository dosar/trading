package ideas.williamsad

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import tradinganalyzers.statistics.VolatileDaysStatisticalPrinter
import tradingideas.{WilliamsAdMin, WilliamsAdMax, TradingIdea}
import scala.collection.immutable.Range.Inclusive

/**
 * @author alespuh
 * @since 25.07.13
 */
@RunWith(classOf[JUnitRunner])
class Stocks1DayCandlesWilliamsAdIdea_Test extends FunSuite
{
    //сделать перебор по daysAfter для минимума и максимума, потому что совсем необязательна симметрия
    //еще по дням для определения максимума/минимума индикатора
    test("GAZP on 1st day"){ new SimpleTest("GAZP", 24, 1, 1).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
    test("GAZP on 2nd day"){ new SimpleTest("GAZP", 27, 2, 2).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
    test("GMKN on 1st day"){ new SimpleTest("GMKN", 15, 1, 1).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
    test("GMKN on 2nd day"){ new SimpleTest("GMKN", 15, 2, 2).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
    test("LKOH on 1st day"){ new SimpleTest("LKOH", 23, 1, 1).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
    test("LKOH on 2nd day"){ new SimpleTest("LKOH", 25, 2, 2).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
    test("NVTK on 1st day"){ new SimpleTest("NVTK", 30, 1, 1).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
    test("NVTK on 2nd day"){ new SimpleTest("NVTK", 35, 2, 2).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
    test("ROSN on 1st day"){ new SimpleTest("ROSN", 20, 1, 1).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
    test("ROSN on 2nd day"){ new SimpleTest("ROSN", 20, 2, 2).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
    test("RTKM on 1st day"){ new SimpleTest("RTKM", 37, 1, 1).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
    test("RTKM on 2nd day"){ new SimpleTest("RTKM", 18, 2, 2).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
}
