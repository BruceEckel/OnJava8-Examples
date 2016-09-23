// onjava/Count.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Generate incremental values of different types
package onjava;
import java.util.*;
import java.util.function.*;
import static onjava.ConvertTo.*;

public interface Count {
  public static class Boolean
  implements Supplier<java.lang.Boolean> {
    private boolean b = true;
    @Override
    public java.lang.Boolean get() {
      b = !b;
      return java.lang.Boolean.valueOf(b);
    }
    public java.lang.Boolean get(int n) { return get(); }
    public java.lang.Boolean[] array(int sz) {
      java.lang.Boolean[] result =
        new java.lang.Boolean[sz];
      Arrays.setAll(result, n -> get());
      return result;
    }
  }
  public static class boolean_ {
    private boolean b = true;
    public boolean get() {
      b = !b;
      return b;
    }
    public boolean get(int n) { return get(); }
    public boolean[] array(int sz) {
      return primitive(new Boolean().array(sz));
    }
  }
  public static class Byte
  implements Supplier<java.lang.Byte> {
    private byte b;
    @Override
    public java.lang.Byte get() { return b++; }
    public java.lang.Byte get(int n) { return get(); }
    public java.lang.Byte[] array(int sz) {
      java.lang.Byte[] result = new java.lang.Byte[sz];
      Arrays.setAll(result, n -> get());
      return result;
    }
  }
  public static class byte_ {
    private byte b;
    public byte get() { return b++; }
    public byte get(int n) { return get(); }
    public byte[] array(int sz) {
      return primitive(new Byte().array(sz));
    }
  }
  static final char[] chars =
    "abcdefghijklmnopqrstuvwxyz".toCharArray();
  public static class Character
  implements Supplier<java.lang.Character> {
    private int i;
    @Override
    public java.lang.Character get() {
      i = (i + 1) % chars.length;
      return chars[i];
    }
    public java.lang.Character get(int n) {
      return get();
    }
    public java.lang.Character[] array(int sz) {
      java.lang.Character[] result =
        new java.lang.Character[sz];
      Arrays.setAll(result, n -> get());
      return result;
    }
  }
  public static class char_ {
    private int i;
    public char get() {
      i = (i + 1) % chars.length;
      return chars[i];
    }
    public char get(int n) { return get(); }
    public char[] array(int sz) {
      return primitive(new Character().array(sz));
    }
  }
  public static class Short
  implements Supplier<java.lang.Short> {
    short s;
    @Override
    public java.lang.Short get() { return s++; }
    public java.lang.Short get(int n) { return get(); }
    public java.lang.Short[] array(int sz) {
      java.lang.Short[] result = new java.lang.Short[sz];
      Arrays.setAll(result, n -> get());
      return result;
    }
  }
  public static class short_ {
    short s;
    public short get() { return s++; }
    public short get(int n) { return get(); }
    public short[] array(int sz) {
      return primitive(new Short().array(sz));
    }
  }
  public static class Integer
  implements Supplier<java.lang.Integer> {
    int i;
    @Override
    public java.lang.Integer get() { return i++; }
    public java.lang.Integer get(int n) { return get(); }
    public java.lang.Integer[] array(int sz) {
      java.lang.Integer[] result =
        new java.lang.Integer[sz];
      Arrays.setAll(result, n -> get());
      return result;
    }
  }
  public static class int_ implements IntSupplier {
    int i;
    public int get() { return i++; }
    public int get(int n) { return get(); }
    @Override
    public int getAsInt() { return get(); }
    public int[] array(int sz) {
      return primitive(new Integer().array(sz));
    }
  }
  public static class Long
  implements Supplier<java.lang.Long> {
    private long l;
    @Override
    public java.lang.Long get() { return l++; }
    public java.lang.Long get(int n) { return get(); }
    public java.lang.Long[] array(int sz) {
      java.lang.Long[] result = new java.lang.Long[sz];
      Arrays.setAll(result, n -> get());
      return result;
    }
  }
  public static class long_ implements LongSupplier {
    private long l;
    public long get() { return l++; }
    public long get(int n) { return get(); }
    @Override
    public long getAsLong() { return get(); }
    public long[] array(int sz) {
      return primitive(new Long().array(sz));
    }
  }
  public class Float
  implements Supplier<java.lang.Float> {
    private int i;
    @Override
    public java.lang.Float get() {
      return java.lang.Float.valueOf(i++);
    }
    public java.lang.Float get(int n) { return get(); }
    public java.lang.Float[] array(int sz) {
      java.lang.Float[] result = new java.lang.Float[sz];
      Arrays.setAll(result, n -> get());
      return result;
    }
  }
  public class float_ {
    private int i;
    public float get() { return i++; }
    public float get(int n) { return get(); }
    public float[] array(int sz) {
      return primitive(new Float().array(sz));
    }
  }
  public class Double
  implements Supplier<java.lang.Double> {
    private int i;
    @Override
    public java.lang.Double get() {
      return java.lang.Double.valueOf(i++);
    }
    public java.lang.Double get(int n) { return get(); }
    public java.lang.Double[] array(int sz) {
      java.lang.Double[] result =
        new java.lang.Double[sz];
      Arrays.setAll(result, n -> get());
      return result;
    }
  }
  public class double_ implements DoubleSupplier {
    private int i;
    public double get() { return i++; }
    public double get(int n) { return get(); }
    @Override
    public double getAsDouble() { return get(0); }
    public double[] array(int sz) {
      return primitive(new Double().array(sz));
    }
  }
}
