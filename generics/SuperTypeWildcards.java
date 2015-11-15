// generics/SuperTypeWildcards.java
// ©2016 MindView LLC: see Copyright.txt
import java.util.*;

public class SuperTypeWildcards {
  static void writeTo(List<? super Apple> apples) {
    apples.add(new Apple());
    apples.add(new Jonathan());
    // apples.add(new Fruit()); // Error
  }
}
