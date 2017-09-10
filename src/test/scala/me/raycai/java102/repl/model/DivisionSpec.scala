package me.raycai.java102.repl.model

import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class DivisionSpec extends FlatSpec {
  "408 divided by 12" should "return 34" in {
    assertResult(34) {
      new Division(new Number(408), new Number(12)).evaluate()
    }
  }
  "39.36 divided by 12.3" should "return 3.2" in {
    assertResult(3.2f) {
      new Division(new Number(39.36f), new Number(12.3f)).evaluate()
    }
  }
}
