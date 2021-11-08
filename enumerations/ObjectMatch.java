// enumerations/ObjectMatch.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// {NewFeature} Preview in JDK 17
// Compile with javac flags:
//   --enable-preview --source 17
// Run with java flag: --enable-preview
import java.util.*;

record XX() {}

public class ObjectMatch {
  static String match(Object o) {
    return switch(o) {
      case Dog d -> "Walk the dog";
      case Fish f -> "Change the fish water";
      case Pet sp -> "Not dog or fish";
      case String s -> "String " + s;
      case Integer i -> "Integer " + i;
      case String[] sa -> String.join(", ", sa);
      case null, XX xx -> "null or XX: " + xx;
      default -> "Something else";
    };
  }
  public static void main(String[] args) {
    List.of(new Dog(), new Fish(), new Pet(),
      "Oscar", Integer.valueOf(12),
      Double.valueOf("47.74"),
      new String[]{ "to", "the", "point" },
      new XX()
    ).forEach(
      p -> System.out.println(match(p))
    );
  }
}
/* Output:
Walk the dog
Change the fish water
Not dog or fish
String Oscar
Integer 12
Something else
to, the, point
null or Object: XX[]
*/
