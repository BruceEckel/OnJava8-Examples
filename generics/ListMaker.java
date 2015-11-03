// generics/ListMaker.java
import java.util.*;

public class ListMaker<T> {
  List<T> create() { return new ArrayList<>(); }
  public static void main(String[] args) {
    ListMaker<String> stringMaker= new ListMaker<>();
    List<String> stringList = stringMaker.create();
  }
}
/* Output: (None) */
