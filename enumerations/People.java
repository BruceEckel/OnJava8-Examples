// enumerations/People.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// {NewFeature} Preview in JDK 17
// Compile with javac flags:
//   --enable-preview --source 17
// Run with java flag: --enable-preview
import java.util.*;

record Person(String name, int age) {}

public class People {
  static String categorize(Person person) {
    return switch(person) {
      case Person p && p.age() > 40          // [1]
        -> p + " is middle aged";
      case Person p &&
        (p.name().contains("D") || p.age() == 14)
        -> p + " D or 14";
      case Person p && !(p.age() >= 100)     // [2]
        -> p + " is not a centenarian";
      case Person p -> p + " Everyone else";
    };
  }
  public static void main(String[] args) {
    List.of(
      new Person("Dorothy", 15),
      new Person("John Bigboote", 42),
      new Person("Morty", 14),
      new Person("Morty Jr.", 1),
      new Person("Jose", 39),
      new Person("Kane", 118)
    ).forEach(
      p -> System.out.println(categorize(p))
    );
  }
}
/* Output:
Person[name=Dorothy, age=15] D or 14
Person[name=John Bigboote, age=42] is middle aged
Person[name=Morty, age=14] D or 14
Person[name=Morty Jr., age=1] is not a centenarian
Person[name=Jose, age=39] is not a centenarian
Person[name=Kane, age=118] is middle aged
*/
