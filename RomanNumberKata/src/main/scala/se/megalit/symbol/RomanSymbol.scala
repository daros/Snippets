package se.megalit.symbol

/**
 * Created by IntelliJ IDEA.
 * User: David Rosell - Redpill-Linpro
 * Date: Jul 6, 2010
 * Time: 10:57:15 AM
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

object RomanNumber {
  def apply(arabicNumber: Int) = new RomanNumber(arabicNumber)
}

class RomanNumber(val arabicNumber: Int) {
  import RomanSymbol._

  def toSymbol = arabicNumber match {
    case i if i < 10 => baseList(i)
    case _ => List(Undefined)
  }


  override def toString = toSymbol.mkString
}