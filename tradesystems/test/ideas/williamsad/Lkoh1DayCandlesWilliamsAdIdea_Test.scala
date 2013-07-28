package ideas.williamsad

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite

/**
 * @author alespuh
 * @since 28.07.13
 */
@RunWith(classOf[JUnitRunner])
class Lkoh1DayCandlesWilliamsAdIdea_Test extends FunSuite
{
    test("LKOH on 1 1"){ new SimpleTest("LKOH", 30, 1, 1).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
    test("LKOH on 1 2"){ new SimpleTest("LKOH", 26, 1, 2).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
    test("LKOH on 1 3"){ new SimpleTest("LKOH", 20, 1, 3).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
    test("LKOH on 2 1"){ new SimpleTest("LKOH", 26, 2, 1).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
    test("LKOH on 2 2"){ new SimpleTest("LKOH", 20, 2, 2).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
    test("LKOH on 2 3"){ new SimpleTest("LKOH", 20, 2, 3).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
    test("LKOH on 3 1"){ new SimpleTest("LKOH", 23, 3, 1).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
    test("LKOH on 3 2"){ new SimpleTest("LKOH", 28, 3, 2).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
    test("LKOH on 3 3"){ new SimpleTest("LKOH", 20, 3, 3).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
}
