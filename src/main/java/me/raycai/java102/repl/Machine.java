package me.raycai.java102.repl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigDecimal;
import me.raycai.java102.repl.model.Expression;
import me.raycai.java102.repl.parse.ParseException;
import me.raycai.java102.repl.parse.Parser;

public class Machine {

  private Parser parser;
  private BufferedReader input;
  private BufferedWriter output;

  private static final String promot = "$> ";
  private static final String usage = "type exit ENTER to exit";

  public Machine(Parser parser, BufferedReader input, BufferedWriter output) {
    this.parser = parser;
    this.input = input;
    this.output = output;
  }

  public void start() throws IOException, ParseException {
    output.write(usage);
    output.write("\n");
    output.write(promot);
    output.flush();
    while (true) {
      final String line = input.readLine();
      while (line !=null && "exit".equals(line.trim())) {
        return;
      }
      final Expression expression = parser.parse(line);
      final BigDecimal result = expression.evaluate();

      output.write(result.toString());
      output.write("\n");
      output.write(promot);
      output.flush();
    }
  }

}
