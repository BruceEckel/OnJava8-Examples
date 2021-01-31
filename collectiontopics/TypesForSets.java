// collectiontopics/TypesForSets.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Methods necessary to put your own type in a Set
import java.util.*;
import java.util.function.*;
import java.util.Objects;

class SetType {
  protected int i;
  SetType(int n) { i = n; }
  @Override public boolean equals(Object o) {
    return o instanceof SetType &&
      Objects.equals(i, ((SetType)o).i);
  }
  @Override public String toString() {
    return Integer.toString(i);
  }
}

class HashType extends SetType {
  HashType(int n) { super(n); }
  @Override public int hashCode() {
    return Objects.hashCode(i);
  }
}

class TreeType extends SetType
implements Comparable<TreeType> {
  TreeType(int n) { super(n); }
  @Override public int compareTo(TreeType arg) {
    return Integer.compare(arg.i, i);
    // Equivalent to:
    // return arg.i < i ? -1 : (arg.i == i ? 0 : 1);
  }
}

public class TypesForSets {
  static <T> void
  fill(Set<T> set, Function<Integer, T> type) {
    for(int i = 10; i >= 5; i--) // Descending
      set.add(type.apply(i));
    for(int i = 0; i < 5; i++) // Ascending
      set.add(type.apply(i));
  }
  static <T> void
  test(Set<T> set, Function<Integer, T> type) {
    fill(set, type);
    fill(set, type); // Try to add duplicates
    fill(set, type);
    System.out.println(set);
  }
  public static void main(String[] args) {
    test(new HashSet<>(), HashType::new);
    test(new LinkedHashSet<>(), HashType::new);
    test(new TreeSet<>(), TreeType::new);
    // Things that don't work:
    test(new HashSet<>(), SetType::new);
    test(new HashSet<>(), TreeType::new);
    test(new LinkedHashSet<>(), SetType::new);
    test(new LinkedHashSet<>(), TreeType::new);
    try {
      test(new TreeSet<>(), SetType::new);
    } catch(Exception e) {
      System.out.println(e.getMessage());
    }
    try {
      test(new TreeSet<>(), HashType::new);
    } catch(Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
/* Output:
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
[10, 9, 8, 7, 6, 5, 0, 1, 2, 3, 4]
[10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0]
[4, 1, 5, 1, 0, 5, 6, 8, 7, 0, 2, 8, 4, 9, 6, 10, 6, 7,
2, 9, 10, 3, 8, 4, 10, 3, 9, 5, 3, 7, 1, 2, 0]
[9, 8, 4, 8, 5, 0, 1, 6, 2, 9, 3, 7, 2, 2, 7, 0, 9, 5,
5, 6, 4, 7, 10, 1, 6, 4, 1, 10, 3, 3, 0, 10, 8]
[10, 9, 8, 7, 6, 5, 0, 1, 2, 3, 4, 10, 9, 8, 7, 6, 5,
0, 1, 2, 3, 4, 10, 9, 8, 7, 6, 5, 0, 1, 2, 3, 4]
[10, 9, 8, 7, 6, 5, 0, 1, 2, 3, 4, 10, 9, 8, 7, 6, 5,
0, 1, 2, 3, 4, 10, 9, 8, 7, 6, 5, 0, 1, 2, 3, 4]
SetType cannot be cast to java.lang.Comparable
HashType cannot be cast to java.lang.Comparable
*/
