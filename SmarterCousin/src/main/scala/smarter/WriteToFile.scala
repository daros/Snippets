package smarter

import java.io.{PrintWriter, File}

/**
 * Created by IntelliJ IDEA.
 * User: david
 * Date: 31/3-11
 * Time: 10:01 
 */

object WriteToFile {
  def writeTo(file: File)(op: PrintWriter => Unit) {
    val writer = new PrintWriter(file)
    try {
      op(writer)
    } finally {
      writer.close
    }
  }
}