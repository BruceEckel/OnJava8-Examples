// onjava/CountingSupplier.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// Simple generator implementations.
package onjava;
import java.util.function.*;

public class CountingSupplier {
  public static class
  Boolean implements Supplier<java.lang.Boolean> {
    private boolean value = false;
    @Override
    public java.lang.Boolean get() {
      value = !value; // Just flips back and forth
      return value;
    }
  }
  public static class
  Byte implements Supplier<java.lang.Byte> {
    private byte value = 0;
    @Override
    public java.lang.Byte get() { return value++; }
  }
  static char[] chars = ("abcdefghijklmnopqrstuvwxyz" +
    "ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
  public static class
  Character implements Supplier<java.lang.Character> {
    int index = -1;
    @Override
    public java.lang.Character get() {
      index = (index + 1) % chars.length;
      return chars[index];
    }
  }
  public static class
  String implements Supplier<java.lang.String> {
    private int length = 7;
    Supplier<java.lang.Character> cg = new Character();
    public String() {}
    public String(int length) { this.length = length; }
    @Override
    public java.lang.String get() {
      char[] buf = new char[length];
      for(int i = 0; i < length; i++)
        buf[i] = cg.get();
      return new java.lang.String(buf);
    }
  }
  public static class
  Short implements Supplier<java.lang.Short> {
    private short value = 0;
    @Override
    public java.lang.Short get() { return value++; }
  }
  public static class
  Integer implements Supplier<java.lang.Integer> {
    private int value = 0;
    @Override
    public java.lang.Integer get() { return value++; }
  }
  public static class
  Long implements Supplier<java.lang.Long> {
    private long value = 0;
    @Override
    public java.lang.Long get() { return value++; }
  }
  public static class
  Float implements Supplier<java.lang.Float> {
    private float value = 0;
    @Override
    public java.lang.Float get() {
      float result = value;
      value += 1.0;
      return result;
    }
  }
  public static class
  Double implements Supplier<java.lang.Double> {
    private double value = 0.0;
    @Override
    public java.lang.Double get() {
      double result = value;
      value += 1.0;
      return result;
    }
  }
}
