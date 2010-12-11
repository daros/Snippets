/**
 * Created by IntelliJ IDEA.
 * User: David Rosell - Redpill-Linpro
 * Date: Jul 8, 2010
 * Time: 1:29:45 PM
 */

package se.megalit.split

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import java.util.Date


class SplitIntTest extends FlatSpec with ShouldMatchers {
  "1" should "be converted to List[Char]" in {
    1.toString.toList should be (List('1'))
  }

  "1" should "be converted to List[Int]" in {
    1.toString.toList.map(_.toInt) should be (List(49))
  }

  "1" should "be converted to List(1)" in {
    1.toString.toList.map(_.toInt - 48) should be (List(1))
  }

  "123" should "be converted to List(1,2,3)" in {
    123.toString.toList.map(_.toInt - 48) should be (List(1,2,3)) 
  }

  "123.toList" should "be converted to List(1,2,3)" in {
    implicit def intToList(i: Int): List[Int] = i.toString.toList.map(_.toInt - 48)
    123.toList should be (List(1,2,3))
  }

  def listify(i: Int): List[Int] = i match {
      case i if i/10 == 0 => List(i%10)
      case i => listify(i/10) ::: List(i%10)
    }

  "listify 3" should "return List(3)" in {
    listify(3) should be (List(3))
  }

  "listify 32" should "return List(3,2)" in {
    listify(32) should be (List(3,2))
  }

  "listify 321" should "return List(3,2,1)" in {
    val list = listify(321)
    list should be (List(3,2,1))
    println(list)
  }

  "listify 4321" should "return List(4,3,2,1)" in {
    val list = listify(4321)
    list should be (List(4,3,2,1))
    println(list)
  }

  "yfitsil 4321" should "return List(1,2,3,4)" in {
    val list = yfitsil(4321)
    list.reverse should be (List(4,3,2,1))
    println(list)
  }

  def yfitsil(i: Int): List[Int] = i match {
      case i if i == 0 => List()
      case i => i%10 :: yfitsil(i/10)
    }

  "time comparison" should "deside de" in {
    val numbers = List.range(1,500000)

    val startRecursionMethod = new Date
    numbers.foreach(listify(_))
    val endRecursionMethod = new Date
    println("Recursion: "+(endRecursionMethod.getTime-startRecursionMethod.getTime))

    val startRecursion1Method = new Date
    numbers.foreach(yfitsil(_))
    val endRecursion1Method = new Date
    println("Recursion1: "+(endRecursion1Method.getTime-startRecursion1Method.getTime))

    {
      implicit def intToList(i: Int): List[Int] = i.toString.toList.map(_.toInt - 48)
      val startImplicitMethod = new Date
      numbers.foreach(_.toList)
      val endImplicitMethod = new Date
      println("Implicit: "+(endImplicitMethod.getTime-startImplicitMethod.getTime))
    }

    {
      implicit def intToList(i: Int): List[Int] = """\d""".r.findAllIn(i.toString).map(_.toInt).toList
      val startImplicit1Method = new Date
      numbers.foreach(_.toList)
      val endImplicit1Method = new Date
      println("Implicit2: "+(endImplicit1Method.getTime-startImplicit1Method.getTime))
    }
  }
}