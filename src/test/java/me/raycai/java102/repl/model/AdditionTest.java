package me.raycai.java102.repl.model;

import java.math.BigDecimal;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class AdditionTest {

  @Test
  public void testIntegerAddition() throws Exception {
    assertThat(
        new Addition(new Numeric(new BigDecimal(12)), new Numeric(new BigDecimal(34))).evaluate(),
        is(new BigDecimal(46)));
  }

  @Test
  public void testFloatAddition() throws Exception {
    assertThat(new Addition(new Numeric(BigDecimal.valueOf(13.2d)), new Numeric( BigDecimal.valueOf(23.22d)))
        .evaluate(), is( BigDecimal.valueOf(36.42d)));
  }
}
