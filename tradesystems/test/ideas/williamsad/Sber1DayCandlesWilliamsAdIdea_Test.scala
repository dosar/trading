package ideas.williamsad

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite

/**
 * @author alespuh
 * @since 28.07.13
 */
@RunWith(classOf[JUnitRunner])
class Sber1DayCandlesWilliamsAdIdea_Test extends FunSuite
{
    test("SBER on 1 1"){ new SimpleTest("SBER", 28, 1, 1).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
    test("SBER on 1 2"){ new SimpleTest("SBER", 36, 1, 2).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
    test("SBER on 1 3"){ new SimpleTest("SBER", 36, 1, 3).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
    test("SBER on 2 1"){ new SimpleTest("SBER", 28, 2, 1).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
    test("SBER on 2 2"){ new SimpleTest("SBER", 45, 2, 2).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
    test("SBER on 2 3"){ new SimpleTest("SBER", 32, 2, 3).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
    test("SBER on 3 1"){ new SimpleTest("SBER", 28, 3, 1).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
    test("SBER on 3 2"){ new SimpleTest("SBER", 30, 3, 2).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
    test("SBER on 3 3"){ new SimpleTest("SBER", 23, 3, 3).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
}
