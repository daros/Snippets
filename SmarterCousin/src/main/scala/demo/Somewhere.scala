package demo

import java.io.{PrintWriter, File}
import java.util.Date

/**
 * Created by IntelliJ IDEA.
 * User: david
 * Date: 30/3-11
 * Time: 19:43 
 */

object Somewhere {

  def writingSomething(file: File) {
    val writer = new PrintWriter(file)

    try {

      writer.println("Hello Kitty")
      writer.println(new Date)

    } finally {
      writer.close
    }
  }
}