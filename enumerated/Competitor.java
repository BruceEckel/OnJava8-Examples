//: enumerated/Competitor.java
// ©2015 MindView LLC: see Copyright.txt
// Switching one enum on another.
package enumerated;

public interface Competitor<T extends Competitor<T>> {
  Outcome compete(T competitor);
} ///:~
