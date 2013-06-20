package util

import scala.collection.{GenTraversableLike, GenTraversableOnce}

object TradingImplicits
{
    implicit def toAnyImplicits[T](obj: T) = new AnyImplicits(obj)

    implicit def toSeqImplicits[T](seq: GenTraversableOnce[T]) = new SeqImplicits[T](seq)
}

class AnyImplicits[T](obj: T)
{
    def mapOr[B](f: T => B, dfv: => B) = Option(obj).map(f).getOrElse(dfv)
}

class SeqImplicits[T](seq: GenTraversableOnce[T])
{
    def avg(f: T => Double) = seq.toList.map(f).sum / seq.size
}