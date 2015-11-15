// interfaces/filters/LowPass.java
// ©2016 MindView LLC: see Copyright.txt
package interfaces.filters;

public class LowPass extends Filter {
  double cutoff;
  public LowPass(double cutoff) { this.cutoff = cutoff; }
  @Override
  public Waveform process(Waveform input) {
    return input; // Dummy processing
  }
}
