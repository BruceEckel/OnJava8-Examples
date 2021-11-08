// enumerations/PetPatternMatch.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// {NewFeature} Preview in JDK 17
// Compile with javac flags:
//   --enable-preview --source 17
import java.util.*;

public class PetPatternMatch {
  static void careFor(Pet p) {
    switch(p) {
      case Dog d -> d.walk();
      case Fish f -> f.changeWater();
      case Pet sp -> sp.feed();
    };
  }
  static void petCare() {
    List.of(new Dog(), new Fish())
      .forEach(p -> careFor(p));
  }
}
