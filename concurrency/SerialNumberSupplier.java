// concurrency/SerialNumberSupplier.java

public class SerialNumberSupplier {
  private static volatile int serialNumber = 0;
  public static int nextSerialNumber() {
    return serialNumber++; // Not thread-safe
  }
}
