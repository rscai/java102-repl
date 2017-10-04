package me.raycai.java102.repl.model;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import org.junit.Test;


public class SubtractionTest {

  @Test
  public void testIntegerSubtraction() throws Exception {
    assertThat(new Subtraction(new Numeric(new BigDecimal(34)), new Numeric(new BigDecimal(12)))
        .evaluate(), is(new BigDecimal(22)));
  }

  @Test
  public void testFloatSubtraction() throws Exception {
    assertThat(
        new Subtraction(new Numeric(BigDecimal.valueOf(34.56d)),
            new Numeric(BigDecimal.valueOf(34.5d)))
            .evaluate(), is(BigDecimal.valueOf(0.06d)));
  }
}
