import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

/**
 * @author alespuh
 * @since 14.06.13
 */
@RunWith(classOf[JUnitRunner])
class RosneftDayCandles2010to2013_Test extends FunSuite with AnalyticalStatisticsPrinter
{
    lazy val data = standardImport("g:\\work\\trademachine\\GAZP_2010_2013_1day.txt")

    test("n day plus/n day minus with different stops"){ standardTest(2.5) }

    test("2 day plus/minus 45 roubles stop with details") { detailedTest(2.5, 2) }
}
