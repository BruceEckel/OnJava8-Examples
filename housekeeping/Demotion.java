// housekeeping/Demotion.java
// Demotion of primitives and overloading.

public class Demotion {
  void f1(char x) { System.out.println("f1(char)"); }
  void f1(byte x) { System.out.println("f1(byte)"); }
  void f1(short x) { System.out.println("f1(short)"); }
  void f1(int x) { System.out.println("f1(int)"); }
  void f1(long x) { System.out.println("f1(long)"); }
  void f1(float x) { System.out.println("f1(float)"); }
  void f1(double x) { System.out.println("f1(double)"); }

  void f2(char x) { System.out.println("f2(char)"); }
  void f2(byte x) { System.out.println("f2(byte)"); }
  void f2(short x) { System.out.println("f2(short)"); }
  void f2(int x) { System.out.println("f2(int)"); }
  void f2(long x) { System.out.println("f2(long)"); }
  void f2(float x) { System.out.println("f2(float)"); }

  void f3(char x) { System.out.println("f3(char)"); }
  void f3(byte x) { System.out.println("f3(byte)"); }
  void f3(short x) { System.out.println("f3(short)"); }
  void f3(int x) { System.out.println("f3(int)"); }
  void f3(long x) { System.out.println("f3(long)"); }

  void f4(char x) { System.out.println("f4(char)"); }
  void f4(byte x) { System.out.println("f4(byte)"); }
  void f4(short x) { System.out.println("f4(short)"); }
  void f4(int x) { System.out.println("f4(int)"); }

  void f5(char x) { System.out.println("f5(char)"); }
  void f5(byte x) { System.out.println("f5(byte)"); }
  void f5(short x) { System.out.println("f5(short)"); }

  void f6(char x) { System.out.println("f6(char)"); }
  void f6(byte x) { System.out.println("f6(byte)"); }

  void f7(char x) { System.out.println("f7(char)"); }

  void testDouble() {
    double x = 0;
    System.out.println("double argument:");
    f1(x);f2((float)x);f3((long)x);f4((int)x);
    f5((short)x);f6((byte)x);f7((char)x);
  }
  public static void main(String[] args) {
    Demotion p = new Demotion();
    p.testDouble();
  }
}
/* Output:
double argument:
f1(double)
f2(float)
f3(long)
f4(int)
f5(short)
f6(byte)
f7(char)
*/
