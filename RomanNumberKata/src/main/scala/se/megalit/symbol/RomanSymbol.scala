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

  def toSymbol = buildList(arabicNumber, List())

  private implicit def intToList(i: Int): List[Int] = i.toString.toList.map(_.toInt - 48)
//  private implicit def intToList(i: Int): List[Int] = """\d""".r.findAllIn(i.toString).map(_.toInt).toList

  private def buildList(num: List[Int], res: List[RomanSymbol.Value]): List[RomanSymbol.Value] = 
    num match {
      case List() => res
      case x :: xs => buildList(xs, res.map(shift) ::: baseList(x))
    }

  override def toString = toSymbol.mkString
}