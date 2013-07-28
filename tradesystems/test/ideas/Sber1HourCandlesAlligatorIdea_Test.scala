package ideas

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import logic.TestUtils

/**
 * @author alespuh
 * @since 22.07.13
 */
@RunWith(classOf[JUnitRunner])
class Sber1HourCandlesAlligatorIdea_Test extends FunSuite with TestUtils
{
    def b(openPosition: Double, closePosition: Double) = (openPosition, closePosition - openPosition)
    def s(openPosition: Double, closePosition: Double) = (openPosition, openPosition - closePosition)
    //заходим и выходим когда пересекли все три линии и adx растет из зоны меньше 15 3 - 5 свечей назад и не был больше 25 за эти 5 свечей
    test("alligator by hand")
    {
        createProfits(Vector(
            b(97.46, 99.87), //08.01.2013 11:00 - 15.01.2013 18:00 адх скакнул и закрылись выше аллигаторов, вышли когда закрылись ниже аллигаторов
            s(99.87, 100.19),//-
            b(100.19, 102.26),//22.01
            s(102.68, 102.08),//12:00 24.01 - выбросило по стопу в 1.5 атр
            b(103.15, 106.87),//12:00 31.01
            s(106.87, 107.87),//12:00 31.01 -
            s(108.79, 108.29),//9:00 06.04
            b(108.29, 107.49),// вылетели по стопу -
            b(107.05, 106.5),//12.02 -
            s(106.5, 107.5),//вылетели по стопу -
            s(107.93, 106.37),
            b(107.14, 106.50),//stop -
            b(106.86, 105.38),//даже по стопу не вылечу, потому что перенос на след. день
            b(104, 103.30),//stop
            s(103.69, 103.66),
            s(106.11, 105.55),
            b(105.55, 104.80), //stop
            b(102.02, 101.04),
            b(100.67, 99.67),//stop
            b(100, 101)//stub
            ))
    }
}
