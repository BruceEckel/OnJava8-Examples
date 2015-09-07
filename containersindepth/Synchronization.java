// containersindepth/Synchronization.java
// ©2015 MindView LLC: see Copyright.txt
// Using the Collections.synchronized methods.
import java.util.*;

public class Synchronization {
  public static void main(String[] args) {
    Collection<String> c =
      Collections.synchronizedCollection(
        new ArrayList<>());
    List<String> list = Collections.synchronizedList(
      new ArrayList<>());
    Set<String> s = Collections.synchronizedSet(
      new HashSet<>());
    Set<String> ss = Collections.synchronizedSortedSet(
      new TreeSet<>());
    Map<String,String> m = Collections.synchronizedMap(
      new HashMap<>());
    Map<String,String> sm =
      Collections.synchronizedSortedMap(new TreeMap<>());
  }
}
/* Output: (None) */
