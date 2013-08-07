package ideas.williamsad.hours

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite

/**
  * @author alespuh
  * @since 28.07.13
  */
@RunWith(classOf[JUnitRunner])
class Gmkn1HourCandlesWilliamsAdIdea_Test extends FunSuite
 {
     test("GMKN on 1 1"){ new SimpleTest("GMKN", 15, 1, 1).standardTestSP(Vector(0.25, 0.5), Vector(13, 17)) }
     test("GMKN on 1 2"){ new SimpleTest("GMKN", 15, 1, 1).standardTestSP(Vector(0.25, 0.5), Vector(13, 17)) }
     test("GMKN on 1 3"){ new SimpleTest("GMKN", 15, 1, 1).standardTestSP(Vector(0.25, 0.5), Vector(13, 17)) }
     test("GMKN on 2 1"){ new SimpleTest("GMKN", 15, 1, 1).standardTestSP(Vector(0.25, 0.5), Vector(13, 17)) }
     test("GMKN on 2 2"){ new SimpleTest("GMKN", 15, 1, 1).standardTestSP(Vector(0.25, 0.5), Vector(13, 17)) }
     test("GMKN on 2 3"){ new SimpleTest("GMKN", 15, 1, 1).standardTestSP(Vector(0.25, 0.5), Vector(13, 17)) }
     test("GMKN on 3 1"){ new SimpleTest("GMKN", 15, 1, 1).standardTestSP(Vector(0.25, 0.5), Vector(13, 17)) }
     test("GMKN on 3 2"){ new SimpleTest("GMKN", 15, 1, 1).standardTestSP(Vector(0.25, 0.5), Vector(13, 17)) }
     test("GMKN on 3 3"){ new SimpleTest("GMKN", 15, 1, 1).standardTestSP(Vector(0.25, 0.5), Vector(13, 17)) }
 }
