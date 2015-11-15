// concurrency/MultiLock.java
// ©2016 MindView LLC: see Copyright.txt
// One thread can reacquire the same lock.

public class MultiLock {
  public synchronized void f1(int count) {
    if(count-- > 0) {
      System.out.println("f1() calling f2() with count " + count);
      f2(count);
    }
  }
  public synchronized void f2(int count) {
    if(count-- > 0) {
      System.out.println("f2() calling f1() with count " + count);
      f1(count);
    }
  }
  public static void main(String[] args) throws Exception {
    final MultiLock multiLock = new MultiLock();
    new Thread() {
      @Override
      public void run() {
        multiLock.f1(10);
      }
    }.start();
  }
}
/* Output:
f1() calling f2() with count 9
f2() calling f1() with count 8
f1() calling f2() with count 7
f2() calling f1() with count 6
f1() calling f2() with count 5
f2() calling f1() with count 4
f1() calling f2() with count 3
f2() calling f1() with count 2
f1() calling f2() with count 1
f2() calling f1() with count 0
*/
