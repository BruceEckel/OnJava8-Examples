// interfaces/filters/BandPass.java
// ©2016 MindView LLC: see Copyright.txt
package interfaces.filters;

public class BandPass extends Filter {
  double lowCutoff, highCutoff;
  public BandPass(double lowCut, double highCut) {
    lowCutoff = lowCut;
    highCutoff = highCut;
  }
  @Override
  public Waveform process(Waveform input) { return input; }
}
