package ideas.williamsad

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite

/**
 * @author alespuh
 * @since 28.07.13
 */
@RunWith(classOf[JUnitRunner])
class NvtkDayCandlesWilliamsAdIdea_Test extends FunSuite
{
//    Max Williams A/D | 6|7|1|op:sell p: 9,00 s: 4,00 и Min Williams A/D | 6|5|1|op:buy  p:13,00 s: 4,00 | 2010|023|021|  48,20|0,00|-16,00|2011|021|024|  41,09|-8,30|-16,00|2012|023|014|  48,25|-3,20| -8,00|2013|010|006|  23,92|0,00| -6,64 |  | 1 | 002 | 12,45 | 0,00 | 2 | 003 | 21,44 | 0,00 | 3 | 004 | -4,46 | -5,37 | 4 | 003 | 25,96 | 0,00 | 5 | 004 | 23,89 | -4,00 | 6 | 006 | -9,55 | -16,00 | 7 | 003 | -2,77 | -4,00 | 8 | 004 | -3,26 | -4,00 | 9 | 003 | -7,13 | -8,00 | 10 | 003 | -6,67 | -6,67 | 11 | 005 | 8,46 | -8,00 | 12 | 004 | -10,16 | -10,16| | 1 | 002 | -1,39 | -1,39 | 2 | 004 | 0,10 | 0,00 | 3 | 004 | 1,09 | -4,64 | 4 | 003 | 3,57 | 0,00 | 5 | 005 | -2,59 | -8,51 | 6 | 004 | -5,07 | -5,07 | 7 | 003 | 12,93 | -4,00 | 8 | 005 | 26,08 | 0,00 | 9 | 005 | -10,81 | -16,00 | 10 | 003 | 5,73 | 0,00 | 11 | 003 | 1,89 | -5,47 | 12 | 004 | 9,58 | 0,00| | 1 | 003 | -1,12 | -1,12 | 2 | 002 | 4,88 | 0,00 | 3 | 003 | 2,74 | -2,17 | 4 | 001 | -1,71 | -1,71 | 5 | 006 | 2,29 | -8,00 | 6 | 004 | 11,93 | -8,00 | 7 | 004 | 4,27 | -4,00 | 8 | 002 | 0,85 | -0,98 | 9 | 003 | 8,58 | -0,79 | 10 | 003 | 7,87 | 0,00 | 11 | 003 | 10,71 | 0,00 | 12 | 003 | -3,05 | -3,05| | 1 | 002 | 5,30 | 0,00 | 2 | 003 | 3,03 | -2,03 | 3 | 003 | 2,96 | -0,92 | 4 | 004 | -6,64 | -6,64 | 5 | 004 | 19,27 | 0,00
    test("NVTK on 1 1"){ new SimpleTest("NVTK", 40, 1, 1).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
//    Max Williams A/D | 6|7|1|op:sell p: 9,00 s: 4,00 и Min Williams A/D | 6|7|2|op:buy  p:12,00 s: 4,00 | 2010|020|020|  46,20|0,00|-12,00|2011|020|022|  60,09|-11,17| -8,00|2012|018|015|  46,31|-2,50| -9,57|2013|010|005|  25,73|0,00| -8,00 |  | 1 | 002 | 12,45 | 0,00 | 2 | 002 | 7,56 | 0,00 | 3 | 004 | -5,06 | -9,37 | 4 | 003 | 14,00 | -4,00 | 5 | 004 | 26,84 | -4,00 | 6 | 005 | -2,32 | -12,00 | 7 | 002 | 1,23 | 0,00 | 8 | 003 | 3,05 | -4,00 | 9 | 004 | -6,01 | -8,00 | 10 | 002 | -1,95 | -1,95 | 11 | 004 | -5,42 | -8,00 | 12 | 005 | 1,84 | 0,00| | 1 | 003 | 1,35 | -1,39 | 2 | 003 | -3,89 | -3,89 | 3 | 004 | -1,96 | -8,00 | 4 | 003 | -2,10 | -2,10 | 5 | 004 | 4,62 | -4,58 | 6 | 003 | 5,71 | 0,00 | 7 | 004 | 7,93 | -8,00 | 8 | 005 | 23,43 | 0,00 | 9 | 004 | -0,92 | -4,00 | 10 | 003 | 8,13 | 0,00 | 11 | 003 | 1,62 | -5,47 | 12 | 003 | 16,17 | 0,00| | 1 | 003 | -2,50 | -2,50 | 2 | 002 | 8,62 | 0,00 | 3 | 003 | 6,16 | -2,17 | 4 | 001 | -4,00 | -4,00 | 5 | 004 | 3,88 | -4,00 | 6 | 004 | 23,91 | 0,00 | 7 | 003 | 7,39 | 0,00 | 8 | 002 | 0,85 | -0,98 | 9 | 003 | 7,79 | -0,79 | 10 | 003 | -9,57 | -9,57 | 11 | 003 | 7,06 | -0,10 | 12 | 002 | -3,29 | -3,29| | 1 | 003 | 10,82 | 0,00 | 2 | 003 | 4,53 | -1,27 | 3 | 002 | 3,00 | 0,00 | 4 | 003 | -5,73 | -8,00 | 5 | 004 | 13,11 | -3,34
    test("NVTK on 1 2"){ new SimpleTest("NVTK", 46, 1, 2).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
//    Max Williams A/D | 6|6|1|op:sell p:13,00 s: 4,00 и Min Williams A/D | 6|6|3|op:buy  p:13,00 s: 4,00 | 2010|022|017|  42,76|0,00|-12,00|2011|024|019|  46,66|-22,79|-10,98|2012|023|014|  59,59|-0,00| -3,27|2013|009|006|  30,31|0,00| -9,79 |  | 1 | 002 | 10,43 | 0,00 | 2 | 002 | 17,20 | 0,00 | 3 | 003 | 13,32 | 0,00 | 4 | 003 | 4,66 | -1,54 | 5 | 004 | 3,79 | -4,00 | 6 | 005 | -4,52 | -12,00 | 7 | 002 | -0,60 | -0,91 | 8 | 003 | 0,75 | -4,00 | 9 | 004 | -5,15 | -8,00 | 10 | 002 | -1,63 | -1,63 | 11 | 005 | 3,88 | -8,00 | 12 | 004 | 0,64 | -4,00| | 1 | 003 | -4,32 | -4,32 | 2 | 004 | -0,19 | -0,19 | 3 | 004 | -3,30 | -8,00 | 4 | 004 | -10,98 | -10,98 | 5 | 004 | 5,38 | -4,00 | 6 | 004 | -0,66 | -0,66 | 7 | 003 | 6,39 | -4,00 | 8 | 004 | 29,53 | 0,00 | 9 | 003 | 13,87 | -4,00 | 10 | 004 | -0,73 | -0,89 | 11 | 002 | 3,42 | -1,73 | 12 | 004 | 8,26 | 0,00| | 1 | 003 | 3,28 | -0,00 | 2 | 003 | 8,06 | 0,00 | 3 | 003 | -0,25 | -1,49 | 4 | 003 | -3,27 | -3,27 | 5 | 004 | 2,60 | 0,00 | 6 | 004 | 26,56 | 0,00 | 7 | 004 | 19,26 | 0,00 | 8 | 003 | -1,17 | -2,60 | 9 | 002 | 3,91 | 0,00 | 10 | 003 | -1,10 | -1,10 | 11 | 002 | 2,47 | 0,00 | 12 | 003 | -0,75 | -0,75| | 1 | 003 | 9,26 | 0,00 | 2 | 003 | 4,15 | -1,37 | 3 | 002 | 4,21 | 0,00 | 4 | 004 | -9,79 | -9,79 | 5 | 003 | 22,48 | 0,00
    test("NVTK on 1 3"){ new SimpleTest("NVTK", 42, 1, 3).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
//    Max Williams A/D | 6|5|2|op:sell p: 8,00 s: 4,00 и Min Williams A/D | 6|7|1|op:buy  p:11,00 s: 4,00 | 2010|024|021|  33,42|0,00|-10,49|2011|026|025|  32,14|-12,56|-12,61|2012|024|013|  51,60|-6,01| -8,00|2013|011|004|  26,74|0,00| -6,35 |  | 1 | 001 | 2,92 | 0,00 | 2 | 005 | 28,49 | 0,00 | 3 | 004 | -0,66 | -5,07 | 4 | 003 | 5,78 | -2,21 | 5 | 006 | 16,84 | -8,00 | 6 | 004 | -10,49 | -10,49 | 7 | 003 | 7,07 | 0,00 | 8 | 003 | 0,77 | -4,00 | 9 | 004 | -5,65 | -9,30 | 10 | 003 | -6,18 | -6,18 | 11 | 005 | 3,95 | -8,00 | 12 | 004 | -9,42 | -9,42| | 1 | 004 | 7,85 | 0,00 | 2 | 004 | 3,01 | 0,00 | 3 | 005 | -12,61 | -12,61 | 4 | 003 | -4,02 | -4,02 | 5 | 004 | 2,57 | -4,80 | 6 | 004 | -5,35 | -5,35 | 7 | 004 | 11,68 | -4,00 | 8 | 005 | 18,61 | 0,00 | 9 | 004 | 12,76 | -2,24 | 10 | 006 | -7,54 | -8,55 | 11 | 003 | 9,98 | 0,00 | 12 | 005 | -4,80 | -12,00| | 1 | 002 | 2,06 | 0,00 | 2 | 003 | 3,74 | -4,00 | 3 | 004 | -5,87 | -5,87 | 4 | 001 | 2,07 | 0,00 | 5 | 006 | 3,32 | -8,00 | 6 | 004 | 8,39 | -8,00 | 7 | 004 | 24,93 | 0,00 | 8 | 003 | -2,53 | -3,01 | 9 | 003 | 4,52 | 0,00 | 10 | 003 | 2,15 | 0,00 | 11 | 003 | 7,53 | 0,00 | 12 | 001 | 1,30 | 0,00| | 1 | 003 | 1,76 | 0,00 | 2 | 003 | 4,00 | 0,00 | 3 | 002 | 2,71 | 0,00 | 4 | 003 | -4,08 | -6,35 | 5 | 004 | 22,36 | 0,00
    test("NVTK on 2 1"){ new SimpleTest("NVTK", 32, 2, 1).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
//    Max Williams A/D | 3|4|2|op:sell p:12,00 s: 4,00 и Min Williams A/D | 6|6|2|op:buy  p:11,00 s: 4,00 | 2010|032|023|  45,58|-5,51|-11,48|2011|029|025|  41,23|-26,29|-12,00|2012|031|019|  73,00|0,00| -7,14|2013|012|009|  31,14|-1,74| -5,65 |  | 1 | 004 | -5,51 | -5,51 | 2 | 004 | 27,66 | 0,00 | 3 | 006 | -7,26 | -11,48 | 4 | 003 | 6,33 | 0,00 | 5 | 005 | 9,19 | -4,00 | 6 | 006 | 12,45 | -6,40 | 7 | 004 | 2,67 | 0,00 | 8 | 004 | 7,57 | 0,00 | 9 | 005 | -0,18 | -6,31 | 10 | 003 | -4,92 | -4,92 | 11 | 005 | -10,00 | -10,07 | 12 | 006 | 7,58 | 0,00| | 1 | 003 | 1,87 | -1,62 | 2 | 004 | -3,87 | -3,87 | 3 | 005 | -6,71 | -12,00 | 4 | 005 | -11,89 | -11,89 | 5 | 004 | 6,76 | -4,00 | 6 | 004 | -8,46 | -8,46 | 7 | 004 | 11,04 | -4,00 | 8 | 006 | 15,08 | -4,00 | 9 | 005 | 38,33 | 0,00 | 10 | 005 | -2,18 | -4,86 | 11 | 005 | 3,58 | -1,38 | 12 | 004 | -2,32 | -8,00| | 1 | 004 | 0,06 | 0,00 | 2 | 003 | 5,73 | 0,00 | 3 | 005 | 2,68 | -0,86 | 4 | 003 | 13,43 | 0,00 | 5 | 005 | 1,53 | -4,00 | 6 | 004 | 18,02 | 0,00 | 7 | 005 | 20,48 | -4,00 | 8 | 005 | -0,92 | -3,05 | 9 | 003 | 8,66 | 0,00 | 10 | 005 | 1,52 | -0,11 | 11 | 006 | 0,90 | -7,14 | 12 | 002 | 0,90 | 0,00| | 1 | 004 | -0,84 | -1,74 | 2 | 004 | 9,10 | 0,00 | 3 | 004 | 2,93 | -0,91 | 4 | 003 | -5,65 | -5,65 | 5 | 005 | 29,60 | 0,00 | 6 | 001 | -4,00 | -4,00
    test("NVTK on 2 2"){ new SimpleTest("NVTK", 41, 2, 2).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
//    Max Williams A/D | 3|7|2|op:sell p: 8,00 s: 4,00 и Min Williams A/D | 6|6|3|op:buy  p:11,00 s: 4,00 | 2010|021|020|  38,25|-4,00|-12,00|2011|024|024|  49,98|-7,53|-12,92|2012|024|017|  39,93|0,00| -6,82|2013|009|007|  20,02|0,00| -9,51 |  | 1 | 002 | -2,11 | -4,00 | 2 | 003 | 24,72 | 0,00 | 3 | 004 | 10,09 | 0,00 | 4 | 002 | 0,08 | -4,00 | 5 | 005 | 13,30 | -4,00 | 6 | 005 | -2,70 | -12,00 | 7 | 002 | 1,11 | 0,00 | 8 | 003 | 9,02 | 0,00 | 9 | 003 | -4,71 | -8,00 | 10 | 004 | -9,95 | -9,95 | 11 | 004 | -5,45 | -8,00 | 12 | 004 | 4,84 | 0,00| | 1 | 003 | 14,40 | 0,00 | 2 | 004 | 2,18 | 0,00 | 3 | 005 | -7,19 | -12,00 | 4 | 004 | -12,92 | -12,92 | 5 | 004 | 5,38 | -4,00 | 6 | 003 | 2,94 | 0,00 | 7 | 003 | 5,07 | -4,00 | 8 | 005 | 19,82 | -4,00 | 9 | 006 | 15,00 | -12,00 | 10 | 005 | -6,98 | -6,98 | 11 | 003 | 11,61 | 0,00 | 12 | 003 | 0,68 | -4,12| | 1 | 003 | 4,25 | 0,00 | 2 | 003 | 9,87 | 0,00 | 3 | 003 | -1,28 | -2,17 | 4 | 003 | 0,91 | -1,13 | 5 | 004 | 26,00 | 0,00 | 6 | 006 | -0,76 | -0,76 | 7 | 004 | -2,49 | -6,53 | 8 | 003 | -2,72 | -4,00 | 9 | 002 | 4,82 | -0,39 | 10 | 004 | 6,78 | 0,00 | 11 | 003 | -4,84 | -6,82 | 12 | 003 | -0,61 | -0,61| | 1 | 003 | 9,35 | 0,00 | 2 | 003 | 4,43 | -1,37 | 3 | 002 | 1,93 | 0,00 | 4 | 004 | -9,51 | -9,51 | 5 | 003 | 17,82 | 0,00 | 6 | 001 | -4,00 | -4,00
    test("NVTK on 2 3"){ new SimpleTest("NVTK", 38, 2, 3).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
//    Max Williams A/D | 3|2|3|op:sell p:13,00 s: 4,00 и Min Williams A/D | 6|7|1|op:buy  p:13,00 s: 4,00 | 2010|032|039|  34,67|-6,26|-10,11|2011|041|027|  32,43|-15,27|-10,28|2012|035|024|  66,58|-2,28| -5,80|2013|015|010|  15,16|0,00| -5,50 |  | 1 | 003 | -5,16 | -5,16 | 2 | 006 | 18,68 | -1,10 | 3 | 007 | -1,53 | -8,44 | 4 | 006 | 2,57 | -0,46 | 5 | 006 | 26,08 | -8,00 | 6 | 007 | -8,33 | -8,33 | 7 | 005 | 6,92 | 0,00 | 8 | 004 | -0,43 | -4,00 | 9 | 009 | -3,48 | -8,06 | 10 | 004 | -1,16 | -1,16 | 11 | 007 | 2,89 | -10,11 | 12 | 007 | -2,38 | -4,00| | 1 | 005 | 4,88 | -1,60 | 2 | 004 | -4,46 | -4,46 | 3 | 007 | -10,28 | -10,28 | 4 | 005 | -0,61 | -0,61 | 5 | 004 | 2,57 | -4,80 | 6 | 005 | -4,24 | -4,24 | 7 | 006 | 8,40 | -2,67 | 8 | 008 | 14,02 | 0,00 | 9 | 005 | -0,04 | -7,26 | 10 | 006 | 15,03 | 0,00 | 11 | 005 | 8,67 | 0,00 | 12 | 008 | -1,52 | -4,34| | 1 | 004 | -1,38 | -1,38 | 2 | 005 | 8,06 | -0,90 | 3 | 005 | 0,26 | -4,31 | 4 | 002 | 6,19 | 0,00 | 5 | 008 | 7,50 | 0,00 | 6 | 005 | 17,20 | 0,00 | 7 | 006 | 15,77 | 0,00 | 8 | 006 | -3,53 | -5,80 | 9 | 004 | 1,23 | -2,07 | 10 | 005 | 5,14 | 0,00 | 11 | 006 | 10,77 | 0,00 | 12 | 003 | -0,63 | -0,63| | 1 | 006 | 3,37 | 0,00 | 2 | 004 | -0,63 | -0,63 | 3 | 005 | 11,65 | 0,00 | 4 | 004 | -3,23 | -5,50 | 5 | 005 | 8,01 | -1,20 | 6 | 001 | -4,00 | -4,00
//    Max Williams A/D | 6|7|3|op:sell p:10,00 s: 4,00 и Min Williams A/D | 6|7|1|op:buy  p:13,00 s: 4,00 | 2010|018|021|  31,95|-0,27|-10,16|2011|020|022|  38,44|-28,31|-17,92|2012|018|013|  49,71|-3,46| -8,00|2013|008|006|  15,43|0,00| -4,67 |  | 1 | 001 | -0,27 | -0,27 | 2 | 003 | 22,71 | 0,00 | 3 | 004 | 2,81 | -4,00 | 4 | 002 | 20,00 | 0,00 | 5 | 002 | 7,24 | 0,00 | 6 | 004 | 1,68 | -8,00 | 7 | 003 | 0,65 | -0,03 | 8 | 004 | -4,84 | -4,84 | 9 | 004 | -6,73 | -8,00 | 10 | 003 | -8,72 | -8,72 | 11 | 005 | 7,58 | -8,00 | 12 | 004 | -10,16 | -10,16| | 1 | 002 | -1,39 | -1,39 | 2 | 002 | -2,88 | -2,88 | 3 | 005 | -15,76 | -15,76 | 4 | 002 | -3,48 | -3,48 | 5 | 004 | 2,57 | -4,80 | 6 | 003 | 5,71 | 0,00 | 7 | 005 | 4,93 | -8,00 | 8 | 004 | 28,43 | 0,00 | 9 | 005 | -17,92 | -17,92 | 10 | 003 | 16,75 | 0,00 | 11 | 003 | 8,43 | -4,00 | 12 | 004 | 13,06 | 0,00| | 1 | 002 | -3,46 | -3,46 | 2 | 002 | 5,81 | 0,00 | 3 | 003 | 5,56 | 0,00 | 4 | 001 | 2,07 | 0,00 | 5 | 005 | 17,04 | -8,00 | 6 | 003 | 16,03 | -4,00 | 7 | 003 | 4,71 | -2,53 | 8 | 003 | -2,54 | -2,54 | 9 | 003 | -5,71 | -8,00 | 10 | 002 | 10,30 | 0,00 | 11 | 002 | 0,10 | 0,00 | 12 | 002 | -0,18 | -0,18| | 1 | 002 | 5,97 | 0,00 | 2 | 002 | -2,82 | -2,82 | 3 | 003 | 10,54 | 0,00 | 4 | 003 | -4,67 | -4,67 | 5 | 004 | 6,41 | 0,00
//    Max Williams A/D | 6|7|3|op:sell p: 9,00 s: 4,00 и Min Williams A/D | 9|7|1|op:buy  p:10,00 s: 4,00 | 2010|018|022|  30,10|-0,27|-10,16|2011|020|023|  34,04|-17,78|-16,00|2012|018|013|  48,23|-3,46| -8,00|2013|008|006|  17,81|0,00| -4,67 |  | 1 | 001 | -0,27 | -0,27 | 2 | 003 | 21,71 | 0,00 | 3 | 003 | 3,20 | -4,00 | 4 | 003 | 14,00 | -4,00 | 5 | 003 | 15,00 | -4,00 | 6 | 004 | 1,68 | -8,00 | 7 | 003 | 0,65 | -0,03 | 8 | 004 | -4,84 | -4,84 | 9 | 004 | -6,73 | -8,00 | 10 | 003 | -8,72 | -8,72 | 11 | 005 | 4,58 | -8,00 | 12 | 004 | -10,16 | -10,16| | 1 | 002 | -1,39 | -1,39 | 2 | 002 | -2,88 | -2,88 | 3 | 005 | -5,23 | -8,00 | 4 | 002 | -3,48 | -3,48 | 5 | 004 | 4,84 | -4,80 | 6 | 003 | 5,71 | 0,00 | 7 | 005 | 1,93 | -8,00 | 8 | 004 | 31,27 | 0,00 | 9 | 005 | -6,00 | -16,00 | 10 | 004 | -11,23 | -11,23 | 11 | 003 | 8,43 | -4,00 | 12 | 004 | 12,06 | 0,00| | 1 | 002 | -3,46 | -3,46 | 2 | 002 | 8,31 | 0,00 | 3 | 003 | 5,56 | 0,00 | 4 | 001 | -2,48 | -2,48 | 5 | 005 | 13,88 | -8,00 | 6 | 003 | 13,03 | -4,00 | 7 | 003 | 12,42 | 0,00 | 8 | 003 | -2,54 | -2,54 | 9 | 003 | -5,71 | -8,00 | 10 | 002 | 9,30 | 0,00 | 11 | 002 | 0,10 | 0,00 | 12 | 002 | -0,18 | -0,18| | 1 | 002 | 5,97 | 0,00 | 2 | 002 | -2,82 | -2,82 | 3 | 003 | 10,54 | 0,00 | 4 | 003 | -4,67 | -4,67 | 5 | 003 | 12,79 | 0,00 | 6 | 001 | -4,00 | -4,00
    test("NVTK on 3 1"){ new SimpleTest("NVTK", 30, 3, 1).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
//    Max Williams A/D | 3|2|3|op:sell p: 8,00 s: 4,00 и Min Williams A/D | 6|6|2|op:buy  p: 9,00 s: 4,00 | 2010|037|040|  43,46|-6,26|-10,80|2011|041|030|  44,54|-8,50|-12,00|2012|036|028|  55,33|-1,14| -6,01|2013|016|009|  21,02|0,00| -4,07 |  | 1 | 003 | -5,16 | -5,16 | 2 | 007 | 22,48 | -1,10 | 3 | 006 | 12,33 | 0,00 | 4 | 006 | 2,57 | -0,46 | 5 | 006 | 11,04 | -4,00 | 6 | 009 | 7,15 | -6,77 | 7 | 006 | 3,25 | 0,00 | 8 | 006 | -2,35 | -4,96 | 9 | 009 | -1,96 | -8,06 | 10 | 005 | -0,66 | -0,66 | 11 | 006 | -10,74 | -10,80 | 12 | 008 | 5,51 | 0,00| | 1 | 004 | 6,88 | 0,00 | 2 | 005 | -3,38 | -3,38 | 3 | 007 | -2,73 | -12,00 | 4 | 005 | 0,23 | -0,44 | 5 | 004 | 7,31 | -4,00 | 6 | 005 | -5,56 | -5,56 | 7 | 006 | 9,27 | -2,67 | 8 | 009 | 22,63 | 0,00 | 9 | 006 | -1,63 | -5,63 | 10 | 008 | 4,32 | -4,00 | 11 | 006 | 2,66 | -0,39 | 12 | 006 | 4,54 | -1,71| | 1 | 004 | 2,50 | -0,37 | 2 | 006 | 4,73 | -3,64 | 3 | 005 | -3,00 | -4,31 | 4 | 003 | 1,25 | 0,00 | 5 | 009 | 18,08 | 0,00 | 6 | 005 | 13,09 | 0,00 | 7 | 007 | 13,66 | -4,00 | 8 | 007 | -1,54 | -5,80 | 9 | 004 | 3,26 | -0,36 | 10 | 004 | 0,35 | 0,00 | 11 | 007 | 3,58 | -6,01 | 12 | 003 | -0,63 | -0,63| | 1 | 006 | 5,92 | 0,00 | 2 | 004 | -0,93 | -0,93 | 3 | 005 | 12,21 | 0,00 | 4 | 004 | -4,07 | -4,07 | 5 | 005 | 11,88 | 0,00 | 6 | 001 | -4,00 | -4,00
    test("NVTK on 3 2"){ new SimpleTest("NVTK", 42, 3, 2).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
//    Max Williams A/D | 6|5|3|op:sell p:10,00 s: 4,00 и Min Williams A/D | 6|7|3|op:buy  p:11,00 s: 4,00 | 2010|024|017|  54,49|-0,54|-10,90|2011|021|025|  35,88|-20,95|-12,00|2012|024|012|  34,07|-4,16| -6,56|2013|007|008|  18,43|0,00| -8,00 |  | 1 | 001 | -0,54 | -0,54 | 2 | 003 | 24,39 | 0,00 | 3 | 004 | 16,17 | 0,00 | 4 | 003 | 13,59 | -4,00 | 5 | 004 | 11,30 | -4,00 | 6 | 003 | 0,57 | -4,00 | 7 | 003 | 2,65 | 0,00 | 8 | 003 | 0,10 | -4,00 | 9 | 004 | -8,40 | -9,39 | 10 | 004 | -10,90 | -10,90 | 11 | 005 | 3,95 | -8,00 | 12 | 004 | 1,61 | -4,00| | 1 | 003 | -5,23 | -5,23 | 2 | 003 | 0,58 | -3,12 | 3 | 005 | -7,42 | -12,00 | 4 | 004 | -4,08 | -4,08 | 5 | 004 | 3,20 | -4,80 | 6 | 003 | 4,77 | 0,00 | 7 | 005 | 1,09 | -8,00 | 8 | 005 | 23,85 | 0,00 | 9 | 003 | 3,00 | -8,00 | 10 | 004 | 4,30 | -4,11 | 11 | 003 | 4,03 | -4,00 | 12 | 004 | 7,78 | 0,00| | 1 | 003 | 3,06 | -0,56 | 2 | 003 | 2,97 | 0,00 | 3 | 003 | -1,63 | -1,96 | 4 | 003 | -5,18 | -6,56 | 5 | 004 | -3,38 | -3,38 | 6 | 004 | 22,67 | 0,00 | 7 | 004 | 7,16 | 0,00 | 8 | 003 | -0,36 | -2,06 | 9 | 002 | 4,34 | 0,00 | 10 | 003 | -3,08 | -3,08 | 11 | 002 | 3,94 | 0,00 | 12 | 002 | 3,56 | 0,00| | 1 | 003 | 2,73 | 0,00 | 2 | 002 | -0,82 | -0,82 | 3 | 003 | 6,92 | 0,00 | 4 | 003 | -5,73 | -8,00 | 5 | 004 | 15,32 | -2,44
    test("NVTK on 3 3"){ new SimpleTest("NVTK", 34, 3, 3).standardTest(stopMultiplierSteps = 2, stopMultiplierStep = 2, takeProfitStart = 8) }
}