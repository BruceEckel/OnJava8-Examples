// interfaces/filters/Waveform.java
// ©2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
package interfaces.filters;

public class Waveform {
  private static long counter;
  private final long id = counter++;
  @Override
  public String toString() {
    return "Waveform " + id;
  }
}
