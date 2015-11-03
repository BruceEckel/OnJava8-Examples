// com/mindviewinc/util/RandomSupplier.java
// Suppliers that produce random values.
package com.mindviewinc.util;
import java.util.*;
import java.util.function.*;

public class RandomSupplier {
  private static Random r = new Random(47);
  public static class
  Boolean implements Supplier<java.lang.Boolean> {
    @Override
    public java.lang.Boolean get() {
      return r.nextBoolean();
    }
  }
  public static class
  Byte implements Supplier<java.lang.Byte> {
    @Override
    public java.lang.Byte get() {
      return (byte)r.nextInt();
    }
  }
  public static class
  Character implements Supplier<java.lang.Character> {
    @Override
    public java.lang.Character get() {
      return CountingSupplier.chars[
        r.nextInt(CountingSupplier.chars.length)];
    }
  }
  public static class
  String extends CountingSupplier.String {
    // Plug in the random Character generator:
    { cg = new Character(); } // Instance initializer
    public String() {}
    public String(int length) { super(length); }
  }
  public static class
  Short implements Supplier<java.lang.Short> {
    @Override
    public java.lang.Short get() {
      return (short)r.nextInt();
    }
  }
  public static class
  Integer implements Supplier<java.lang.Integer> {
    private int mod = 10000;
    public Integer() {}
    public Integer(int modulo) { mod = modulo; }
    @Override
    public java.lang.Integer get() {
      return r.nextInt(mod);
    }
  }
  public static class
  Long implements Supplier<java.lang.Long> {
    private int mod = 10000;
    public Long() {}
    public Long(int modulo) { mod = modulo; }
    @Override
    public java.lang.Long get() {
      return (long) r.nextInt(mod);
    }
  }
  public static class
  Float implements Supplier<java.lang.Float> {
    @Override
    public java.lang.Float get() {
      // Trim all but the first two decimal places:
      int trimmed = Math.round(r.nextFloat() * 100);
      return ((float)trimmed) / 100;
    }
  }
  public static class
  Double implements Supplier<java.lang.Double> {
    @Override
    public java.lang.Double get() {
      long trimmed = Math.round(r.nextDouble() * 100);
      return ((double)trimmed) / 100;
    }
  }
}
