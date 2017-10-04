package me.raycai.java102.repl.model;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import org.junit.Test;

public class MultiplicationTest {

  @Test
  public void testIntegerMultiplication() throws Exception {
    assertThat(new Multiplication(new Numeric(new BigDecimal(34)), new Numeric(new BigDecimal(12)))
        .evaluate(), is(new BigDecimal(408)));
  }

  @Test
  public void testFloatMultiplication() throws Exception {
    assertThat(
        new Multiplication(new Numeric(BigDecimal.valueOf(12.3d)),
            new Numeric(BigDecimal.valueOf(3.2d)))
            .evaluate(), is(BigDecimal.valueOf(39.36d)));
  }
}
