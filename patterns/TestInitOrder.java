/**
 *
 * @author yangWu
 * @date 2021/5/2 9:46 下午
 * @version 1.0
 */
public class TestInitOrder {
  static {
    System.out.println(TestInitOrder.stat1);
    System.out.println(TestInitOrder.stat2);
    System.out.println(TestInitOrder.str);
    System.out.println(TestInitOrder.str2);

    str = "something";

    System.out.println(TestInitOrder.str);
    System.out.println(TestInitOrder.str2);
    System.out.println(TestInitOrder.lazy);
    System.out.println(TestInitOrder.second);
  }

  private static final int stat1 = 10;
  static final String str2 = "sdfff";
  static String str = "crap";
  private static int stat2 = 19;
  static final Second second = new Second();
  static final int lazy;

  static {
    lazy = 20;
  }

  static {
    System.out.println(TestInitOrder.str2);
    System.out.println(TestInitOrder.stat2);
    System.out.println(TestInitOrder.str);
    System.out.println(TestInitOrder.lazy);
    System.out.println(TestInitOrder.second);
  }

  public static void main(String args[]) {
  }

}

class Second {
  public Second() {
    System.out.println(TestInitOrder.second);
  }

}
