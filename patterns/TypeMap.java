// patterns/TypeMap.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Generic TypeMap works for any types.
package patterns;
import java.util.*;
import java.util.stream.*;

public class TypeMap<T> {
  public final Map<Class, List<T>> map =
    new HashMap<>();
  public void add(T o) {
    Class type = o.getClass();
    map.computeIfAbsent(type,
      k -> new ArrayList<T>()).add(o);
  }
  public Stream<List<T>> values() {
    return map.values().stream();
  }
}
