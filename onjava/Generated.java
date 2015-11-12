// onjava/Generated.java
package onjava;
import java.util.function.*;

public class Generated {
  // Fill an existing array:
  public static <T> T[] array(T[] a, Supplier<T> gen) {
    return new CollectionData<>(gen, a.length).toArray(a);
  }
  // Create a new array:
  @SuppressWarnings("unchecked")
  public static <T> T[] array(Class<T> type,
      Supplier<T> gen, int size) {
    T[] a =
      (T[])java.lang.reflect.Array.newInstance(type, size);
    return new CollectionData<>(gen, size).toArray(a);
  }
}
