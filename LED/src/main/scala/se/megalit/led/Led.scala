package se.megalit.led

/**
 * Created by IntelliJ IDEA.
 * User: David Rosell - Redpill-Linpro
 * Date: Jul 18, 2010
 * Time: 4:46:52 PM
 */

object LedSymbol extends Enumeration {
  val h1 = Value("   ")
  val h2 = Value(" - ")
  val v1 = Value("  |")
  val v2 = Value("|  ")
  val v3 = Value("| |")

  def baseList(i: Int): List[LedSymbol.Value] = i match {
    case 0 => List(h2,v3,h1,v3,h2)
    case 1 => List(h1,v1,h1,v1,h1)
    case 2 => List(h2,v1,h2,v2,h2)
    case 3 => List(h2,v1,h2,v1,h2)
    case 4 => List(h1,v3,h2,v1,h1)
    case 5 => List(h2,v2,h2,v1,h2)
    case 6 => List(h2,v2,h2,v3,h2)
    case 7 => List(h2,v1,h1,v1,h1)
    case 8 => List(h2,v3,h2,v3,h2)
    case 9 => List(h2,v3,h2,v1,h2)
  }

  def main(args: Array[String]) {
    for (i <- 3510 to 3539) {
      Led(i, 2).toLed.foreach(println)
    }
  }
}

case class Led(val number: Int, val size: Int) {
  import LedSymbol._

  private implicit def intToList(i: Int): List[Int] = i.toString.toList.map(_.toInt - 48)

  def toLed: List[String] = buildList(number)

  private def buildList(num: List[Int]): List[String] = {
    val list = Array("","","","","")
    for (numList <- num.map(baseList)) {
      for (i <- 0 to 4) {
        val repl = numList(i) match {
          case LedSymbol.h2 => "-"
          case _ => " "
        }

        val tmp = numList(i).toString.splitAt(1)
        val str = (tmp) match {
          case (a,b) => a + repl*(size-1) + b
        }
        list(i) = list(i) + str
      }
    }
    list.toList
  }
}