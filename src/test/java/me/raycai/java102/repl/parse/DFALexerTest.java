package me.raycai.java102.repl.parse;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import me.raycai.java102.repl.parse.Token.Type;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class DFALexerTest {

  private String input;
  private List<Token> expectedTokens;

  @Parameterized.Parameters
  public static Collection inputAndExpectedTokens() {
    return Arrays.asList(new Object[][]{
        {"1+2", Arrays.asList(new Token(Type.NUMERIC, "1"), new Token(Type.ADDITION, "+"),
            new Token(Type.NUMERIC, "2"))},
        {"1 + 2*3 -4 /5", Arrays.asList(new Token(Type.NUMERIC, "1"), new Token(Type.ADDITION, "+"),
            new Token(Type.NUMERIC, "2"), new Token(Type.MULTIPLICATION, "*"),
            new Token(Type.NUMERIC, "3"), new Token(Type.SUBTRACTION, "-"),
            new Token(Type.NUMERIC, "4"), new Token(Type.DIVISION, "/"),
            new Token(Type.NUMERIC, "5"))}
    });
  }

  public DFALexerTest(String input, List<Token> expectedTokens) {
    this.input = input;
    this.expectedTokens = expectedTokens;
  }

  @Test
  public void testTokenize() throws Exception {
    final DFALexer testObject = new DFALexer();
    final List<Token> actual = testObject.tokenize(input);
    assertThat(actual, is(expectedTokens));

  }
}
