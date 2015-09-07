// innerclasses/TestBed.java
// ©2015 MindView LLC: see Copyright.txt
// Putting test code in a nested class.
// {main: TestBed$Tester}

public class TestBed {
  public void f() { System.out.println("f()"); }
  public static class Tester {
    public static void main(String[] args) {
      TestBed t = new TestBed();
      t.f();
    }
  }
}
/* Output:
f()
*/
