package tradingsystems

/**
 * @author alespuh
 * @since 08.07.13
 */
case class TradingData(data: Vector[Candle])
{
    def this(anotherData: TradingData) = this(anotherData.data)

    type Year = Int
    type Price = Double
}
