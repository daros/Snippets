package smarter

import io.Source
import java.io.File
import java.util.Date
import org.specs.Specification

/**
 * Created by IntelliJ IDEA.
 * User: david
 * Date: 31/3-11
 * Time: 10:05 
 */

object WriteToFileSpecs extends Specification with TestFileHelper {

  import WriteToFile._

  "When written to, a new file must be created" in {
    withFile(
      file => {
        file mustNot exist

        // func to be tested
        writeTo(file)(
          writer => {
            writer.println
          }
        )

        file must exist
      }
    )

  }

  "Validate written text" in {
    withFile(
      file => {
        // func to be tested
        val now = new Date
        writeTo(file)(
          writer => {
            writer.println("Hello Kitty")
            writer.println(now)
          }
        )

        // validate written file content
        validate(file) (
          content => {
            content(0) must be equalTo "Hello Kitty"
            content(1) must be equalTo now.toString
          }
        )
      }
    )
  }
}


trait TestFileHelper {
  def withFile(op: File => Unit) {
    val file = new File("test.txt")
    try {
      op(file)
    } finally {
      file.delete
    }
  }

  def validate(file: File) (op: List[String] => Unit) {
    val content = Source.fromFile(file).getLines.toList;
    try {
      op(content)
    } catch {
      case e =>
        file.delete
        throw e
    }
  }
}
