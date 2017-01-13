// lowlevel/SerialNumberChecker.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Operations that might seem safe are not,
// when threads are present.
import java.util.*;
import java.util.concurrent.*;
import onjava.Nap;

// Reuses storage so we don't run out of memory:
class CircularSet {
  private int[] array;
  private int size;
  private int index = 0;
  public CircularSet(int size) {
    array = new int[size];
    // Initialize to a value not produced
    // by the SerialNumberSupplier:
    Arrays.fill(array, -1);
    this.size = size;
  }
  public synchronized void add(int i) {
    array[index] = i;
    // Wrap index and write over old elements:
    index = ++index % size;
  }
  public synchronized boolean contains(int val) {
    for(int i = 0; i < size; i++)
      if(array[i] == val) return true;
    return false;
  }
}

public class SerialNumberChecker implements Runnable {
  private CircularSet serials = new CircularSet(1000);
  @Override
  public void run() {
    while(true) {
      int serial =
        SerialNumberSupplier.nextSerialNumber();
      if(serials.contains(serial)) {
        System.out.println("Duplicate: " + serial);
        System.exit(0);
      }
      serials.add(serial);
    }
  }
  public static void main(String[] args) {
    for(int i = 0; i < 10; i++)
      CompletableFuture.runAsync(
        new SerialNumberChecker());
    new Nap(4000, "No duplicates detected");
  }
}
/* Output:
Duplicate: 4119
*/
