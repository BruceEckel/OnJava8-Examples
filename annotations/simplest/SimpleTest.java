//: annotations/simplest/SimpleTest.java
// Test the "Simple" annotation
package annotations.simplest;

@Simple
public class SimpleTest {
  @Simple
  int i;
  @Simple
  public SimpleTest() {}
  @Simple
  public void foo() {
    System.out.println("SimpleTest.foo()");
  }
  @Simple
  public static void main(String[] args) {
    @Simple
    SimpleTest st = new SimpleTest();
    st.foo();
  }
} ///:~
