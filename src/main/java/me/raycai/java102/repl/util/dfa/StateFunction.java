package me.raycai.java102.repl.util.dfa;

import java.util.Map;
import java.util.function.Consumer;

public class StateFunction<S,E> {

  private final Map<Class<E>, Consumer<E>> eventFunctions;
  
  StateFunction(Map<Class<E>, Consumer<E>> eventFunctions){
    this.eventFunctions = eventFunctions;
  }

  public boolean isDefinedAt(E event) {
    return eventFunctions.containsKey(event.getClass());
  }

  public void apply(E event) {
    eventFunctions.get(event.getClass()).accept(event);
  }

}
