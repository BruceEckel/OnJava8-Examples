// interfaces/filters/HighPass.java
// ©2015 MindView LLC: see Copyright.txt
package interfaces.filters;

public class HighPass extends Filter {
  double cutoff;
  public HighPass(double cutoff) { this.cutoff = cutoff; }
  @Override
  public Waveform process(Waveform input) { return input; }
}
