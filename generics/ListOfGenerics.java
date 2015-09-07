// generics/ListOfGenerics.java
// ©2015 MindView LLC: see Copyright.txt
import java.util.*;

public class ListOfGenerics<T> {
  private List<T> array = new ArrayList<>();
  public void add(T item) { array.add(item); }
  public T get(int index) { return array.get(index); }
}
