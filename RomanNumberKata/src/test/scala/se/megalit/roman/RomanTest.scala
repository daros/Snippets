package se.megalit.roman

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers


class RomanTest extends FlatSpec with ShouldMatchers {
  import RomanNumber._

  "RomanNumber.I" should "equal I" in {
    RomanNumber.I should equal(I)
  }

  "RomanNumber(1)" should "be same object as I" in {
    RomanNumber(1).eq(I) should equal(true)
    RomanNumber(5) should be(V)
    RomanNumber(10) should be(X)
    RomanNumber(100) should not be (C)
    RomanNumber(100).ne(C) should equal(true)
  }

  "I toInt" should "become 1" in {
    I.toInt should equal (1)
  }

  "RomanNumber constants" should "interpret as int" in {
    List[(RomanNumber, Int)](
      (I, 1),
      (V, 5),
      (X, 10),
      (L, 50),
      (C, 100),
      (D, 500),
      (M, 1000)
      ).foreach {
      case (r, i) => {r.toInt should equal(i)}
    }
  }

  "RomanNumber constants" should "interpret as String" in {
    List[(RomanNumber, String)](
      (I, "I"),
      (V, "V"),
      (X, "X"),
      (L, "L"),
      (C, "C"),
      (D, "D"),
      (M, "M")
      ).foreach {
      case (r, s) => {r.toString should equal(s)}
    }
  }

  "String representation of uninterpreted int numbers" should "be undefined for RomanNumbers" in {
    RomanNumber(4000).toString should equal("-undefined-")
  }

  "I" should "be shiftable 2 steps" in {
    I shift 0 should equal(I)
    I shift 1 should equal(X)
    I shift 2 should equal(C)
    I shift 3 should equal(M)
  }

  "V" should "be shiftable 1 steps" in {
    V shift 0 should equal(V)
    V shift 1 should equal(L)
    V shift 2 should equal(D)
  }

  "X" should "be shiftable 0 steps" in {
    X shift 0 should equal(X)
    X shift 1 should equal(C)
    X shift 2 should equal(M)
  }

  "RomanNumber(2).toString" should "equal II" in {
    RomanNumber(2).toString should equal("II")
  }

  "RomanNumber(3).toString" should "equal III" in {
    RomanNumber(3).toString should equal("III")
  }

  "RomanNumber(4).toString" should "equal IV" in {
    RomanNumber(4).toString should equal("IV")
  }

  "RomanNumber 1 to 10 toString" should "be defined" in {
    for (i <- 1 to 10) {
      RomanNumber(i).toString should not equal ("undefined")
    }
  }

  "RomanNumber(2).toList" should "equal List(I, I)" in {
    RomanNumber(2).toList should equal(List(I, I))
  }

  "shiftSymbol(RomanNumber(1) 1 step" should "yield X" in {
    RomanNumber.shiftSymbol(RomanNumber(1), 1) should be(X)
  }

  "List(I,I,I) shift 1 step" should "yield List(X,X,X)" in {
    val res = for{
      r <- List(I, I, I)
    } yield RomanNumber.shiftSymbol(r, 1)

    res should equal(List(X, X, X))
  }

  "RomanNumber(20).toString" should "equal XX" in {
    RomanNumber(20).toString should equal("XX")
  }

  "RomanNumber(80).toString" should "equal LXXX" in {
    RomanNumber(80).toString should equal("LXXX")
  }

  "RomanNumber(300).toString" should "equal CCC" in {
    RomanNumber(300).toString should equal("CCC")
  }

  "RomanNumber(900).toString" should "equal CM" in {
    RomanNumber(900).toString should equal("CM")
  }

  "RomanNumber(2000).toString" should "equal MM" in {
    RomanNumber(2000).toString should equal("MM")
  }

  "RomanNumber 1 to 9" should "be defined" in {
    for (i <- 1 to 9) {
      RomanNumber(i).toList should not equal (Nil)
    }
  }

  "RomanNumber 10 to 99" should "be defined" in {
    for (i <- 10 to 99) {
      RomanNumber(i).toList should not equal (Nil)
    }
  }

  "RomanNumber 100 to 999" should "be defined" in {
    for (i <- 100 to 999) {
      RomanNumber(i).toList should not equal (Nil)
    }
  }

  "RomanNumber 1000 to 3999" should "be defined" in {
    for (i <- 1000 to 3999) {
      RomanNumber(i).toList should not equal (Nil)
    }
  }

  "RomanNumber(999).toString" should "equal CMXCIX" in {
    RomanNumber(999).toString should equal ("CMXCIX")
  }
}