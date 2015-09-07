// enums/Competitor.java
// ©2015 MindView LLC: see Copyright.txt
// Switching one enum on another.
package enums;

public interface Competitor<T extends Competitor<T>> {
  Outcome compete(T competitor);
}
