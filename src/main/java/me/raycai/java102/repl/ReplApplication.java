package me.raycai.java102.repl;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import me.raycai.java102.repl.parse.DFALexer;
import me.raycai.java102.repl.parse.TreeParser;

public class ReplApplication {

  public static void main(String[] args) throws Exception {
    Machine machine = new Machine(new TreeParser(new DFALexer()),
        new BufferedReader(new InputStreamReader(System.in)),
        new BufferedWriter(new OutputStreamWriter(System.out)));
    machine.start();
  }
}
