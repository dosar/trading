package parsing

import org.scalatest.FunSuite
import logic.TestUtils
import tradinganalyzers.statistics.DayStandardImporter._
import tradingideas._
import tradingsystems.TradingData
import tradingsystems.TradingData

/**
 * @author alespuh
 * @since 05.08.13
 */
object SimpleTrendWilliamsAdOutputParser
{
    def parse(strRepresentation: String): Vector[(TradingData, Int => WilliamsAdIdea, Int => LongTrendCandles)] =
    {
        val datas = Vector(gazp, gmkn, lkoh, nvtk, rosn, rtkm, sber)
            .map(data => (data.ticker, data)).toMap
        strRepresentation.stripMargin.split("\n").map{
            oneComb =>
            {
                val parts = oneComb.trim.split('|')
                val ticker = parts(0).substring(parts(0).length - 4, parts(0).length)
                val williamsAdF: Int => WilliamsAdIdea =
                    if(parts(3).substring(0, 3) == "Min") new WilliamsAdMin(parts(4).trim.toInt, _, parts(6).trim.toInt)
                    else new WilliamsAdMax(parts(4).trim.toInt, _, parts(6).trim.toInt)
                val simpleTrendF: Int => LongTrendCandles =
                    if(parts(8).contains("роста")) new PositiveTrendCandles(parts(8)(0).toString.toInt, _)
                    else new NegativeTrendCandles(parts(8)(0).toString.toInt, _)
                (datas(ticker), williamsAdF, simpleTrendF)
            }
        }.toVector
    }
}

class ParseSimpleTrendWilliamsAdOutput_Test extends FunSuite with TestUtils
{
    val parsedIterator = SimpleTrendWilliamsAdOutputParser.parse("""    30,00 GAZP|200|249|Min Williams A/D | 8|1|3||2 дня падения, 1 дня в
                         |    30,00 LKOH|70|109|Min Williams A/D |21|1|2||3 дня падения, 1 дня в
                         |    30,05 RTKM|193|262|Min Williams A/D |10|1|3||2 дня падения, 1 дня в
                         |    30,13 GAZP|156|249|Min Williams A/D |12|1|3||2 дня падения, 1 дня в
                         |    30,16 LKOH|63|109|Min Williams A/D |23|1|2||3 дня падения, 1 дня в
                         |    30,16 ROSN|126|221|Max Williams A/D |11|1|3||2 дня падения, 1 дня в
                         |    30,20 RTKM|149|262|Min Williams A/D |18|1|3||2 дня падения, 1 дня в
                         |    30,22 RTKM|139|141|Min Williams A/D |20|1|3||3 дня падения, 1 дня в
                         |    30,29 NVTK|175|223|Min Williams A/D | 7|1|3||2 дня роста, 1 дня в
                         |    30,30 RTKM|132|141|Min Williams A/D |22|1|3||3 дня падения, 1 дня в
                         |    30,35 ROSN|253|201|Max Williams A/D | 4|1|3||2 дня роста, 1 дня в
                         |    30,35 ROSN|239|201|Min Williams A/D | 5|1|3||2 дня роста, 1 дня в
                         |    30,36 RTKM|247|262|Min Williams A/D | 6|1|3||2 дня падения, 1 дня в
                         |    30,39 SBER|142|102|Min Williams A/D |12|1|3||3 дня падения, 1 дня в
                         |    30,40 RTKM|125|141|Min Williams A/D |24|1|3||3 дня падения, 1 дня в
                         |    30,43 GAZP|161|249|Min Williams A/D |11|1|3||2 дня падения, 1 дня в
                         |    30,43 SBER|161|223|Max Williams A/D | 9|1|3||2 дня падения, 1 дня в
                         |""").iterator

    test("1") { check(gazp, new WilliamsAdMin(8, _, 3), new NegativeTrendCandles(2, _), parsedIterator.next()) }
    test("16") { check(lkoh, new WilliamsAdMin(21, _, 2), new NegativeTrendCandles(3, _), parsedIterator.next()) }
    test("2") { check(rtkm, new WilliamsAdMin(10, _, 3), new NegativeTrendCandles(2, _), parsedIterator.next()) }
    test("3") { check(gazp, new WilliamsAdMin(12, _, 3), new NegativeTrendCandles(2, _), parsedIterator.next()) }
    test("17") { check(lkoh, new WilliamsAdMin(23, _, 2), new NegativeTrendCandles(3, _), parsedIterator.next()) }
    test("4") { check(rosn, new WilliamsAdMax(11, _, 3), new NegativeTrendCandles(2, _), parsedIterator.next()) }
    test("5") { check(rtkm, new WilliamsAdMin(18, _, 3), new NegativeTrendCandles(2, _), parsedIterator.next()) }
    test("6") { check(rtkm, new WilliamsAdMin(20, _, 3), new NegativeTrendCandles(3, _), parsedIterator.next()) }
    test("7") { check(nvtk, new WilliamsAdMin(7, _, 3), new PositiveTrendCandles(2, _), parsedIterator.next()) }
    test("8") { check(rtkm, new WilliamsAdMin(22, _, 3), new NegativeTrendCandles(3, _), parsedIterator.next()) }
    test("9") { check(rosn, new WilliamsAdMax(4, _, 3), new PositiveTrendCandles(2, _), parsedIterator.next()) }
    test("10") { check(rosn, new WilliamsAdMin(5, _, 3), new PositiveTrendCandles(2, _), parsedIterator.next()) }
    test("11") { check(rtkm, new WilliamsAdMin(6, _, 3), new NegativeTrendCandles(2, _), parsedIterator.next()) }
    test("12") { check(sber, new WilliamsAdMin(12, _, 3), new NegativeTrendCandles(3, _), parsedIterator.next()) }
    test("13") { check(rtkm, new WilliamsAdMin(24, _, 3), new NegativeTrendCandles(3, _), parsedIterator.next()) }
    test("14") { check(gazp, new WilliamsAdMin(11, _, 3), new NegativeTrendCandles(2, _), parsedIterator.next()) }
    test("15") { check(sber, new WilliamsAdMax(9, _, 3), new NegativeTrendCandles(2, _), parsedIterator.next()) }


    def check(data: TradingData, williamsAdF: Int => WilliamsAdIdea, simpleTrendF: Int => LongTrendCandles,
        actual: (TradingData, Int => WilliamsAdIdea, Int => LongTrendCandles))
    {
        assert(data === actual._1)
        assert(williamsAdF(0) === actual._2(0))
        assert(williamsAdF(10) === actual._2(10))
        assert(williamsAdF(20) === actual._2(20))
        assert(simpleTrendF(0) === actual._3(0))
        assert(simpleTrendF(10) === actual._3(10))
        assert(simpleTrendF(20) === actual._3(20))
    }
}
