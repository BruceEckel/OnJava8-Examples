// enums/Competitor.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// Switching one enum on another
package enums;

public interface Competitor<T extends Competitor<T>> {
  Outcome compete(T competitor);
}
