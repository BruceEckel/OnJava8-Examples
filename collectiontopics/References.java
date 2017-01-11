// collectiontopics/References.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Demonstrates Reference objects
import java.lang.ref.*;
import java.util.*;

class VeryBig {
  private static final int SIZE = 10000;
  private long[] la = new long[SIZE];
  private String ident;
  public VeryBig(String id) { ident = id; }
  @Override
  public String toString() { return ident; }
  @Override
  protected void finalize() {
    System.out.println("Finalizing " + ident);
  }
}

public class References {
  private static ReferenceQueue<VeryBig> rq =
    new ReferenceQueue<>();
  public static void checkQueue() {
    Reference<? extends VeryBig> inq = rq.poll();
    if(inq != null)
      System.out.println("In queue: " + inq.get());
  }
  public static void main(String[] args) {
    int size = 10;
    // Or, choose size via the command line:
    if(args.length > 0)
      size = new Integer(args[0]);
    LinkedList<SoftReference<VeryBig>> sa =
      new LinkedList<>();
    for(int i = 0; i < size; i++) {
      sa.add(new SoftReference<>(
        new VeryBig("Soft " + i), rq));
      System.out.println(
        "Just created: " + sa.getLast());
      checkQueue();
    }
    LinkedList<WeakReference<VeryBig>> wa =
      new LinkedList<>();
    for(int i = 0; i < size; i++) {
      wa.add(new WeakReference<>(
        new VeryBig("Weak " + i), rq));
      System.out.println(
        "Just created: " + wa.getLast());
      checkQueue();
    }
    SoftReference<VeryBig> s =
      new SoftReference<>(new VeryBig("Soft"));
    WeakReference<VeryBig> w =
      new WeakReference<>(new VeryBig("Weak"));
    System.gc();
    LinkedList<PhantomReference<VeryBig>> pa =
      new LinkedList<>();
    for(int i = 0; i < size; i++) {
      pa.add(new PhantomReference<>(
        new VeryBig("Phantom " + i), rq));
      System.out.println(
        "Just created: " + pa.getLast());
      checkQueue();
    }
  }
}
/* Output: (First and Last 10 Lines)
Just created: java.lang.ref.SoftReference@1db9742
Just created: java.lang.ref.SoftReference@106d69c
Just created: java.lang.ref.SoftReference@52e922
Just created: java.lang.ref.SoftReference@25154f
Just created: java.lang.ref.SoftReference@10dea4e
Just created: java.lang.ref.SoftReference@647e05
Just created: java.lang.ref.SoftReference@1909752
Just created: java.lang.ref.SoftReference@1f96302
Just created: java.lang.ref.SoftReference@14eac69
Just created: java.lang.ref.SoftReference@a57993
...________...________...________...________...
Just created: java.lang.ref.PhantomReference@1ee12a7
In queue: null
Just created: java.lang.ref.PhantomReference@10bedb4
In queue: null
Just created: java.lang.ref.PhantomReference@103dbd3
In queue: null
Just created: java.lang.ref.PhantomReference@167cf4d
In queue: null
Just created: java.lang.ref.PhantomReference@a987ac
In queue: null
*/
