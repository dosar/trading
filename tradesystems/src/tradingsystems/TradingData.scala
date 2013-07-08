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

    def avgPrice(year: Int) = avgPrices(year)

    private lazy val avgPrices: Map[Year, Price] = data.groupBy(_.date.getYear)
        .map{case (year, yearCandles) => (year, yearCandles.map(_.open).sum / yearCandles.length)}
}
