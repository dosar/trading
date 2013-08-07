package ideas.williamsad.hours

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite

/**
 * @author alespuh
 * @since 29.07.13
 */
@RunWith(classOf[JUnitRunner])
class Rtkm1HourCandlesWilliamsAdIdea_Test extends FunSuite
{
    test("RTKM on 1 1"){ new SimpleTest("RTKM", 15, 1, 1).standardTestSP(Vector(0.1, 0.25, 0.5), Vector(8, 13, 17)) }
    test("RTKM on 1 2"){ new SimpleTest("RTKM", 15, 1, 2).standardTestSP(Vector(0.1, 0.25, 0.5), Vector(8, 13, 17)) }
    test("RTKM on 1 3"){ new SimpleTest("RTKM", 15, 1, 3).standardTestSP(Vector(0.1, 0.25, 0.5), Vector(8, 13, 17)) }
    test("RTKM on 2 1"){ new SimpleTest("RTKM", 15, 2, 1).standardTestSP(Vector(0.1, 0.25, 0.5), Vector(8, 13, 17)) }
    test("RTKM on 2 2"){ new SimpleTest("RTKM", 15, 2, 2).standardTestSP(Vector(0.1, 0.25, 0.5), Vector(8, 13, 17)) }
    test("RTKM on 2 3"){ new SimpleTest("RTKM", 15, 2, 3).standardTestSP(Vector(0.1, 0.25, 0.5), Vector(8, 13, 17)) }
    test("RTKM on 3 1"){ new SimpleTest("RTKM", 15, 3, 1).standardTestSP(Vector(0.1, 0.25, 0.5), Vector(8, 13, 17)) }
    test("RTKM on 3 2"){ new SimpleTest("RTKM", 15, 3, 2).standardTestSP(Vector(0.1, 0.25, 0.5), Vector(8, 13, 17)) }
    test("RTKM on 3 3"){ new SimpleTest("RTKM", 15, 3, 3).standardTestSP(Vector(0.1, 0.25, 0.5), Vector(8, 13, 17)) }
}