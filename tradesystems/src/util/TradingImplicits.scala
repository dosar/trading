package util

object TradingImplicits
{
    implicit def toAnyImplicits[T](obj: T) = new AnyImplicits(obj)
}

class AnyImplicits[T](obj: T)
{
    def mapOr[B](f: T => B, dfv: => B) = Option(obj).map(f).getOrElse(dfv)
}