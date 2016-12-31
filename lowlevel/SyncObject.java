// lowlevel/SyncObject.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Synchronizing on another object

class DualSynch {
  private Object syncObject = new Object();
  public synchronized void f() {
    for(int i = 0; i < 5; i++) {
      System.out.println("f()");
      Thread.yield();
    }
  }
  public void g() {
    synchronized(syncObject) {
      for(int i = 0; i < 5; i++) {
        System.out.println("g()");
        Thread.yield();
      }
    }
  }
}

public class SyncObject {
  public static void main(String[] args) {
    final DualSynch ds = new DualSynch();
    new Thread() {
      @Override
      public void run() {
        ds.f();
      }
    }.start();
    ds.g();
  }
}
/* Output:
g()
g()
g()
g()
g()
f()
f()
f()
f()
f()
*/
