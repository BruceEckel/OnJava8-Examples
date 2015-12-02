// interfaces/filters/LowPass.java
// ©2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
package interfaces.filters;

public class LowPass extends Filter {
  double cutoff;
  public LowPass(double cutoff) {
    this.cutoff = cutoff;
  }
  @Override
  public Waveform process(Waveform input) {
    return input; // Dummy processing
  }
}
