// lowlevel/SerialNumberSupplier.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.

public class SerialNumberSupplier {
  private static volatile int serialNumber = 0;
  public static int nextSerialNumber() {
    return serialNumber++; // Not thread-safe
  }
}
