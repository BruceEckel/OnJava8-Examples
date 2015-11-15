// onjava/Generated.java
// ©2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
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
