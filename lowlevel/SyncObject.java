// lowlevel/SyncObject.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Synchronizing on another object
import java.util.concurrent.*;
import onjava.Nap;

class DualSynch {
  public synchronized void f() {
    for(int i = 0; i < 5; i++)
      System.out.println("f() " + i);
  }
  private Object syncObject = new Object();
  public void g() {
    synchronized(syncObject) {
      for(int i = 0; i < 5; i++)
        System.out.println("g() " + i);
    }
  }
}

public class SyncObject {
  public static void main(String[] args) {
    DualSynch ds = new DualSynch();
    CompletableFuture.runAsync(() -> ds.f());
    ds.g();
  }
}
/* Output:
g() 0
g() 1
f() 0
g() 2
f() 1
g() 3
f() 2
g() 4
f() 3
f() 4
*/
