// enums/Competitor.java
// Switching one enum on another.
package enums;

public interface Competitor<T extends Competitor<T>> {
  Outcome compete(T competitor);
}
