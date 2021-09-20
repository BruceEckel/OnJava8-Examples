// collections/GenericTypeInference.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// {NewFeature} Since JDK 11
import java.util.*;

public class GenericTypeInference {
  void old() {
    ArrayList<Apple> apples = new ArrayList<>();
  }
  void modern() {
    var apples = new ArrayList<Apple>();
  }
  void pitFall() {
    var apples = new ArrayList<>();
    apples.add(new Apple());
    apples.get(0); // Comes back as plain Object
  }
}
