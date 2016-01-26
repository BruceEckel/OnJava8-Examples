// collectionsindepth/TypesForSets.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// Methods necessary to put your own type in a Set
import java.lang.reflect.InvocationTargetException;
import java.util.*;

class SetType {
  int i;
  public SetType(int n) { i = n; }
  @Override
  public boolean equals(Object o) {
    return o instanceof SetType && (i == ((SetType)o).i);
  }
  @Override
  public String toString() { return Integer.toString(i); }
}

class HashType extends SetType {
  public HashType(int n) { super(n); }
  @Override
  public int hashCode() { return i; }
}

class TreeType extends SetType
implements Comparable<TreeType> {
  public TreeType(int n) { super(n); }
  @Override
  public int compareTo(TreeType arg) {
    return (arg.i < i ? -1 : (arg.i == i ? 0 : 1));
  }
}

public class TypesForSets {
  static <T> Set<T> fill(Set<T> set, Class<T> type) {
    try {
      for(int i = 0; i < 10; i++)
          set.add(type.getConstructor(int.class)
            .newInstance(i));
    } catch(NoSuchMethodException |
            SecurityException |
            InstantiationException |
            IllegalAccessException |
            IllegalArgumentException |
            InvocationTargetException e) {
      throw new RuntimeException(e);
    }
    return set;
  }
  static <T> void test(Set<T> set, Class<T> type) {
    fill(set, type);
    fill(set, type); // Try to add duplicates
    fill(set, type);
    System.out.println(set);
  }
  public static void main(String[] args) {
    test(new HashSet<>(), HashType.class);
    test(new LinkedHashSet<>(), HashType.class);
    test(new TreeSet<>(), TreeType.class);
    // Things that don't work:
    test(new HashSet<>(), SetType.class);
    test(new HashSet<>(), TreeType.class);
    test(new LinkedHashSet<>(), SetType.class);
    test(new LinkedHashSet<>(), TreeType.class);
    try {
      test(new TreeSet<>(), SetType.class);
    } catch(Exception e) {
      System.out.println("Expected: " + e.getMessage());
    }
    try {
      test(new TreeSet<>(), HashType.class);
    } catch(Exception e) {
      System.out.println("Expected: " + e.getMessage());
    }
  }
}
/* Output:
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
[9, 8, 7, 6, 5, 4, 3, 2, 1, 0]
[6, 9, 2, 0, 8, 5, 4, 8, 0, 2, 7, 8, 9, 1, 4, 5, 0, 4, 3,
1, 6, 3, 2, 7, 7, 6, 5, 3, 9, 1]
[7, 2, 8, 9, 1, 7, 4, 1, 2, 3, 0, 9, 1, 8, 3, 0, 8, 5, 6,
6, 3, 0, 2, 7, 5, 4, 6, 4, 5, 9]
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2, 3, 4, 5, 6, 7, 8,
9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2, 3, 4, 5, 6, 7, 8,
9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
Expected: SetType cannot be cast to java.lang.Comparable
Expected: HashType cannot be cast to java.lang.Comparable
*/
