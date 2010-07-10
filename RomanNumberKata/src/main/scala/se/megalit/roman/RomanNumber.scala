package se.megalit.roman
import RomanNumber._

/**
 * Roman number code-kata.
 * Map arabic numbers to roman numbers.
 *
 * @author <a href="mailto:wmdaros@gmail.com">David Rosell</a>
 */

object RomanNumber {
  val I = new RomanNumber(1) with Shiftable
  val V = new RomanNumber(5) with Shiftable
  val X = new RomanNumber(10) with Shiftable
  val L = new RomanNumber(50)
  val C = new RomanNumber(100)
  val D = new RomanNumber(500)
  val M = new RomanNumber(1000)

  def apply(arab: Int): RomanNumber = arab match {
    case 1 => I
    case 5 => V
    case 10 => X
    case i => new RomanNumber(i)
  }

  private[roman] def baseList(i: Int): List[RomanNumber] = i match {
    case 0 => List()           // Nil
    case 1 => List(I)          // I :: Nil
    case 2 => List(I, I)       // I :: I :: Nil
    case 3 => List(I, I, I)    // I :: I :: I :: Nil
    case 4 => List(I, V)       // I :: V :: Nil
    case 5 => List(V)          // V :: Nil
    case 6 => List(V, I)       // V :: I :: Nil
    case 7 => List(V, I, I)    // V :: I :: I :: Nil
    case 8 => List(V, I, I, I) // V :: I :: I :: I :: Nil
    case 9 => List(I, X)       // I :: X :: Nil
  }

  private[roman] def shiftSymbol(r: RomanNumber, step: Int) = r match {
    case s: Shiftable => s.shift(step)
    case _ => r
  }

  def main(args: Array[String]) {
    for (i <- List.range(0,4000,5)) {
      println
      for (j <- 0 to 4) {
        printf("%4s -> %15s ", i+j, RomanNumber(i+j).toString)
      }
    } 
  }
}

private[roman] class RomanNumber(val arabicNumeral: Int) {
  val shift1 = shiftSymbol(_: RomanNumber, 1)
  val shift2 = shiftSymbol(_: RomanNumber, 2)
  val shift3 = shiftSymbol(_: RomanNumber, 3)

  def toList: List[RomanNumber] = arabicNumeral match {
    case i if arabicNumeral < 10   => baseList(i)
    case i if arabicNumeral < 100  => baseList(i / 10).map(shift1)   ::: baseList(i % 10)
    case i if arabicNumeral < 1000 => baseList(i / 100).map(shift2)  ::: baseList(i % 100 / 10).map(shift1)   ::: baseList(i % 10)
    case i if arabicNumeral < 4000 => baseList(i / 1000).map(shift3) ::: baseList(i % 1000 / 100).map(shift2) ::: baseList(i % 100 / 10).map(shift1) ::: baseList(i % 10)
  }

  def toInt: Int = {
    arabicNumeral
  }

  override def toString: String = arabicNumeral match {
    case    0 => ""
    case    1 => "I"
    case    5 => "V"
    case   10 => "X"
    case   50 => "L"
    case  100 => "C"
    case  500 => "D"
    case 1000 => "M"
    case _ if arabicNumeral < 4000 => this.toList mkString
    case _ => "-undefined-"
  }
}

trait Shiftable {
  def shift(step: Int): RomanNumber = {
    val shiftArray = List[RomanNumber](I, V, X, L, C, D, M)
    shiftArray(shiftArray.indexOf(this) + step * 2)
  }
}