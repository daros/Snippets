package smarter

/**
 * Created by IntelliJ IDEA.
 * User: david
 * Date: 30/3-11
 * Time: 09:19 
 */

object TillLoop {
  def tillLoopCurry(cond: => Boolean)(body: => Unit): Unit = {
    if (cond) {
      body
      tillLoopCurry(cond)(body)
    }
  }

  def tillLoopDefault(cond: => Boolean, body: => Unit): Unit = {
    if (cond) {
      body
      tillLoopDefault(cond, body)
    }
  }
}