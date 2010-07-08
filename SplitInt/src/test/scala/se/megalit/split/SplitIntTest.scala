/**
 * Created by IntelliJ IDEA.
 * User: David Rosell - Redpill-Linpro
 * Date: Jul 8, 2010
 * Time: 1:29:45 PM
 */

package se.megalit.split

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers


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
}