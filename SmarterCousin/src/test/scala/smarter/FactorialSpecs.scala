package smarter

import math.BigInt._

import org.specs.Specification

/**
 * Created by IntelliJ IDEA.
 * User: david
 * Date: 26/3-11
 * Time: 22:09 
 */

class FactorialSpecs extends Specification {

  "3! must be 6"  in {
    val res: BigInt = new Factorial(3).!
    (res) must be equalTo(6)
  }

  import smarter.Factorial.intToFactorial

  "1! must be 1" in {
    (1!) must be equalTo 1
    (1 !) must be equalTo 1
//    (1.!) must be equalTo 1  // error: value ! is not a member of Double
    (1).! must be equalTo(1);
  }

  "2! must be 2" in {
    (2!) must be equalTo 2
  }

  "3! must be 6" in {
    (3!) must be equalTo 6
  }

  "4! must be 24" in {
    (4!) must be equalTo 24
  }

  "12! must be 479001600" in {
    (12!) must be equalTo 479001600
  }

  "24! must be 620448401733239439360000" in {
    (24!) must be equalTo BigInt("620448401733239439360000")
  }

  "36! must be 371993326789901217467999448150835200000000" in {
    (36!) must be equalTo BigInt("371993326789901217467999448150835200000000")
  }

  "2! + 3! must be 8" in {
    ((2!) + (3!)) must be equalTo 8
//    (2! + 3!) must be equalTo 8
  }
}