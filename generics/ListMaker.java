// generics/ListMaker.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
import java.util.*;

public class ListMaker<T> {
  List<T> create() { return new ArrayList<>(); }
  public static void main(String[] args) {
    ListMaker<String> stringMaker= new ListMaker<>();
    List<String> stringList = stringMaker.create();
  }
}
