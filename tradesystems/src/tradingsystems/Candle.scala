package tradingsystems

import org.joda.time.LocalDate

case class Candle(date: LocalDate, open: Double, high: Double, low:Double, close:Double, volume: Int = 0)
{
    //dosarTODO: подразумевается, что сюда потом добавится комиссия
    lazy val buyProfitPct: Double = (close/open - 1) * 100
    lazy val sellProfitPct: Double = (1 - close/open) * 100
    lazy val buySlumpPct = (1 - low/open) * 100 //на сколько просели от покупки
    lazy val sellSlumpPct = (high/open - 1) * 100 //на сколько просели от продажи
    lazy val buyProfit: Double = close - open
    lazy val sellProfit: Double = open - close
    lazy val buySlump = open - low //на сколько просели от покупки
    lazy val sellSlump = high - open //на сколько просели от продажи
    lazy val volatility = high - low

    lazy val sellString = toString + ("sellProfit", sellProfit) + ("sellSlump", sellSlump)
    lazy val buyString = toString + ("buyProfit", buyProfit) + ("buySlump", buySlump)
}


