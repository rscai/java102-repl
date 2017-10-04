package me.raycai.java102.repl.util.dfa;

import java.util.Map;
import java.util.TreeMap;
import java.util.function.Consumer;

public class AbstractDFA<S, E> {

  private final Map<S, StateFunction<S, E>> stateFunctions = new TreeMap<>();

  private S currentState;
  private boolean isInitialized = false;

  /**
   * DSL
   */
  protected void when(S state, StateFunctionBuilder stateFunctionBuilder) {
    stateFunctions.put(state, stateFunctionBuilder.build());
  }

  protected StateFunctionBuilder<S, E> matchEvent(Class<? extends E> eventType,
      Consumer<E> eventFunction) {
    return new StateFunctionBuilder<S, E>().event(eventType, eventFunction);
  }

  protected void startWith(S state) {
    currentState = state;
  }

  protected void initialize() {
    isInitialized = true;
  }

  protected S stay() {
    return currentState;
  }

  protected S transitTo(S nextState) {
    currentState = nextState;
    return currentState;
  }

  //
  public S receive(E event) {
    final StateFunction<S, E> stateFunction = stateFunctions.get(currentState);
    if (stateFunction.isDefinedAt(event)) {
      stateFunction.apply(event);
      return currentState;
    } else {
      return currentState;
    }
  }

  public S getCurrentState() {
    return currentState;
  }
}
