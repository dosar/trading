package util

import scala.collection.{GenTraversableLike, GenTraversableOnce}

object TradingImplicits
{
    implicit def toAnyImplicits[T](obj: T) = new AnyImplicits(obj)

    implicit def toSeqImplicits[T](seq: IndexedSeq[T]) = new SeqImplicits[T](seq)

    implicit def toVectorImplicits[T](vector: Vector[T]) = new VectorImplicits[T](vector)

    implicit def toDoubleImplicits(d: Double) = new DoubleImplicits(d)
}

class DoubleImplicits(d: Double)
{
    def roundP(precision: Int) =
    {
        val pow = math.pow(10, precision)
        (d * pow).round / pow
    }
}

class AnyImplicits[T](obj: T)
{
    def mapOr[B](f: T => B, dfv: => B) = Option(obj).map(f).getOrElse(dfv)
}

class VectorImplicits[T](vector: Vector[T])
{
    def sumWith(anotherVector: Vector[T], elementSummator: (T, T) => T, default: => T): Vector[T] =
    {
        for((left, right) <- vector.zipAll(anotherVector, default, default)) yield elementSummator(left, right)
    }
}

class SeqImplicits[T](seq: IndexedSeq[T])
{
    def avg(f: T => Double) = sumBy(f) / seq.size

    def sumBy(f: T => Double) = seq.map(f).sum
}