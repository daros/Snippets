package smarter

/**
* Created by IntelliJ IDEA.
* User: david
* Date: 26/3-11
* Time: 21:52
*/

import BigInt._

object Factorial {
  implicit def intToFactorial(i: Int) = new Factorial(i)
}

class Factorial(n: Int) {

  def ! = factorialTail(n)

  private def factorialTail(n: Int): BigInt = {

    def fTail(inter: BigInt, num: Int): BigInt = num match {
      case 0 => inter
      case n => fTail(inter * n, n - 1)
    }

    fTail(1, n)
  }


  // not tail recuesive
  private def factorial(n: Int): BigInt =
    n match {
      case 0 => 1
      case n => n * factorial(n - 1)
    }

  private def factorialFunc(n: BigInt) = (1 to n).product

  private def factorialFold(n: BigInt) = (1 to n).foldLeft(BigInt(1))(_ * _)
}