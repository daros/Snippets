package smarter

import org.specs.Specification

/**
 * Created by IntelliJ IDEA.
 * User: david
 * Date: 30/3-11
 * Time: 09:21 
 */

class TillLoopSpecs extends Specification {

  "tillLoopCurry as while should increment variable i to 10" in {
    import smarter.TillLoop._

    var i = 0
    tillLoopCurry(i < 10) {
                            println(i)
                            i += 1
                          }
    i must be(10)
  }

  "tillLoopCurry inlining should increment variable i to 10" in {
    import smarter.TillLoop._

    var i = 0

    tillLoopCurry(i < 10)({
                            println(i)
                            i += 1
                          })
    i must be(10)
  }

  "tillLoopCurry declared body should increment variable i to 10" in {
    import smarter.TillLoop._

    var i = 0
    val body = () => {
      println(i)
      i += 1
    }

    tillLoopCurry(i < 10)(body())
    i must be(10)
  }

  "tillLoopCurry declared condition + body should increment variable i to 10" in {
    import smarter.TillLoop._

    var i = 0
    val body = () => {
      println(i)
      i += 1
    }
    var cond = () => i < 10


    tillLoopCurry(cond())(body())
    i must be(10)
  }

  "tillLoopCurry declared condition + body operator syntax should increment variable i to 10" in {
    import smarter.TillLoop._

    var i = 0
    var cond = () => i < 10
    val body = () => {
      println(i)
      i += 1
    }

    tillLoopCurry(cond()) {
                            body()
                          }
    i must be(10)
  }

  "tillLoopDefault declared body should increment variable i to 10" in {
    import smarter.TillLoop._

    var i = 0
    val body = () => {
      println(i)
      i += 1
    }

    tillLoopDefault(i < 10, body())
    i must be(10)
  }

}
