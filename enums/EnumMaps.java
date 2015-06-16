//: enums/EnumMaps.java
// ©2015 MindView LLC: see Copyright.txt
// Basics of EnumMaps.
package enums;
import java.util.*;
import static enums.AlarmPoints.*;
import static com.mindviewinc.util.Print.*;

interface Command { void action(); }

public class EnumMaps {
  public static void main(String[] args) {
    EnumMap<AlarmPoints,Command> em =
      new EnumMap<>(AlarmPoints.class);
    em.put(KITCHEN, () -> print("Kitchen fire!"));
    em.put(BATHROOM, () -> print("Bathroom alert!"));
    for(Map.Entry<AlarmPoints,Command> e : em.entrySet()) {
      printnb(e.getKey() + ": ");
      e.getValue().action();
    }
    try { // If there's no value for a particular key:
      em.get(UTILITY).action();
    } catch(Exception e) {
      print("Expected: " + e);
    }
  }
} /* Output:
BATHROOM: Bathroom alert!
KITCHEN: Kitchen fire!
Expected: java.lang.NullPointerException
*///:~
