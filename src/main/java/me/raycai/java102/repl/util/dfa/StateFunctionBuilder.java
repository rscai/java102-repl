package me.raycai.java102.repl.util.dfa;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class StateFunctionBuilder<S,E> {

  private Map<Class<?>, Consumer<E>> eventFunctions = new HashMap<>();

  public StateFunctionBuilder event(Class<? extends E> eventType, Consumer<E> eventFunction) {
    eventFunctions.put(eventType, eventFunction);
    return this;
  }

  public StateFunctionBuilder orElse(Class<? extends E> eventType, Consumer<E> eventFunction) {
    eventFunctions.put(eventType, eventFunction);
    return this;
  }

  public StateFunction build() {
    return new StateFunction(eventFunctions);
  }
}
