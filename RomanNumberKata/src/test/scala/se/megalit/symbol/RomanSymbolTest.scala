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
      symbolList.contains(?) should be (false)
    }
  }

  "RomanNumber 8 toString" should "equal VIII" in {
    RomanNumber(8).toString should be ("VIII")
  }

//  "RomanNumber 4000 toString" should "be M?" in {
//    RomanNumber(4000).toString should be ("M?")
//  }
//
//  "RomanNumber 4000 toSymbol" should "be List(M,?)" in {
//    RomanNumber(4000).toSymbol should be (List(M, ?))
//  }

  "RomanNumber 44 toSymbol" should "be List(X,L,I,V)" in {
    RomanNumber(44).toSymbol should be (List(X, L, I, V))
  }

  "RomanNumber 111 toSymbol" should "be List(C,X,I)" in {
    RomanNumber(111).toSymbol should be (List(C, X, I))
  }

  "RomanNumber 123 toSymbol" should "be List(C,X,X,I,I,I)" in {
    RomanNumber(123).toSymbol should be(List(C, X, X, I, I, I))
  }

  "RomanNumber 740 toSymbol" should "be List(D,C,C,X,L)" in {
    RomanNumber(740).toSymbol should be(List(D,C,C,X,L))
  }

  "RomanNumber 1505" should "be List(M,D,V)" in {
    RomanNumber(1505).toSymbol should be (List(M,D,V))
  }

  "RomanNumber 0 to 3999" should "be defined" in {
    for (i <- 0 to 3999) {
      RomanNumber(i).toSymbol.contains(?) should be(false)
    }
  }

  "RomanNumber(999).toString" should "equal CMXCIX" in {
    RomanNumber(999).toString should be ("CMXCIX")
  }

  "RN 5005 toString" should "equal \u2181V" in {
    RomanNumber(5005).toString should be ("\u2181V")
    println(11111 + " -> " + RomanNumber(11111))
    println(5555 + " -> " + RomanNumber(5555))
  }



}