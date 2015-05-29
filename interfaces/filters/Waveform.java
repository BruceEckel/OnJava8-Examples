//: interfaces/filters/Waveform.java
// ©2015 MindView LLC: see Copyright.txt
package interfaces.filters;

public class Waveform {
  private static long counter;
  private final long id = counter++;
  @Override
  public String toString() { return "Waveform " + id; }
} ///:~
