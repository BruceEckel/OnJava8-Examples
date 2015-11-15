// onjava/BasicSupplier.java
// ©2016 MindView LLC: see Copyright.txt
// Automatically create a Supplier, given a class
// with a default (no-arg) constructor.
package onjava;
import java.util.function.*;

public class BasicSupplier<T> implements Supplier<T> {
  private Class<T> type;
  public BasicSupplier(Class<T> type){ this.type = type; }
  @Override
  public T get() {
    try {
      // Assumes type is a public class:
      return type.newInstance();
    } catch(InstantiationException |
            IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }
  // Produce a Default generator given a type token:
  public static <T> Supplier<T> create(Class<T> type) {
    return new BasicSupplier<>(type);
  }
}
