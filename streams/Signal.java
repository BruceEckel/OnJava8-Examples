// streams/Signal.java
// ©2016 MindView LLC: see Copyright.txt
import java.util.*;
import java.util.stream.*;
import java.util.function.*;

public class Signal {
  private final String msg;
  public Signal(String msg) { this.msg = msg; }
  public String getMsg() { return msg; }
  @Override
  public String toString() {
    return "Signal(" + msg + ")";
  }
  static class Generator implements Supplier<Signal> {
    Random rand = new Random(47);
    public Signal get() {
      switch(rand.nextInt(4)) {
        case 1: return new Signal("dot");
        case 2: return new Signal("dash");
        default: return null;
      }
    }
  }
  public static Stream<Optional<Signal>> stream() {
    return Stream.generate(new Generator())
      .map(signal -> Optional.ofNullable(signal));
  }
}
