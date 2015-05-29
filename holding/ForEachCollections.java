//: holding/ForEachCollections.java
// ©2015 MindView LLC: see Copyright.txt
// All collections work with foreach.
import java.util.*;

public class ForEachCollections {
  public static void main(String[] args) {
    Collection<String> cs = new LinkedList<>();
    Collections.addAll(cs,
      "Take the long way home".split(" "));
    for(String s : cs)
      System.out.print("'" + s + "' ");
  }
} /* Output:
'Take' 'the' 'long' 'way' 'home'
*///:~
