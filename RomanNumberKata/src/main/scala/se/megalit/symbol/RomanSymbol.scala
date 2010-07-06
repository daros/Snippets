package se.megalit.symbol

/**
 * Roman number code-kata.
 * Map arabic numbers to roman numbers.
 *
 * @author <a href="mailto:wmdaros@gmail.com">David Rosell</a>
 */

object RomanSymbol extends Enumeration {
  val Undefined = Value
  val I = Value(1)
  val V = Value(5)
  val X = Value(10)
  val L = Value(50)
  val C = Value(100)
  val D = Value(500)
  val M = Value(1000)

  def shift(symbol: RomanSymbol.Value): RomanSymbol.Value = symbol match {
    case I => X
    case V => L
    case X => C
    case L => D
    case C => M
    case _ => Undefined
  }

  private[symbol] def baseList(i: Int): List[RomanSymbol.Value] = i match {
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
}

case class RomanNumber(val arabicNumber: Int) {
  import RomanSymbol._

  def toSymbol = arabicNumber match {
    case i if i < 10 => baseList(i)
    case i if i < 100 => baseList(i/10).map(shift) ::: baseList(i%10)
    case i if i < 1000 => baseList(i/100).map(shift).map(shift) ::: baseList(i%100/10).map(shift) ::: baseList(i%10)
    case i if i < 3999 => baseList(i/1000).map(shift).map(shift).map(shift) ::: baseList(i%1000/100).map(shift).map(shift) ::: baseList(i%100/10).map(shift) ::: baseList(i%10)
    case _ => List(Undefined)
  }

  override def toString = toSymbol.mkString
}