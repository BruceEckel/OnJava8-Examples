// containers/ApplesAndOrangesWithGenerics.java
// ©2016 MindView LLC: see Copyright.txt
import java.util.*;

public class ApplesAndOrangesWithGenerics {
  public static void main(String[] args) {
    ArrayList<Apple> apples = new ArrayList<>();
    for(int i = 0; i < 3; i++)
      apples.add(new Apple());
    // Compile-time error:
    // apples.add(new Orange());
    for(Apple apple : apples) {
      System.out.println(apple.id());
    }
    // Using for-in:
    for(Apple c : apples)
      System.out.println(c.id());
  }
}
/* Output:
0
1
2
0
1
2
*/
