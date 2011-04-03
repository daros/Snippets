package demo

import org.specs.Specification
import java.io.File
import io.Source
import java.util.Date

/**
 * Created by IntelliJ IDEA.
 * User: david
 * Date: 30/3-11
 * Time: 19:54 
 */

object SomewhereSpecs extends Specification {

  "calling write something must write Hello Kitty to file" in {
    val file = new File("test.txt")

    try {
      Somewhere.writingSomething(file)

      val content = Source.fromFile(file).getLines.toList
      val now = new Date

      content(0) must be equalTo "Hello Kitty"
//      content(1) match {
//        case d: Date => d.getTime must be closeTo(now.getTime, 1000)
//        case _ => throw new IllegalArgumentException
//      }

    } finally {
      file.delete
    }


  }
}