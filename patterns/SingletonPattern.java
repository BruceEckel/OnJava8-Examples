// patterns/SingletonPattern.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.

final class IntegerSingleton
  implements Resource<Integer> {
  private static IntegerSingleton value =
    new IntegerSingleton();
  private Integer i = Integer.valueOf(0);
  private IntegerSingleton() {
    System.out.println("IntegerSingleton()");
  }
  public static IntegerSingleton instance() {
    return value;
  }
  @Override public synchronized
  Integer get() { return i; }
  @Override public synchronized
  void set(Integer x) { i = x; }
}

public class SingletonPattern {
  public static <T> void show(Resource<T> r) {
    T val = r.get();
    System.out.println(val);
  }
  public static <T> void put(Resource<T> r, T val) {
    r.set(val);
  }
  public static void main(String[] args) {
    System.out.println("Inside main()");
    Resource<Integer> ir =
      IntegerSingleton.instance();
    Resource<Integer> ir2 =
      IntegerSingleton.instance();
    show(ir);
    put(ir2, Integer.valueOf(9));
    show(ir);
  }
}
/* Output:
Inside main()
IntegerSingleton()
0
9
*/
