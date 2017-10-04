package me.raycai.java102.repl.util.dfa;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class AbstractDFATest {

  private enum State {
    S1,
    S2
  }

  private interface Event {

  }

  private class Zero implements Event {

  }

  private class One implements Event {

  }

  private AbstractDFA<State, Event> testObject;

  @Before
  public void setUp() {
    testObject = new AbstractDFA<State, Event>() {
      {
        startWith(State.S1);

        when(State.S1, matchEvent(One.class, (Event event) -> {
          stay();
        }).orElse(Zero.class, (Event event) -> {
          transitTo(State.S2);
        }));

        when(State.S2, matchEvent(One.class, (Event event) -> {
          stay();
        }).orElse(Zero.class, (Event event) -> {
          transitTo(State.S1);
        }));

        initialize();
      }
    };
  }

  @Test
  public void testTransition() throws Exception {
    final List<Event> input = Arrays.asList(new One(), new Zero());
    for (final Event event : input) {
      testObject.receive(event);
    }

    assertThat(testObject.getCurrentState(), is(State.S2));
  }

}
