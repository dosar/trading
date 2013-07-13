package util

import scala.collection.{GenTraversableLike, GenTraversableOnce}

object TradingImplicits
{
    implicit def toAnyImplicits[T](obj: T) = new AnyImplicits(obj)

    implicit def toSeqImplicits[T](seq: IndexedSeq[T]) = new SeqImplicits[T](seq)
}

class AnyImplicits[T](obj: T)
{
    def mapOr[B](f: T => B, dfv: => B) = Option(obj).map(f).getOrElse(dfv)
}

class SeqImplicits[T](seq: IndexedSeq[T])
{
    def avg(f: T => Double) = sumBy(f) / seq.size

    def sumBy(f: T => Double) = seq.map(f).sum
}