package tradingsystems

import org.joda.time.LocalDate

case class Candle(date: LocalDate, open: Double, high: Double, low:Double, close:Double)
{
    import tradingsystems.CandleCalculator._
    //dosarTODO: подразумевается, что сюда потом добавится комиссия
    lazy val buyProfit: Profit = close - open
    lazy val sellProfit: Profit = open - close
    lazy val buySlump = open - low //на сколько просели от покупки
    lazy val sellSlump = high - open //на сколько просели от продажи

    lazy val sellString = toString + ("sellProfit", sellProfit) + ("sellSlump", sellSlump)
    lazy val buyString = toString + ("buyProfit", buyProfit) + ("buySlump", buySlump)
}


