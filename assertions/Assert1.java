// assertions/Assert1.java
// ©2015 MindView LLC: see Copyright.txt
// Non-informative style of assert
// {JVMArgs: -ea} // Must run with -ea
// {ThrowsException}

public class Assert1 {
  public static void main(String[] args) {
    assert false;
  }
}
/* Output:
___[ Error Output ]___
Exception in thread "main" java.lang.AssertionError
        at Assert1.main(Assert1.java:8)
___[ Exception is Expected ]___
*/
