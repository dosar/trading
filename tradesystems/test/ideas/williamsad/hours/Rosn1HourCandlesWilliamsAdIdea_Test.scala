package ideas.williamsad.hours

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * @author alespuh
 * @since 28.07.13
 */
@RunWith(classOf[JUnitRunner])
class Rosn1HourCandlesWilliamsAdIdea_Test extends FunSuite
{
    test("ROSN on 1 1"){ new SimpleTest("ROSN", 15, 1, 1).standardTestSP(Vector(0.1, 0.25, 0.5), Vector(8, 13, 17)) }
    test("ROSN on 1 2"){ new SimpleTest("ROSN", 15, 1, 2).standardTestSP(Vector(0.1, 0.25, 0.5), Vector(8, 13, 17)) }
    test("ROSN on 1 3"){ new SimpleTest("ROSN", 15, 1, 3).standardTestSP(Vector(0.1, 0.25, 0.5), Vector(8, 13, 17)) }
    test("ROSN on 2 1"){ new SimpleTest("ROSN", 15, 2, 1).standardTestSP(Vector(0.1, 0.25, 0.5), Vector(8, 13, 17)) }
    test("ROSN on 2 2"){ new SimpleTest("ROSN", 15, 2, 2).standardTestSP(Vector(0.1, 0.25, 0.5), Vector(8, 13, 17)) }
    test("ROSN on 2 3"){ new SimpleTest("ROSN", 15, 2, 3).standardTestSP(Vector(0.1, 0.25, 0.5), Vector(8, 13, 17)) }
    test("ROSN on 3 1"){ new SimpleTest("ROSN", 15, 3, 1).standardTestSP(Vector(0.1, 0.25, 0.5), Vector(8, 13, 17)) }
    test("ROSN on 3 2"){ new SimpleTest("ROSN", 15, 3, 2).standardTestSP(Vector(0.1, 0.25, 0.5), Vector(8, 13, 17)) }
    test("ROSN on 3 3"){ new SimpleTest("ROSN", 15, 3, 3).standardTestSP(Vector(0.1, 0.25, 0.5), Vector(8, 13, 17)) }
}