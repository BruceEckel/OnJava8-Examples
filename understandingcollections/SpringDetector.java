// understandingcollections/SpringDetector.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// What will the weather be?
import java.lang.reflect.*;
import java.util.*;

public class SpringDetector {
  // Uses a Groundhog or class derived from Groundhog:
  public static <T extends Groundhog>
  void detectSpring(Class<T> type) throws Exception {
    Constructor<T> ghog = type.getConstructor(int.class);
    Map<Groundhog,Prediction> map = new HashMap<>();
    for(int i = 0; i < 10; i++)
      map.put(ghog.newInstance(i), new Prediction());
    System.out.println("map = " + map);
    Groundhog gh = ghog.newInstance(3);
    System.out.println("Looking up prediction for " + gh);
    if(map.containsKey(gh))
      System.out.println(map.get(gh));
    else
      System.out.println("Key not found: " + gh);
  }
  public static void
  main(String[] args) throws Exception {
    detectSpring(Groundhog.class);
  }
}
/* Output:
map = {Groundhog #2=Early Spring!, Groundhog #5=Early
Spring!, Groundhog #6=Early Spring!, Groundhog #4=Early
Spring!, Groundhog #9=Six more weeks of Winter!, Groundhog
#8=Six more weeks of Winter!, Groundhog #0=Early Spring!,
Groundhog #1=Early Spring!, Groundhog #3=Early Spring!,
Groundhog #7=Six more weeks of Winter!}
Looking up prediction for Groundhog #3
Key not found: Groundhog #3
*/
