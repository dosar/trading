package ideas.williamsad

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * @author alespuh
 * @since 28.07.13
 */
@RunWith(classOf[JUnitRunner])
class Gazp1DayCandlesWilliamsAdIdea_Test extends FunSuite
{
    test("GAZP on 1 1"){ new SimpleTest("GAZP", 33, 1, 1).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
    test("GAZP on 1 2"){ new SimpleTest("GAZP", 23, 1, 2).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
    test("GAZP on 1 3"){ new SimpleTest("GAZP", 34, 1, 3).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
    test("GAZP on 2 1"){ new SimpleTest("GAZP", 25, 2, 1).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
    test("GAZP on 2 2"){ new SimpleTest("GAZP", 26, 2, 2).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
    test("GAZP on 2 3"){ new SimpleTest("GAZP", 28, 2, 3).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
    test("GAZP on 3 1"){ new SimpleTest("GAZP", 26, 3, 1).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
    test("GAZP on 3 2"){ new SimpleTest("GAZP", 23, 3, 2).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
    test("GAZP on 3 3"){ new SimpleTest("GAZP", 34, 3, 3).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
}
