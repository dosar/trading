package ideas.williamsbysma.hours

import org.scalatest.FunSuite

/**
 * @author alespuh
 * @since 06.08.13
 */
class Sber1HourCandlesWsAdBySmaIdea_Test extends FunSuite
{
    test("SBER on 1 1 3 sma up fall"){ new SimpleTest("SBER", 15, 1, 1, 3, true).standardTestSP(Vector(0.1, 0.25), Vector(13, 17)) }
    test("SBER on 1 1 3 sma fall up"){ new SimpleTest("SBER", 15, 1, 1, 3, false).standardTestSP(Vector(0.1, 0.25), Vector(13, 17)) }
    test("SBER on 1 1 7 sma up fall"){ new SimpleTest("SBER", 15, 1, 1, 7, true).standardTestSP(Vector(0.1, 0.25), Vector(13, 17)) }
    test("SBER on 1 1 7 sma fall up"){ new SimpleTest("SBER", 15, 1, 1, 7, false).standardTestSP(Vector(0.1, 0.25), Vector(13, 17)) }
    test("SBER on 1 1 14 sma up fall"){ new SimpleTest("SBER", 15, 1, 1, 14, true).standardTestSP(Vector(0.1, 0.25), Vector(13, 17)) }
    test("SBER on 1 1 14 sma fall up"){ new SimpleTest("SBER", 15, 1, 1, 14, false).standardTestSP(Vector(0.1, 0.25), Vector(8, 13)) }
    test("SBER on 1 1 15 sma up fall"){ new SimpleTest("SBER", 15, 1, 1, 15, true).standardTestSP(Vector(0.1, 0.25), Vector(13, 17)) }
    test("SBER on 1 1 16 sma up fall"){ new SimpleTest("SBER", 15, 1, 1, 16, true).standardTestSP(Vector(0.1, 0.25), Vector(13, 17)) }
    test("SBER on 1 1 17 sma up fall"){ new SimpleTest("SBER", 15, 1, 1, 17, true).standardTestSP(Vector(0.1, 0.25), Vector(13, 17)) }
    test("SBER on 1 1 16 sma fall up"){ new SimpleTest("SBER", 15, 1, 1, 16, false).standardTestSP(Vector(0.1, 0.25), Vector(8, 13)) }
    test("SBER on 1 1 21 sma up fall"){ new SimpleTest("SBER", 15, 1, 1, 21, true).standardTestSP(Vector(0.1, 0.25), Vector(13, 17)) }
    test("SBER on 1 1 21 sma fall up"){ new SimpleTest("SBER", 15, 1, 1, 21, false).standardTestSP(Vector(0.1, 0.25), Vector(13, 17)) }
}
