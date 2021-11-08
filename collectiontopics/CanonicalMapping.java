// collectiontopics/CanonicalMapping.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Demonstrates WeakHashMap
import java.util.*;
import java.util.stream.*;

public class CanonicalMapping {
  static void showKeys(Map<String, String> m) {
    // Display sorted keys
    List<String> keys = new ArrayList<>(m.keySet());
    Collections.sort(keys);
    System.out.println(keys);
  }
  public static void main(String[] args) {
    int size = 100;
    String[] savedKeys = new String[size];
    WeakHashMap<String,String> map =
      new WeakHashMap<>();
    for(int i = 0; i < size; i++) {
      String key = String.format("%03d", i);
      String value = Integer.toString(i);
      if(i % 3 == 0)
        savedKeys[i] = key; // Save as "real" references
      map.put(key, value);
    }
    showKeys(map);
    System.gc();
    showKeys(map);
  }
}
