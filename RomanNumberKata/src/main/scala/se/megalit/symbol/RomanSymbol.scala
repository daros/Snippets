package se.megalit.symbol

/**
 * Roman number code-kata.
 * Map arabic numbers to roman numbers.
 *
 * @author <a href="mailto:wmdaros@gmail.com">David Rosell</a>
 */

object RomanSymbol extends Enumeration {
  val I = Value
  val V = Value
  val X = Value
  val L = Value
  val C = Value
  val D = Value
  val M = Value
  val R5000 = Value("\u2181")
  val R10000 = Value("\u2182")
  val ? = Value("?")

  private[symbol] def shift(symbol: RomanSymbol.Value): RomanSymbol.Value = symbol match {
    case s if s.id < RomanSymbol.nextId - 3 => RomanSymbol(s.id + 2)
    case _ => ?
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

  def toSymbol = buildList(arabicNumber.toString.reverse, List())

  private def buildList(num: String, list: List[RomanSymbol.Value]): List[RomanSymbol.Value] = num match {
      case i if i.length == 0 => list
      case i => buildList(num.init, list.map(shift) ::: baseList(i.toInt%10))
  }

  override def toString = toSymbol.mkString
}