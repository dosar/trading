package tradinganalyzers

import tradingsystems.Candle

object TradingOp
{
     def buy(stopPercent: Double, takeProfitPercent: Double) =
         new TradingOp(stopPercent, takeProfitPercent, _.buyProfit, _.buySlump, _.sellSlump, "buy")

     def sell(stopPercent: Double, takeProfitPercent: Double) =
         new TradingOp(stopPercent, takeProfitPercent, _.sellProfit, _.buySlump, _.buySlump, "sell")
}

class TradingOp(stopPercent: Double, takeProfitPercent: Double, candleProfit: Candle => Double,
    slump: Candle => Double, opJump: Candle => Double, val opStr: String)
{
    val stop = stopPercent / 100
    val takeProfit = takeProfitPercent / 100

    def desc(days: Int) =
        "d:" + days + " op:" + opStr + " p:" + takeProfitPercent.formatted("%5.2f") + " s:" + stopPercent

    //dosarTODO:
    // нужно бы еще добавить инфу о том, что было раньше, лоу или хай. потому что от этого зависит срабатывание стопов
    // взависимости от цены открытия,хая,закрытия,лоу и стопов может быть > 0 или < 0

    def profit(candle: Candle): Double =
    {
        //open - low \ high - open
        if(slump(candle) / candle.open >= stop) -stop * candle.open
        //high - open \ open - low
        else if(opJump(candle) / candle.open >= takeProfit) takeProfit * candle.open
        else candleProfit(candle)
    }

    def ret(candle: Candle) = (candle.close / candle.open) - 1
}