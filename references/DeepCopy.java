// references/DeepCopy.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// Cloning a composed object
// (Install libraries from junit.org)
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class DepthReading implements Cloneable {
  private double depth;
  public DepthReading(double depth) {
    this.depth = depth;
  }
  @Override
  public DepthReading clone() {
    try {
      return (DepthReading)super.clone();
    } catch(CloneNotSupportedException e) {
      throw new RuntimeException(e);
    }
  }
  public double getDepth() { return depth; }
  public void setDepth(double depth) {
    this.depth = depth;
  }
  @Override
  public String toString() {
    return String.valueOf(depth);
  }
}

class TemperatureReading implements Cloneable {
  private long time;
  private double temperature;
  public TemperatureReading(double temperature) {
    time = System.currentTimeMillis();
    this.temperature = temperature;
  }
  @Override
  public TemperatureReading clone() {
    try {
      return (TemperatureReading)super.clone();
    } catch(CloneNotSupportedException e) {
      throw new RuntimeException(e);
    }
  }
  public double getTemperature() {
    return temperature;
  }
  public void setTemperature(double temp) {
    this.temperature = temp;
  }
  @Override
  public String toString() {
    return String.valueOf(temperature);
  }
}

class OceanReading implements Cloneable {
  private DepthReading depth;
  private TemperatureReading temperature;
  public
  OceanReading(double tdata, double ddata) {
    temperature = new TemperatureReading(tdata);
    depth = new DepthReading(ddata);
  }
  @Override
  public OceanReading clone() {
    OceanReading or = null;
    try {
      or = (OceanReading)super.clone();
    } catch(CloneNotSupportedException e) {
      throw new RuntimeException(e);
    }
    // Must clone references:
    or.depth = (DepthReading)or.depth.clone();
    or.temperature =
      (TemperatureReading)or.temperature.clone();
    return or;
  }
  public TemperatureReading getTemperatureReading() {
    return temperature;
  }
  public void
  setTemperatureReading(TemperatureReading tr) {
    temperature = tr;
  }
  public DepthReading getDepthReading() {
    return depth;
  }
  public void setDepthReading(DepthReading dr) {
    this.depth = dr;
  }
  @Override
  public String toString() {
    return "temperature: " + temperature +
      ", depth: " + depth;
  }
}

public class DeepCopy {
  @Test
  public void testClone() {
    OceanReading reading =
      new OceanReading(33.9, 100.5);
    // Now clone it:
    OceanReading clone = reading.clone();
    TemperatureReading tr =
      clone.getTemperatureReading();
    tr.setTemperature(tr.getTemperature() + 1);
    clone.setTemperatureReading(tr);
    DepthReading dr = clone.getDepthReading();
    dr.setDepth(dr.getDepth() + 1);
    clone.setDepthReading(dr);
    assertEquals(reading.toString(),
      "temperature: 33.9, depth: 100.5");
    assertEquals(clone.toString(),
      "temperature: 34.9, depth: 101.5");
  }
  public static void main(String[] args) {
    org.junit.runner.JUnitCore.runClasses(
      DeepCopy.class);
  }
}
