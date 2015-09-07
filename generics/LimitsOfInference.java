// generics/LimitsOfInference.java
// ©2015 MindView LLC: see Copyright.txt
import typeinfo.pets.*;
import java.util.*;

public class LimitsOfInference {
  static void
  f(Map<Person, List<? extends Pet>> petPeople) {}
  public static void main(String[] args) {
    // f(New.map()); // Does not compile
  }
}
/* Output: (None) */
