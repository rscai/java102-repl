package me.raycai.java102.repl.model

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FlatSpec

@RunWith(classOf[JUnitRunner])
class SubtractionSpec extends FlatSpec {
    "34 minus 12" should "return 22" in {
        new Subtraction(new Number(34), new Number(12))
                .evaluate()
    }
    "34.56 minus 34.5" should "return 0.06" in {
        new Subtraction(new Number(34.56f), new Number(34.5f))
                .evaluate()
    }
}
