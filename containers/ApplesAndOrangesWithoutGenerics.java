// containers/ApplesAndOrangesWithoutGenerics.java
// ©2016 MindView LLC: see Copyright.txt
// Simple container use (suppressing compiler warnings)
// {ThrowsException}
import java.util.*;

class Apple {
  private static long counter;
  private final long id = counter++;
  public long id() { return id; }
}

class Orange {}

public class ApplesAndOrangesWithoutGenerics {
  @SuppressWarnings("unchecked")
  public static void main(String[] args) {
    ArrayList apples = new ArrayList();
    for(int i = 0; i < 3; i++)
      apples.add(new Apple());
    // Not prevented from adding an Orange to apples:
    apples.add(new Orange());
    for(Object apple : apples) {
      ((Apple) apple).id();
      // Orange is detected only at run time
    }
  }
}
/* Output:
___[ Error Output ]___
Exception in thread "main" java.lang.ClassCastException:
Orange cannot be cast to Apple
        at ApplesAndOrangesWithoutGenerics.main(ApplesAndOr
angesWithoutGenerics.java:23)
___[ Exception is Expected ]___
*/
