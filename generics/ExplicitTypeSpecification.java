//: generics/ExplicitTypeSpecification.java
// ©2015 MindView LLC: see Copyright.txt
import typeinfo.pets.*;
import java.util.*;
import com.mindviewinc.util.*;

public class ExplicitTypeSpecification {
  static void f(Map<Person, List<Pet>> petPeople) {}
  public static void main(String[] args) {
    f(New.<Person, List<Pet>>map());
  }
} /* Output: (None) *///:~
