package tradingsystems

/**
 * @author alespuh
 * @since 08.07.13
 */
object AccumulatedProfit
{
//    final val Accumulator = SummedProfit
    final val Accumulator = MultipliedProfit
}

trait ProfitBase
{
    val profit: Double
    val profitPct = profit * 100
    val slump: Double
    val slumpPct = slump * 100
}

trait AccumulatedProfit extends ProfitBase
{
    val profits: Vector[Profit]
}

//исходим из того, что торгуем все время постоянной суммой и заработанное откладываем, а потерянное восполняем
case class SummedProfit(profits: Vector[Profit]) extends AccumulatedProfit
{
    override lazy val (profit, slump) = profits.foldLeft((0.0, 0.0))
    {
        case((tempProfit, tempSlumpPct), p) =>
            val newProfit = tempProfit + p.profit
            (newProfit, java.lang.Math.min(newProfit, tempSlumpPct))
    }
}

//исходим из того, что торгуем "на все"
case class MultipliedProfit(profits: Vector[Profit]) extends AccumulatedProfit
{
    override lazy val (profit, slump) =
    {
        val results = profits.foldLeft((1.0, 0.0))
        {
            case((tempProfit, tempSlump), p) =>
                val newProfit = tempProfit * (1 + p.profit)
                (newProfit, java.lang.Math.min(newProfit - 1, tempSlump)) //newProfit - 1 - это мы смотрим на сколько мы просели от первоначальной 1. что и есть просадка
        }

        (results._1 - 1.0, results._2)
    }
}

case class Profit(start: Double, change: Double) extends ProfitBase
{
    override lazy val profit = change / start
    override lazy val slump = profit
}