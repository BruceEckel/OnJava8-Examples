// enumerations/PetPatternMatch2.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// {NewFeature} Preview in JDK 17
// Compile with javac flags:
//   --enable-preview --source 17
package sealedpet;
import java.util.*;

sealed interface Pet {
  void feed();
}

final class Dog implements Pet {
  @Override public void feed() {}
  void walk() {}
}

final class Fish implements Pet {
  @Override public void feed() {}
  void changeWater() {}
}

public class PetPatternMatch2 {
  static void careFor(Pet p) {
    switch(p) {
      case Dog d -> d.walk();
      case Fish f -> f.changeWater();
    };
  }
  static void petCare() {
    List.of(new Dog(), new Fish())
      .forEach(p -> careFor(p));
  }
}
