import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

/**
 * @author alespuh
 * @since 14.06.13
 */
@RunWith(classOf[JUnitRunner])
class GazpDayCandles2010to2013_Test extends FunSuite with AnalyticalStatisticsPrinter
{
    lazy val data = standardImport("g:\\work\\trademachine\\GAZP_2010_2013_1day.txt")

    test("n day plus/n day minus with different stops"){ standardTest(1.5) }

    test("2 day plus/minus 2.5 roubles stop with details") { detailedTest(1.5) }
}
