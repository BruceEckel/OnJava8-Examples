//: containers/GenericsAndUpcasting.java
// ©2015 MindView LLC: see Copyright.txt
import java.util.*;

class GrannySmith extends Apple {}
class Gala extends Apple {}
class Fuji extends Apple {}
class Braeburn extends Apple {}

public class GenericsAndUpcasting {
  public static void main(String[] args) {
    ArrayList<Apple> apples = new ArrayList<>();
    apples.add(new GrannySmith());
    apples.add(new Gala());
    apples.add(new Fuji());
    apples.add(new Braeburn());
    for(Apple c : apples)
      System.out.println(c);
  }
} /* Output:
GrannySmith@19e0bfd
Gala@139a55
Fuji@1db9742
Braeburn@106d69c
*///:~
