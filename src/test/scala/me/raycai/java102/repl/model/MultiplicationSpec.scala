package me.raycai.java102.repl.model

import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class MultiplicationSpec extends FlatSpec {
  "34 multiply 12" should "return 408" in {
    assertResult(408) {
      new Multiplication(new Number(34), new Number(12)).evaluate
    }
  }

  "12.3 multiply 3.2" should "return 39.36" in {
    assertResult(39.36f) {
      new Multiplication(new Number(12.3f), new Number(3.2f)).evaluate
    }
  }
}
