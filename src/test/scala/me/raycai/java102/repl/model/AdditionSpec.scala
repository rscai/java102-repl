package me.raycai.java102.repl.model

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FlatSpec

@RunWith(classOf[JUnitRunner])
class AdditionSpec extends FlatSpec {
    "12 plus 34" should "return 46" in {
        assertResult(46) {
            val addition = new Addition(new Number(12), new Number(34));
            addition
                    .evaluate
        }
    }

    "13.2 plus 23.22" should "return 36.42" in {
        assertResult(36.42f) {
            new Addition(new Number(13.2f), new Number(23.22f))
                    .evaluate()
        }
    }

}


