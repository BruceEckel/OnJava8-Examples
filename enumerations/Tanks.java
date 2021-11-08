// enumerations/Tanks.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// {NewFeature} Preview in JDK 17
// Compile with javac flags:
//   --enable-preview --source 17
// Run with java flag: --enable-preview
import java.util.*;

enum Type { TOXIC, FLAMMABLE, NEUTRAL }

record Level(int percent) {
  Level {
    if(percent < 0 || percent > 100)
      throw new IndexOutOfBoundsException(
        percent + " percent");
  }
}

record Tank(Type type, Level level) {}

public class Tanks {
  static String check(Tank tank) {
    return switch(tank) {
      case Tank t && t.type() == Type.TOXIC
        -> "Toxic: " + t;
      case Tank t && (                 // [1]
          t.type() == Type.TOXIC &&
          t.level().percent() < 50
        ) -> "Toxic, low: " + t;
      case Tank t && t.type() == Type.FLAMMABLE
        -> "Flammable: " + t;
      // Equivalent to "default":
      case Tank t -> "Other Tank: " + t;
    };
  }
  public static void main(String[] args) {
    List.of(
      new Tank(Type.TOXIC, new Level(49)),
      new Tank(Type.FLAMMABLE, new Level(52)),
      new Tank(Type.NEUTRAL, new Level(75))
    ).forEach(
      t -> System.out.println(check(t))
    );
  }
}
