package tradinganalyzers

import tradingsystems.{ProfitBase, Profit, Candle}

object TradingOp
{
    def buy(stopPercent: Double, takeProfitPercent: Double) =
        new BuyTradingOp(stopPercent, takeProfitPercent)

    def sell(stopPercent: Double, takeProfitPercent: Double) =
        new SellTradingOp(stopPercent, takeProfitPercent)
}

trait TradingOp
{
    //dosarTODO:
    // нужно бы еще добавить инфу о том, что было раньше, лоу или хай. потому что от этого зависит срабатывание стопов
    // взависимости от цены открытия,хая,закрытия,лоу и стопов может быть > 0 или < 0
    val stopPercent: Double
    val takeProfitPercent: Double
    val opStr: String

    val stop = stopPercent / 100
    val takeProfit = takeProfitPercent / 100

    def desc(days: Int): String =
        "d:" + days + " " + desc

    def desc: String =
        "op:" + opStr + " p:" + takeProfitPercent.formatted("%5.2f") + " s:" + stopPercent.formatted("%5.2f")

    def profit(candle: Candle): Profit

    def profit(position: TradingPosition): (Profit, Int)

    def ret(candle: Candle) = (candle.close / candle.open) - 1 //это так для памяти
}

class BuyTradingOp(val stopPercent: Double, val takeProfitPercent: Double) extends TradingOp
{
    val opStr: String = "buy "

    def profit(candle: Candle): Profit =
        if(candle.buySlump / candle.open >= stop) Profit(candle.open, -stop * candle.open)
        else if(candle.sellSlump / candle.open >= takeProfit) Profit(candle.open, takeProfit * candle.open)
        else Profit(candle.open, candle.buyProfit)

    def profit(position: TradingPosition): (Profit, Int) =
    {
        for((candle, index) <- position.candles.zipWithIndex)
        {
            if((position.open - candle.low) / position.open >= stop)
                return (Profit(position.open, -stop * position.open), index)
            if((candle.high - position.open) / position.open >= takeProfit)
                return (Profit(position.open, takeProfit * position.open), index)
        }
        (Profit(position.open, position.close - position.open), position.candles.length - 1)
    }
}

class SellTradingOp(val stopPercent: Double, val takeProfitPercent: Double) extends TradingOp
{
    val opStr: String = "sell"

    def profit(candle: Candle): Profit =
        if(candle.sellSlump / candle.open >= stop) Profit(candle.open, -stop * candle.open)
        else if(candle.buySlump / candle.open >= takeProfit) Profit(candle.open, takeProfit * candle.open)
        else Profit(candle.open, candle.sellProfit)

    def profit(position: TradingPosition): (Profit, Int) =
    {
        for((candle, index) <- position.candles.zipWithIndex)
        {
            if((candle.high - position.open) / position.open >= stop)
                return (Profit(position.open, -stop * position.open), index)
            if((position.open - candle.low) / position.open >= takeProfit)
                return (Profit(position.open, takeProfit * position.open), index)
        }
        (Profit(position.open, position.open - position.close), position.candles.length - 1)
    }
}