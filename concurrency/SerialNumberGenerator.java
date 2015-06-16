//: concurrency/SerialNumberGenerator.java
// ©2015 MindView LLC: see Copyright.txt

public class SerialNumberGenerator {
  private static volatile int serialNumber = 0;
  public static int nextSerialNumber() {
    return serialNumber++; // Not thread-safe
  }
} ///:~
