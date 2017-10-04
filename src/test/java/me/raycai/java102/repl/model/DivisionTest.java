package me.raycai.java102.repl.model;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import org.junit.Test;

public class DivisionTest {

  @Test
  public void testIntegerDivision() throws Exception {
    assertThat(
        new Division(new Numeric(new BigDecimal(408)), new Numeric(new BigDecimal(12))).evaluate(),
        is(new BigDecimal(34)));
  }

  @Test
  public void testFloatDivision() throws Exception {
    assertThat(new Division(new Numeric(BigDecimal.valueOf(39.36d)),
        new Numeric(BigDecimal.valueOf(12.3d)))
        .evaluate(), is(BigDecimal.valueOf(3.2d)));
  }
}
