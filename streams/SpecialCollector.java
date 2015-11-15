// streams/SpecialCollector.java
// ©2016 MindView LLC: see Copyright.txt
import java.util.*;
import java.util.stream.*;

public class SpecialCollector {
  public static void main(String[] args) throws Exception {
    ArrayList<String> words =
      FileToWords.stream("Cheese.dat")
        .collect(ArrayList::new,
                 ArrayList::add,
                 ArrayList::addAll);
    for(String s : words)
      if(s.equals("cheese"))
        System.out.println(s);
  }
}
/* Output:
cheese
cheese
*/
