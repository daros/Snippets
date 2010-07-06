package se.megalit.symbol

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

/**
 * Created by IntelliJ IDEA.
 * User: David Rosell - Redpill-Linpro
 * Date: Jul 6, 2010
 * Time: 11:05:01 AM
 */

class RomanSymbolTest extends FlatSpec with ShouldMatchers {
  import RomanSymbol._

  "RomanSymbol.I" should "equal 1" in {
    RomanSymbol.I.id should equal (1)
    V.id should equal (5)
  }

  "RomanSymbol id" should "match arabic numerals" in {
    val arabicNumerals = Array(1, 5, 10, 50, 100, 500, 1000)
    arabicNumerals.foreach(a => RomanSymbol(a).id should equal (a))
  }

  "X.toString" should "equal X" in {
    X.toString should equal ("X")
  }

  "shift I" should "equal X" in {
    shift(I) should equal (X)
  }

  "RomanNumber(1).toSymbol" should "equal List(I)" in {
    RomanNumber(1).toSymbol should be (List(I))
  }

  "RomanNumber 0 to 9" should "be defined" in {
    for (i <- 0 to 9 ) {
      val symbolList = RomanNumber(i).toSymbol
      symbolList.contains(Undefined) should be (false)
    }
  }

  "RomanNumber 8 toString" should "equal VIII" in {
    RomanNumber(8).toString should be ("VIII")
  }

  "RomanNumber 4000 toString" should "be Undefined" in {
    RomanNumber(4000).toString should be ("Undefined")
  }

  "RomanNumber 4000 toSymbol" should "be List(Undefined)" in {
    RomanNumber(4000).toSymbol should be (List(Undefined))
  }

  "RomanNumber 44 toSymbol" should "be List(X,L,I,V)" in {
    RomanNumber(44).toSymbol should be (List(X, L, I, V))
  }

  "RomanNumber 111 toSymbol" should "be List(C,X,I)" in {
    RomanNumber(111).toSymbol should be (List(C, X, I))
  }

  "RomanNumber 1505" should "be List(M,D,V)" in {
    RomanNumber(1505).toSymbol should be (List(M,D,V))
  }

  "RomanNumber(999).toString" should "equal CMXCIX" in {
    RomanNumber(999).toString should be ("CMXCIX")
  }

}