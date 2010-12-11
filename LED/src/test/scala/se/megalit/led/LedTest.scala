package se.megalit.led

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FlatSpec

/**
 * Created by IntelliJ IDEA.
 * User: David Rosell - Redpill-Linpro
 * Date: Jul 18, 2010
 * Time: 8:40:31 PM
 */

class LedTest extends FlatSpec with ShouldMatchers {
  import LedSymbol._

  "h2" should "be  - " in {
    h2.toString should be (" - ")
  }

  "0" should "be led 0" in {
    Led(0,1).toLed should be (List(h2, v3, h1, v3, h2))
  }
}