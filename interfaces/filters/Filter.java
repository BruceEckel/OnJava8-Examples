// interfaces/filters/Filter.java
// ©2015 MindView LLC: see Copyright.txt
package interfaces.filters;

public class Filter {
  public String name() {
    return getClass().getSimpleName();
  }
  public Waveform process(Waveform input) { return input; }
}
