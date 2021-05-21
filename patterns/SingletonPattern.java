// patterns/SingletonPattern.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.

// final表示该类不能被继承
final class IntegerSingleton
  implements Resource<Integer> {
  // private static 是实现单例的关键 意味着不能通过外部直接访问 且只有一份副本
  // 加载类时调用静态初始化器初始化value 不是懒加载 用一个静态包装函数实现懒加载
  private static IntegerSingleton value =
    new IntegerSingleton();
  // 私有静态成员 只能通过公共方法访问
  private static Integer i = Integer.valueOf(0);
  // 构造函数是私有的
  private IntegerSingleton() {
    System.out.println("IntegerSingleton()");
  }
  // 通过公共静态方法访问单例 这是访问单例的唯一入口点
  public static IntegerSingleton instance() {
    return value;
  }
  // 私有静态成员的唯一访问点 且只能通过单例访问
  @Override
  public synchronized Integer get() { return i; }
  @Override
  public synchronized void set(Integer x) { i = x; }
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

// my code
class MySingleton1 {
  private static MySingleton1 instance = new MySingleton1();
  private static int i;
  private MySingleton1() {}
  public static MySingleton1 getInstance() { return instance; }
  public int getI() { return i; }
  public void setI(int val) { i = val; }

  public static void main(String[] args) {
    MySingleton1 single = MySingleton1.getInstance();
    single.setI(100);
    MySingleton1 single2 = MySingleton1.getInstance();
    single2.setI(200);
    System.out.println(single.getI()); // 200
    System.out.println(single == single2);
  }
}
// double check version
class MySingleton2 {
  private static MySingleton2 instance = null;
  private MySingleton2() {}

  public static MySingleton2 getInstance() {
    if (instance == null) {
      synchronized (MySingleton2.class) {
        if (instance == null)
          instance = new MySingleton2();
      }
    }
    return instance;
  }
}


