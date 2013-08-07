package ideas.williamsad.days

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite

/**
 * @author alespuh
 * @since 28.07.13
 */
@RunWith(classOf[JUnitRunner])
class Lkoh1DayCandlesWilliamsAdIdea_Test extends FunSuite
{
//    Max Williams A/D | 3|6|1|op:sell p:11,00 s: 4,00 и Min Williams A/D | 3|5|1|op:buy  p:13,00 s: 2,00 | 2010|026|027|  35,67|0,00| -8,00|2011|027|027|  48,75|0,00|-10,17|2012|030|018|  43,61|-3,00| -4,00|2013|013|009|  15,93|-0,04| -3,41 |  | 1 | 003 | 5,85 | 0,00 | 2 | 005 | 7,38 | 0,00 | 3 | 004 | 5,19 | -1,75 | 4 | 004 | 1,65 | -4,00 | 5 | 007 | -0,06 | -5,67 | 6 | 005 | -2,35 | -8,00 | 7 | 006 | 2,98 | -2,16 | 8 | 004 | -2,36 | -2,36 | 9 | 004 | 1,32 | -3,08 | 10 | 003 | 1,74 | -1,17 | 11 | 005 | 6,51 | -2,38 | 12 | 003 | 7,83 | 0,00| | 1 | 003 | 9,33 | 0,00 | 2 | 003 | 4,04 | 0,00 | 3 | 004 | 10,89 | 0,00 | 4 | 006 | -8,49 | -8,49 | 5 | 006 | -10,17 | -10,17 | 6 | 004 | 2,35 | 0,00 | 7 | 003 | -2,49 | -3,92 | 8 | 005 | 29,94 | 0,00 | 9 | 003 | 20,20 | 0,00 | 10 | 006 | 3,74 | -4,00 | 11 | 004 | -0,52 | -0,72 | 12 | 007 | -10,08 | -10,08| | 1 | 004 | 6,65 | -3,00 | 2 | 004 | 0,61 | -3,54 | 3 | 004 | 5,04 | -1,08 | 4 | 004 | -2,16 | -2,16 | 5 | 005 | 8,24 | -4,00 | 6 | 004 | 6,42 | 0,00 | 7 | 005 | -1,27 | -1,27 | 8 | 003 | 0,02 | -2,53 | 9 | 005 | 3,57 | 0,00 | 10 | 003 | 7,36 | 0,00 | 11 | 004 | 7,07 | 0,00 | 12 | 003 | 2,06 | -1,27| | 1 | 004 | 2,81 | -0,04 | 2 | 003 | 1,64 | 0,00 | 3 | 004 | 6,07 | 0,00 | 4 | 005 | 5,51 | -3,41 | 5 | 005 | -2,49 | -2,49 | 6 | 001 | 2,40 | 0,00
    test("LKOH on 1 1"){ new SimpleTest("LKOH", 30, 1, 1).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
//    Max Williams A/D | 6|6|1|op:sell p: 8,00 s: 4,00 и Min Williams A/D | 3|4|2|op:buy  p:10,00 s: 4,00 | 2010|026|025|  30,35|0,00| -8,92|2011|030|020|  26,82|-5,89|-14,76|2012|027|021|  27,11|-3,00|-10,19|2013|010|012|  14,20|-2,70| -4,21 |  | 1 | 002 | 11,63 | 0,00 | 2 | 004 | 1,08 | 0,00 | 3 | 004 | 13,97 | 0,00 | 4 | 005 | -1,87 | -4,00 | 5 | 004 | -5,63 | -8,92 | 6 | 005 | -4,03 | -8,00 | 7 | 005 | 7,61 | 0,00 | 8 | 004 | -4,82 | -4,82 | 9 | 005 | 3,13 | -1,26 | 10 | 004 | 2,41 | -1,17 | 11 | 005 | 1,15 | -5,70 | 12 | 004 | 5,72 | 0,00| | 1 | 003 | -0,87 | -5,89 | 2 | 004 | 9,33 | 0,00 | 3 | 004 | 12,66 | 0,00 | 4 | 004 | -4,46 | -4,46 | 5 | 003 | -1,45 | -3,13 | 6 | 004 | 7,59 | 0,00 | 7 | 004 | 0,66 | -4,00 | 8 | 006 | -12,34 | -14,76 | 9 | 005 | 27,75 | 0,00 | 10 | 004 | -0,27 | -1,33 | 11 | 004 | -1,33 | -1,33 | 12 | 005 | -10,45 | -10,45| | 1 | 004 | 6,68 | -3,00 | 2 | 004 | 2,68 | -1,72 | 3 | 005 | 4,86 | -1,17 | 4 | 003 | 2,03 | 0,00 | 5 | 005 | -3,66 | -10,19 | 6 | 004 | 6,11 | 0,00 | 7 | 004 | 0,93 | -1,82 | 8 | 004 | -2,77 | -2,82 | 9 | 003 | -2,49 | -4,00 | 10 | 005 | 5,53 | -0,09 | 11 | 004 | 5,76 | 0,00 | 12 | 003 | 1,45 | -1,27| | 1 | 004 | -0,24 | -0,83 | 2 | 004 | -2,46 | -2,46 | 3 | 004 | 6,32 | 0,00 | 4 | 005 | 3,83 | -4,21 | 5 | 004 | 7,82 | -0,61 | 6 | 001 | -1,06 | -1,06
    test("LKOH on 1 2"){ new SimpleTest("LKOH", 26, 1, 2).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
//    Max Williams A/D | 6|6|1|op:sell p: 8,00 s: 4,00 и Min Williams A/D | 3|3|3|op:buy  p:13,00 s: 4,00 | 2010|027|026|  19,31|-4,57|-16,00|2011|028|023|  20,54|-5,89|-13,09|2012|032|020|  34,71|-3,00| -5,53|2013|013|011|  17,68|-2,06| -2,18 |  | 1 | 002 | 10,08 | 0,00 | 2 | 004 | 4,27 | 0,00 | 3 | 005 | 6,01 | 0,00 | 4 | 004 | 4,04 | -4,00 | 5 | 005 | -12,59 | -16,00 | 6 | 004 | -8,95 | -9,35 | 7 | 006 | 1,63 | -2,08 | 8 | 006 | -3,82 | -3,95 | 9 | 004 | -1,37 | -5,25 | 10 | 005 | 1,46 | 0,00 | 11 | 004 | 10,37 | 0,00 | 12 | 004 | 8,16 | 0,00| | 1 | 003 | -0,87 | -5,89 | 2 | 004 | 9,03 | 0,00 | 3 | 004 | 11,69 | 0,00 | 4 | 005 | -2,97 | -2,97 | 5 | 003 | 2,32 | -0,64 | 6 | 004 | 2,55 | 0,00 | 7 | 004 | 4,40 | -0,82 | 8 | 006 | -9,79 | -12,00 | 9 | 005 | 18,39 | -0,74 | 10 | 003 | -0,59 | -0,59 | 11 | 005 | -0,52 | -0,52 | 12 | 005 | -13,09 | -13,09| | 1 | 004 | 3,79 | -3,00 | 2 | 004 | 0,29 | -1,01 | 3 | 004 | 7,14 | -1,68 | 4 | 006 | 1,88 | -0,44 | 5 | 005 | -2,15 | -5,53 | 6 | 003 | 6,41 | 0,00 | 7 | 006 | -0,85 | -1,83 | 8 | 003 | -0,48 | -0,48 | 9 | 005 | 1,99 | -0,90 | 10 | 005 | 8,86 | 0,00 | 11 | 004 | 8,39 | 0,00 | 12 | 003 | -0,55 | -1,27| | 1 | 004 | -0,53 | -1,12 | 2 | 005 | -1,53 | -1,53 | 3 | 005 | 6,11 | 0,00 | 4 | 004 | 3,06 | -2,18 | 5 | 004 | 11,50 | 0,00 | 6 | 002 | -0,94 | -0,94
    test("LKOH on 1 3"){ new SimpleTest("LKOH", 19, 1, 3).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
//    Max Williams A/D | 3|5|2|op:sell p:13,00 s: 2,00 и Min Williams A/D | 3|4|1|op:buy  p:13,00 s: 2,00 | 2010|029|034|  27,66|-2,00|-13,53|2011|026|034|  26,08|-13,90| -8,00|2012|032|026|  31,09|-5,02| -5,23|2013|014|011|  13,01|-0,43| -3,29 |  | 1 | 002 | 3,76 | -2,00 | 2 | 006 | 7,35 | -2,00 | 3 | 007 | 0,79 | -6,18 | 4 | 005 | 1,47 | -2,00 | 5 | 005 | 8,12 | -2,00 | 6 | 008 | -13,53 | -13,53 | 7 | 007 | 0,92 | -2,00 | 8 | 004 | 2,13 | 0,00 | 9 | 005 | 3,29 | -4,38 | 10 | 004 | -0,97 | -1,13 | 11 | 005 | 8,05 | 0,00 | 12 | 005 | 6,28 | -2,00| | 1 | 004 | -4,53 | -4,53 | 2 | 005 | -3,36 | -3,36 | 3 | 005 | 8,54 | -0,85 | 4 | 007 | -4,36 | -4,36 | 5 | 006 | -7,64 | -8,00 | 6 | 005 | 2,29 | -2,00 | 7 | 004 | -4,13 | -4,83 | 8 | 005 | 15,19 | 0,00 | 9 | 005 | 26,71 | 0,00 | 10 | 004 | -4,94 | -6,00 | 11 | 005 | 9,02 | 0,00 | 12 | 005 | -6,69 | -6,69| | 1 | 005 | -3,02 | -4,85 | 2 | 005 | 2,51 | -2,00 | 3 | 006 | -1,63 | -1,63 | 4 | 003 | -0,13 | -2,00 | 5 | 006 | 15,50 | 0,00 | 6 | 004 | 2,98 | 0,00 | 7 | 005 | 7,54 | -2,00 | 8 | 005 | -2,21 | -5,23 | 9 | 005 | 5,14 | 0,00 | 10 | 005 | 1,97 | -1,52 | 11 | 004 | 5,37 | 0,00 | 12 | 005 | -2,95 | -4,00| | 1 | 003 | 0,62 | -0,43 | 2 | 004 | 3,56 | 0,00 | 3 | 005 | 4,79 | -0,52 | 4 | 007 | 3,89 | -2,56 | 5 | 005 | 0,42 | -3,29 | 6 | 001 | -0,27 | -0,27
//    Max Williams A/D | 3|5|2|op:sell p:13,00 s: 2,00 и Min Williams A/D | 6|4|1|op:buy  p:13,00 s: 2,00 | 2010|028|033|  26,87|-2,00|-13,53|2011|026|034|  26,08|-13,90| -8,00|2012|031|024|  29,91|-5,02| -5,23|2013|013|011|  17,14|-0,73| -2,56 |  | 1 | 002 | 3,76 | -2,00 | 2 | 006 | 2,57 | -2,00 | 3 | 006 | 2,79 | -4,18 | 4 | 005 | 1,47 | -2,00 | 5 | 005 | 8,12 | -2,00 | 6 | 008 | -13,53 | -13,53 | 7 | 007 | 0,92 | -2,00 | 8 | 004 | 2,13 | 0,00 | 9 | 005 | 3,29 | -4,38 | 10 | 004 | -0,97 | -1,13 | 11 | 004 | 10,05 | 0,00 | 12 | 005 | 6,28 | -2,00| | 1 | 004 | -4,53 | -4,53 | 2 | 005 | -3,36 | -3,36 | 3 | 005 | 8,54 | -0,85 | 4 | 007 | -4,36 | -4,36 | 5 | 006 | -7,64 | -8,00 | 6 | 005 | 2,29 | -2,00 | 7 | 004 | -4,13 | -4,83 | 8 | 005 | 15,19 | 0,00 | 9 | 005 | 26,71 | 0,00 | 10 | 004 | -4,94 | -6,00 | 11 | 005 | 9,02 | 0,00 | 12 | 005 | -6,69 | -6,69| | 1 | 005 | -3,02 | -4,85 | 2 | 005 | 2,51 | -2,00 | 3 | 005 | 0,37 | -0,76 | 4 | 003 | -0,13 | -2,00 | 5 | 006 | 6,16 | 0,00 | 6 | 003 | 8,99 | 0,00 | 7 | 005 | 7,54 | -2,00 | 8 | 005 | -2,21 | -5,23 | 9 | 005 | 5,14 | 0,00 | 10 | 005 | 1,97 | -1,52 | 11 | 004 | 5,37 | 0,00 | 12 | 004 | -2,79 | -4,00| | 1 | 004 | 2,83 | -0,73 | 2 | 003 | 1,44 | -0,25 | 3 | 005 | 4,79 | -0,52 | 4 | 006 | 1,77 | -2,56 | 5 | 005 | 6,57 | -2,00 | 6 | 001 | -0,27 | -0,27
//    Max Williams A/D | 3|4|2|op:sell p:13,00 s: 4,00 и Min Williams A/D | 6|5|1|op:buy  p: 9,00 s: 2,00 | 2010|027|031|  26,11|-1,62|-12,00|2011|032|024|  53,84|-4,00| -8,00|2012|032|023|  33,60|-1,40| -4,07|2013|014|008|  13,32|-0,62| -2,31 |  | 1 | 003 | 6,94 | 0,00 | 2 | 005 | 3,64 | 0,00 | 3 | 006 | -3,41 | -3,41 | 4 | 004 | 5,82 | -0,93 | 5 | 007 | -6,39 | -12,00 | 6 | 005 | -6,21 | -6,21 | 7 | 006 | 5,41 | -2,00 | 8 | 004 | -1,21 | -2,00 | 9 | 005 | 2,70 | -2,85 | 10 | 004 | -1,88 | -1,88 | 11 | 005 | 10,11 | -0,43 | 12 | 004 | 10,60 | 0,00| | 1 | 004 | 6,81 | -4,00 | 2 | 003 | -3,05 | -3,05 | 3 | 005 | 4,41 | -2,25 | 4 | 006 | -3,39 | -3,39 | 5 | 006 | -2,50 | -8,00 | 6 | 004 | -2,69 | -2,69 | 7 | 004 | -1,99 | -2,32 | 8 | 006 | 14,23 | 0,00 | 9 | 005 | 34,13 | 0,00 | 10 | 004 | 2,17 | 0,00 | 11 | 005 | 2,62 | 0,00 | 12 | 004 | 3,09 | 0,00| | 1 | 005 | 6,62 | -1,40 | 2 | 005 | 0,08 | -4,07 | 3 | 005 | 4,31 | 0,00 | 4 | 003 | 0,83 | 0,00 | 5 | 006 | 1,76 | 0,00 | 6 | 003 | 8,37 | 0,00 | 7 | 004 | 7,36 | -0,80 | 8 | 005 | -1,26 | -2,31 | 9 | 006 | 2,25 | 0,00 | 10 | 004 | 3,90 | -0,62 | 11 | 004 | 2,21 | 0,00 | 12 | 005 | -2,83 | -3,08| | 1 | 003 | -0,62 | -0,62 | 2 | 004 | 2,91 | 0,00 | 3 | 005 | 2,41 | -0,03 | 4 | 004 | 3,96 | 0,00 | 5 | 005 | 2,25 | -2,31 | 6 | 001 | 2,40 | 0,00
//    Max Williams A/D | 3|6|2|op:sell p:13,00 s: 2,00 и Min Williams A/D | 6|5|1|op:buy  p:13,00 s: 4,00 | 2010|025|027|  27,81|-2,00|-14,71|2011|023|028|  29,36|-6,69| -6,56|2012|024|023|  26,76|-5,58| -5,58|2013|012|006|  20,89|0,00| -2,00 |  | 1 | 002 | 2,77 | -2,00 | 2 | 004 | 3,92 | -2,00 | 3 | 006 | -1,72 | -3,40 | 4 | 004 | 3,61 | -2,00 | 5 | 004 | 7,08 | -2,00 | 6 | 008 | -14,71 | -14,71 | 7 | 005 | 8,76 | 0,00 | 8 | 003 | 3,27 | 0,00 | 9 | 004 | 0,62 | -3,26 | 10 | 005 | -6,03 | -6,03 | 11 | 004 | 15,67 | 0,00 | 12 | 003 | 4,56 | 0,00| | 1 | 004 | -4,89 | -4,89 | 2 | 004 | 0,20 | 0,00 | 3 | 003 | 0,67 | -2,00 | 4 | 004 | 4,52 | -2,00 | 5 | 004 | -0,97 | -4,00 | 6 | 004 | 1,26 | -2,00 | 7 | 004 | -3,23 | -3,29 | 8 | 005 | 5,00 | 0,00 | 9 | 004 | 21,72 | 0,00 | 10 | 007 | -2,67 | -4,75 | 11 | 004 | 14,32 | 0,00 | 12 | 004 | -6,56 | -6,56| | 1 | 005 | -0,97 | -5,58 | 2 | 003 | 7,73 | -1,64 | 3 | 004 | 2,39 | -1,10 | 4 | 003 | -0,41 | -0,41 | 5 | 005 | 0,54 | 0,00 | 6 | 003 | 0,63 | 0,00 | 7 | 004 | 4,78 | -2,00 | 8 | 005 | -3,09 | -4,62 | 9 | 004 | 2,01 | -4,00 | 10 | 004 | 5,58 | -0,98 | 11 | 004 | 11,20 | 0,00 | 12 | 003 | -3,61 | -3,61| | 1 | 003 | 1,14 | 0,00 | 2 | 003 | 1,30 | 0,00 | 3 | 004 | 4,87 | -2,00 | 4 | 003 | 1,97 | -1,41 | 5 | 004 | 9,22 | -2,00 | 6 | 001 | 2,40 | 0,00
//    Max Williams A/D | 3|5|2|op:sell p:13,00 s: 2,00 и Min Williams A/D | 6|5|1|op:buy  p:13,00 s: 4,00 | 2010|027|028|  43,59|-2,00|-15,53|2011|026|030|  28,59|-9,62| -6,69|2012|030|020|  30,22|-5,02| -4,85|2013|011|009|  13,42|-0,04| -2,00 |  | 1 | 002 | 3,76 | -2,00 | 2 | 005 | 4,33 | -2,00 | 3 | 006 | 4,35 | -4,18 | 4 | 005 | 1,47 | -2,00 | 5 | 004 | 7,07 | -2,00 | 6 | 008 | -15,53 | -15,53 | 7 | 005 | 8,53 | 0,00 | 8 | 003 | 3,00 | 0,00 | 9 | 005 | 3,67 | -4,38 | 10 | 004 | -1,53 | -1,53 | 11 | 004 | 12,50 | 0,00 | 12 | 004 | 11,97 | 0,00| | 1 | 004 | -4,53 | -4,53 | 2 | 004 | -3,31 | -3,31 | 3 | 005 | 6,44 | -0,85 | 4 | 005 | -4,22 | -4,22 | 5 | 004 | 0,26 | -4,00 | 6 | 005 | 1,47 | -2,00 | 7 | 004 | -4,13 | -4,83 | 8 | 005 | 18,78 | 0,00 | 9 | 004 | 22,06 | 0,00 | 10 | 007 | -3,69 | -4,75 | 11 | 004 | 6,17 | 0,00 | 12 | 005 | -6,69 | -6,69| | 1 | 005 | -3,02 | -4,85 | 2 | 004 | 1,95 | -2,00 | 3 | 004 | 4,27 | -0,76 | 4 | 003 | -0,13 | -2,00 | 5 | 006 | 9,03 | 0,00 | 6 | 003 | 9,18 | 0,00 | 7 | 005 | -0,90 | -2,00 | 8 | 004 | -0,21 | -3,23 | 9 | 004 | 7,07 | 0,00 | 10 | 004 | 0,86 | -1,52 | 11 | 004 | 4,91 | 0,00 | 12 | 004 | -2,79 | -4,00| | 1 | 004 | 1,09 | -0,04 | 2 | 003 | 0,58 | -0,25 | 3 | 004 | 4,94 | 0,00 | 4 | 004 | 3,70 | 0,00 | 5 | 005 | 3,11 | -2,00
    test("LKOH on 2 1"){ new SimpleTest("LKOH", 26, 2, 1).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
//    Max Williams A/D | 6|2|2|op:sell p:13,00 s: 4,00 и Min Williams A/D | 3|2|2|op:buy  p:13,00 s: 2,00 | 2010|055|041|  23,74|-7,85| -6,57|2011|050|044|  27,09|0,00|-13,74|2012|052|046|  22,56|-2,58| -5,58|2013|023|020|  11,66|-1,06| -3,84 |  | 1 | 003 | 3,35 | 0,00 | 2 | 009 | -0,05 | -3,16 | 3 | 008 | 7,10 | -0,59 | 4 | 009 | -6,57 | -6,57 | 5 | 008 | -6,20 | -6,20 | 6 | 008 | -0,02 | -5,48 | 7 | 009 | 7,54 | -2,00 | 8 | 008 | -5,40 | -5,40 | 9 | 010 | 10,73 | 0,00 | 10 | 008 | 6,90 | 0,00 | 11 | 008 | 5,42 | -1,25 | 12 | 008 | 0,95 | -2,88| | 1 | 006 | 6,54 | 0,00 | 2 | 007 | 1,83 | 0,00 | 3 | 008 | 5,38 | -0,74 | 4 | 009 | -7,65 | -7,65 | 5 | 006 | 0,78 | -3,20 | 6 | 007 | 3,76 | 0,00 | 7 | 010 | 7,86 | 0,00 | 8 | 010 | -13,62 | -13,74 | 9 | 008 | 25,39 | 0,00 | 10 | 010 | -2,01 | -2,01 | 11 | 006 | 5,28 | 0,00 | 12 | 007 | -6,45 | -6,45| | 1 | 010 | 6,09 | -2,58 | 2 | 008 | 6,63 | 0,00 | 3 | 008 | -1,13 | -1,15 | 4 | 007 | 2,09 | -2,00 | 5 | 007 | 9,13 | 0,00 | 6 | 008 | 0,08 | -1,28 | 7 | 008 | 0,88 | -2,26 | 8 | 009 | -5,05 | -5,05 | 9 | 009 | -0,91 | -3,91 | 10 | 009 | 4,96 | 0,00 | 11 | 007 | 5,37 | 0,00 | 12 | 008 | -5,58 | -5,58| | 1 | 007 | 1,75 | -1,06 | 2 | 008 | -1,75 | -2,25 | 3 | 008 | 2,75 | -0,84 | 4 | 009 | 8,18 | -1,96 | 5 | 008 | -3,84 | -3,84 | 6 | 003 | 4,56 | 0,00
//    Max Williams A/D | 9|5|2|op:sell p:13,00 s: 2,00 и Min Williams A/D | 6|4|2|op:buy  p:13,00 s: 4,00 | 2010|023|023|  25,86|-6,78|-11,53|2011|027|024|  25,52|-4,00|-12,76|2012|030|018|  36,15|-4,85| -4,85|2013|011|010|  11,36|-0,82| -2,00 |  | 1 | 001 | -0,08 | -0,08 | 2 | 002 | 7,57 | 0,00 | 3 | 004 | 6,79 | -2,00 | 4 | 005 | -2,96 | -2,96 | 5 | 004 | -5,63 | -8,92 | 6 | 006 | -11,53 | -11,53 | 7 | 005 | 8,79 | 0,00 | 8 | 004 | -5,35 | -5,35 | 9 | 005 | 3,29 | -4,38 | 10 | 003 | 4,87 | 0,00 | 11 | 003 | 1,92 | 0,00 | 12 | 004 | 18,19 | 0,00| | 1 | 004 | 3,50 | -4,00 | 2 | 005 | 2,09 | 0,00 | 3 | 004 | 10,54 | -0,85 | 4 | 004 | -7,70 | -7,70 | 5 | 003 | 0,13 | -3,13 | 6 | 005 | 5,18 | -2,00 | 7 | 004 | -4,13 | -4,83 | 8 | 007 | -7,91 | -12,76 | 9 | 004 | 23,18 | -0,93 | 10 | 004 | 3,05 | 0,00 | 11 | 004 | 2,79 | 0,00 | 12 | 003 | -5,20 | -5,20| | 1 | 005 | -1,66 | -4,85 | 2 | 004 | 4,56 | 0,00 | 3 | 003 | 2,25 | -2,00 | 4 | 004 | -3,17 | -3,17 | 5 | 004 | -1,20 | -1,94 | 6 | 003 | 8,99 | 0,00 | 7 | 005 | 7,54 | -2,00 | 8 | 004 | -0,21 | -3,23 | 9 | 004 | 4,02 | 0,00 | 10 | 004 | 8,29 | -0,09 | 11 | 004 | 9,52 | 0,00 | 12 | 004 | -2,79 | -4,00| | 1 | 003 | -0,60 | -0,82 | 2 | 004 | 0,46 | 0,00 | 3 | 004 | 4,04 | 0,00 | 4 | 004 | 6,20 | 0,00 | 5 | 005 | 2,31 | -2,00 | 6 | 001 | -1,06 | -1,06
//    Max Williams A/D | 9|5|2|op:sell p:13,00 s: 4,00 и Min Williams A/D | 6|6|2|op:buy  p:11,00 s: 4,00 | 2010|020|017|  30,23|0,00|-10,51|2011|021|020|  23,67|-5,95|-12,16|2012|023|017|  22,76|-9,29| -7,35|2013|008|009|  11,53|-0,83| -1,77 |  | 1 | 001 | 1,68 | 0,00 | 2 | 002 | 9,09 | 0,00 | 3 | 004 | 15,32 | 0,00 | 4 | 003 | -4,71 | -4,71 | 5 | 004 | -1,54 | -10,51 | 6 | 003 | -9,21 | -9,21 | 7 | 005 | 5,83 | 0,00 | 8 | 003 | -7,00 | -7,00 | 9 | 004 | 2,64 | -4,38 | 10 | 003 | 5,69 | 0,00 | 11 | 002 | 2,84 | 0,00 | 12 | 003 | 9,59 | 0,00| | 1 | 003 | 1,52 | -5,95 | 2 | 003 | 4,83 | 0,00 | 3 | 003 | 11,62 | 0,00 | 4 | 004 | -10,26 | -10,26 | 5 | 003 | 5,45 | -5,55 | 6 | 004 | 0,05 | -2,05 | 7 | 003 | 1,56 | 0,00 | 8 | 005 | -8,33 | -12,16 | 9 | 004 | 17,88 | -2,50 | 10 | 003 | 3,22 | 0,00 | 11 | 003 | 0,10 | -4,90 | 12 | 003 | -3,97 | -4,00| | 1 | 004 | 0,99 | -3,95 | 2 | 003 | 5,52 | -1,81 | 3 | 003 | -2,29 | -4,00 | 4 | 004 | -6,16 | -6,16 | 5 | 004 | -2,99 | -7,35 | 6 | 003 | 8,91 | 0,00 | 7 | 003 | 1,02 | -0,16 | 8 | 004 | -3,16 | -4,24 | 9 | 004 | 4,20 | -0,46 | 10 | 002 | 2,81 | 0,00 | 11 | 004 | 14,55 | 0,00 | 12 | 002 | -0,63 | -0,63| | 1 | 003 | -0,60 | -0,83 | 2 | 003 | 0,11 | 0,00 | 3 | 003 | 8,06 | 0,00 | 4 | 004 | 0,46 | 0,00 | 5 | 004 | 3,50 | -1,77
    test("LKOH on 2 2"){ new SimpleTest("LKOH", 22, 2, 2).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
//    Max Williams A/D | 3|6|2|op:sell p:13,00 s: 4,00 и Min Williams A/D | 3|4|3|op:buy  p:13,00 s: 4,00 | 2010|025|024|  25,34|-2,32|-16,00|2011|026|023|  19,66|-6,89|-12,80|2012|028|020|  32,07|-5,13| -5,13|2013|011|011|  14,47|-1,10| -4,35 |  | 1 | 002 | 9,92 | 0,00 | 2 | 004 | 1,05 | 0,00 | 3 | 004 | 13,97 | 0,00 | 4 | 004 | -5,74 | -5,74 | 5 | 005 | -9,35 | -16,00 | 6 | 004 | -10,45 | -10,45 | 7 | 005 | 2,29 | -1,73 | 8 | 004 | 5,59 | 0,00 | 9 | 003 | -2,27 | -6,15 | 10 | 004 | 3,10 | -0,03 | 11 | 005 | -0,74 | -1,09 | 12 | 005 | 17,97 | 0,00| | 1 | 003 | -6,89 | -6,89 | 2 | 004 | 5,75 | 0,00 | 3 | 004 | 4,02 | 0,00 | 4 | 004 | 0,16 | -0,43 | 5 | 004 | -6,80 | -8,00 | 6 | 004 | 3,00 | -2,74 | 7 | 004 | 7,70 | -0,22 | 8 | 006 | -10,97 | -12,80 | 9 | 004 | 15,17 | -0,93 | 10 | 004 | -8,88 | -10,96 | 11 | 004 | 13,87 | 0,00 | 12 | 004 | 3,54 | 0,00| | 1 | 004 | 0,19 | -5,13 | 2 | 004 | -2,66 | -2,90 | 3 | 004 | 1,64 | -1,36 | 4 | 004 | 1,45 | 0,00 | 5 | 005 | 0,77 | 0,00 | 6 | 003 | 1,35 | -3,11 | 7 | 005 | 4,80 | -0,71 | 8 | 003 | 0,91 | -0,62 | 9 | 004 | 4,87 | -0,73 | 10 | 005 | 9,12 | -1,73 | 11 | 004 | 10,80 | 0,00 | 12 | 003 | -1,17 | -1,17| | 1 | 004 | -0,03 | -0,23 | 2 | 003 | 0,42 | 0,00 | 3 | 005 | 2,86 | -0,33 | 4 | 004 | -0,01 | -4,35 | 5 | 004 | 13,75 | 0,00 | 6 | 002 | -2,51 | -2,51
    test("LKOH on 2 3"){ new SimpleTest("LKOH", 19, 2, 3).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
//    Max Williams A/D | 3|4|3|op:sell p:13,00 s: 2,00 и Min Williams A/D | 9|5|1|op:buy  p:10,00 s: 2,00 | 2010|027|035|  23,84|-8,15|-14,00|2011|025|036|  24,97|-2,31|-12,00|2012|028|025|  23,22|-2,00| -6,11|2013|013|009|  12,31|-0,60| -2,00 |  | 1 | 003 | 7,21 | 0,00 | 2 | 006 | 5,15 | 0,00 | 3 | 007 | -3,90 | -3,90 | 4 | 004 | 3,27 | -2,00 | 5 | 008 | -8,39 | -14,00 | 6 | 006 | -4,01 | -6,00 | 7 | 006 | 0,33 | -3,45 | 8 | 004 | -3,44 | -3,44 | 9 | 005 | 3,42 | -4,37 | 10 | 004 | 0,37 | -2,00 | 11 | 005 | 12,03 | 0,00 | 12 | 004 | 11,81 | 0,00| | 1 | 003 | 0,91 | -2,31 | 2 | 004 | 4,77 | 0,00 | 3 | 005 | 6,28 | -0,05 | 4 | 006 | 1,58 | 0,00 | 5 | 006 | -8,23 | -8,23 | 6 | 005 | -2,33 | -2,33 | 7 | 004 | -1,34 | -2,91 | 8 | 006 | -1,15 | -2,00 | 9 | 006 | 29,61 | 0,00 | 10 | 005 | 4,35 | 0,00 | 11 | 005 | 2,53 | 0,00 | 12 | 006 | -12,00 | -12,00| | 1 | 004 | 1,89 | -2,00 | 2 | 004 | 5,20 | -0,50 | 3 | 004 | 7,09 | -2,00 | 4 | 004 | -1,54 | -2,00 | 5 | 006 | -2,05 | -2,05 | 6 | 004 | 6,19 | -2,00 | 7 | 004 | 6,02 | -0,15 | 8 | 005 | -2,59 | -4,80 | 9 | 006 | 3,32 | 0,00 | 10 | 004 | 2,80 | -1,60 | 11 | 003 | 2,60 | 0,00 | 12 | 005 | -5,72 | -6,11| | 1 | 004 | -0,33 | -0,60 | 2 | 004 | 1,18 | 0,00 | 3 | 004 | 4,41 | 0,00 | 4 | 004 | 3,96 | 0,00 | 5 | 005 | 0,69 | -2,00 | 6 | 001 | 2,40 | 0,00
    test("LKOH on 3 1"){ new SimpleTest("LKOH", 23, 3, 1).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
//    Max Williams A/D | 3|5|3|op:sell p:13,00 s: 2,00 и Min Williams A/D | 6|5|2|op:buy  p:10,00 s: 4,00 | 2010|028|023|  57,97|0,00|-11,28|2011|026|026|  40,07|-2,00| -9,11|2012|029|020|  31,78|-3,95| -4,83|2013|011|009|  14,47|-0,69| -3,02 |  | 1 | 002 | 11,71 | 0,00 | 2 | 004 | 6,33 | 0,00 | 3 | 005 | 16,06 | -2,00 | 4 | 004 | 6,41 | -2,00 | 5 | 005 | -5,13 | -11,28 | 6 | 005 | 0,54 | -6,00 | 7 | 005 | 8,02 | -0,20 | 8 | 004 | -5,62 | -5,62 | 9 | 004 | -0,77 | -4,38 | 10 | 005 | -1,64 | -1,64 | 11 | 003 | 3,51 | 0,00 | 12 | 005 | 18,54 | 0,00| | 1 | 003 | -0,29 | -2,00 | 2 | 004 | 3,54 | 0,00 | 3 | 004 | 7,87 | -0,85 | 4 | 004 | 7,80 | 0,00 | 5 | 004 | -4,42 | -4,42 | 6 | 004 | 0,40 | -1,24 | 7 | 004 | -2,95 | -3,88 | 8 | 004 | 10,50 | 0,00 | 9 | 006 | 23,67 | -2,92 | 10 | 004 | 1,97 | 0,00 | 11 | 005 | 1,10 | -0,02 | 12 | 006 | -9,11 | -9,11| | 1 | 004 | -0,44 | -2,85 | 2 | 004 | 5,44 | -3,51 | 3 | 004 | 3,03 | -2,00 | 4 | 004 | -3,27 | -3,27 | 5 | 005 | 2,93 | -0,76 | 6 | 004 | 5,00 | -2,00 | 7 | 004 | 3,61 | 0,00 | 8 | 004 | -0,29 | -2,00 | 9 | 005 | 4,18 | -0,45 | 10 | 004 | 2,93 | -1,05 | 11 | 004 | 13,48 | 0,00 | 12 | 003 | -4,83 | -4,83| | 1 | 004 | 0,86 | -0,69 | 2 | 003 | -0,25 | -0,25 | 3 | 004 | 5,81 | 0,00 | 4 | 004 | 0,72 | -3,02 | 5 | 004 | 4,93 | -2,10 | 6 | 001 | 2,40 | 0,00
    test("LKOH on 3 2"){ new SimpleTest("LKOH", 28, 3, 2).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
//    Max Williams A/D | 9|4|3|op:sell p:13,00 s: 4,00 и Min Williams A/D | 3|4|3|op:buy  p:13,00 s: 4,00 | 2010|028|023|  26,84|-6,35|-16,00|2011|031|023|  25,76|0,00|-20,47|2012|031|024|  22,91|-1,40| -4,17|2013|012|012|  11,52|-0,82| -2,51 |  | 1 | 002 | 3,58 | -0,08 | 2 | 004 | 5,73 | 0,00 | 3 | 004 | 3,40 | -1,40 | 4 | 004 | -1,69 | -1,69 | 5 | 005 | -9,35 | -16,00 | 6 | 004 | -6,31 | -8,00 | 7 | 004 | 6,99 | -1,73 | 8 | 004 | 2,06 | -0,02 | 9 | 006 | 6,39 | -4,96 | 10 | 003 | 2,50 | 0,00 | 11 | 005 | -1,67 | -2,02 | 12 | 006 | 15,21 | 0,00| | 1 | 003 | 6,89 | 0,00 | 2 | 005 | 14,68 | 0,00 | 3 | 005 | 6,61 | -0,60 | 4 | 004 | -0,50 | -1,08 | 5 | 004 | -3,33 | -8,00 | 6 | 004 | 4,18 | -0,56 | 7 | 005 | 2,97 | 0,00 | 8 | 007 | -17,91 | -20,47 | 9 | 005 | -3,15 | -3,15 | 10 | 003 | 9,89 | 0,00 | 11 | 004 | 9,02 | 0,00 | 12 | 005 | -3,59 | -3,59| | 1 | 005 | 3,93 | -1,40 | 2 | 004 | 6,58 | 0,00 | 3 | 005 | -4,17 | -4,17 | 4 | 004 | 1,65 | 0,00 | 5 | 004 | 1,24 | -3,19 | 6 | 005 | 6,09 | -0,55 | 7 | 005 | 0,90 | -1,37 | 8 | 005 | -1,55 | -3,76 | 9 | 005 | -0,91 | -3,06 | 10 | 005 | 9,54 | -0,81 | 11 | 004 | 2,01 | 0,00 | 12 | 004 | -2,41 | -2,41| | 1 | 004 | -0,55 | -0,82 | 2 | 004 | -0,19 | -0,19 | 3 | 005 | 4,82 | 0,00 | 4 | 005 | 5,36 | 0,00 | 5 | 004 | 4,59 | -0,39 | 6 | 002 | -2,51 | -2,51
    test("LKOH on 3 3"){ new SimpleTest("LKOH", 22, 3, 3).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
}