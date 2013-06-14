import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

/**
 * @author alespuh
 * @since 13.06.13
 */
@RunWith(classOf[JUnitRunner])
class Sber15MinutesCandles2010to2013_Test extends FunSuite with AnalyticalStatisticsPrinter
{
    lazy val data = standardImport("g:\\work\\trademachine\\SBER_2010_2013_15min.txt")

    test("n day plus/n day minus with different stops"){ standardTest() }
}
