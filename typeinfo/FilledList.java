//: typeinfo/FilledList.java
// ©2015 MindView LLC: see Copyright.txt
import java.util.*;

class CountedInteger {
  private static long counter;
  private final long id = counter++;
  @Override
  public String toString() { return Long.toString(id); }
}

public class FilledList<T> {
  private Class<T> type;
  public FilledList(Class<T> type) { this.type = type; }
  public List<T> create(int nElements) {
    List<T> result = new ArrayList<>();
    try {
      for(int i = 0; i < nElements; i++)
        result.add(type.newInstance());
    } catch(InstantiationException |
            IllegalAccessException e) {
      throw new RuntimeException(e);
    }
    return result;
  }
  public static void main(String[] args) {
    FilledList<CountedInteger> fl =
      new FilledList<>(CountedInteger.class);
    System.out.println(fl.create(15));
  }
} /* Output:
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14]
*///:~
