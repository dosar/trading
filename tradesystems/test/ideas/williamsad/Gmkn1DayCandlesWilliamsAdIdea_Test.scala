package ideas.williamsad

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite

/**
 * @author alespuh
 * @since 28.07.13
 */
@RunWith(classOf[JUnitRunner])
class Gmkn1DayCandlesWilliamsAdIdea_Test extends FunSuite
{
    test("GMKN on 1 1"){ new SimpleTest("GMKN", 34, 1, 1).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
    test("GMKN on 1 2"){ new SimpleTest("GMKN", 23, 1, 2).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
    test("GMKN on 1 3"){ new SimpleTest("GMKN", 20, 1, 3).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
    test("GMKN on 2 1"){ new SimpleTest("GMKN", 23, 2, 1).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
    test("GMKN on 2 2"){ new SimpleTest("GMKN", 26, 2, 2).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
    test("GMKN on 2 3"){ new SimpleTest("GMKN", 22, 2, 3).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
    test("GMKN on 3 1"){ new SimpleTest("GMKN", 22, 3, 1).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
    test("GMKN on 3 2"){ new SimpleTest("GMKN", 22, 3, 2).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
    test("GMKN on 3 3"){ new SimpleTest("GMKN", 15, 3, 3).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
}
