package me.raycai.java102.repl.parse;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import me.raycai.java102.repl.model.Addition;
import me.raycai.java102.repl.model.Division;
import me.raycai.java102.repl.model.Expression;
import me.raycai.java102.repl.model.Multiplication;
import me.raycai.java102.repl.model.Numeric;
import me.raycai.java102.repl.model.Subtraction;
import me.raycai.java102.repl.parse.Token.Type;
import org.junit.Test;

public class TreeParserTest {

  /**
   * 1 + 2
   * 
   */
  @Test
  public void testOneOperator() throws Exception {
    final Lexer mockLexer = mock(Lexer.class);
    when(mockLexer.tokenize(anyString())).thenReturn(Arrays
        .asList(new Token(Type.NUMERIC, "1"), new Token(Type.ADDITION, "+"),
            new Token(Type.NUMERIC, "2")));

    final TreeParser testObject = new TreeParser(mockLexer);

    final String input = "1 + 2";

    final Expression actual = testObject.parse(input);
    assertThat(actual, instanceOf(Addition.class));
    final Addition addition = (Addition) actual;
    assertThat(addition.leftOperand, instanceOf(Numeric.class));
    assertThat(addition.leftOperand.evaluate(), is(BigDecimal.valueOf(1)));
    assertThat(addition.rightOperand, instanceOf(Numeric.class));
    assertThat(addition.rightOperand.evaluate(), is(BigDecimal.valueOf(2)));
  }

  /**
   * 1+2*3-4/5
   */
  @Test
  public void testMoreOperator() throws Exception {
    final Lexer mockLexer = mock(Lexer.class);
    when(mockLexer.tokenize(anyString())).thenReturn(Arrays.asList(
        new Token(Type.NUMERIC, "1"), new Token(Type.ADDITION, "+"),
        new Token(Type.NUMERIC, "2"), new Token(Type.MULTIPLICATION, "*"),
        new Token(Type.NUMERIC, "3"), new Token(Type.SUBTRACTION, "-"),
        new Token(Type.NUMERIC, "4"), new Token(Type.DIVISION, "/"), new Token(Type.NUMERIC, "5")
    ));
    final TreeParser testObject = new TreeParser(mockLexer);
    final String input = "1+2*3-4/5";
    final Expression actual = testObject.parse(input);

    assertThat(actual, instanceOf(Subtraction.class));
    final Subtraction subtraction = (Subtraction) actual;

    assertThat(subtraction.leftOperand, instanceOf(Addition.class));
    final Addition addition = (Addition) subtraction.leftOperand;
    assertThat(addition.leftOperand, instanceOf(Numeric.class));
    assertThat(addition.leftOperand.evaluate(), is(BigDecimal.valueOf(1)));
    assertThat(addition.rightOperand, instanceOf(Multiplication.class));
    final Multiplication multiplication = (Multiplication) addition.rightOperand;
    assertThat(multiplication.leftOperand, instanceOf(Numeric.class));
    assertThat(multiplication.leftOperand.evaluate(), is(BigDecimal.valueOf(2)));
    assertThat(multiplication.rightOperand, instanceOf(Numeric.class));
    assertThat(multiplication.rightOperand.evaluate(), is(BigDecimal.valueOf(3)));

    assertThat(subtraction.rightOperand, instanceOf(Division.class));
    final Division division = (Division) subtraction.rightOperand;
    assertThat(division.leftOperand, instanceOf(Numeric.class));
    assertThat(division.leftOperand.evaluate(), is(BigDecimal.valueOf(4)));
    assertThat(division.rightOperand, instanceOf(Numeric.class));
    assertThat(division.rightOperand.evaluate(), is(BigDecimal.valueOf(5)));
  }
}
