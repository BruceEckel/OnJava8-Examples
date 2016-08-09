// verifying/Assert1.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// Non-informative style of assert
// Must run with -ea:
// {java -ea Assert1}
// {ThrowsException}

public class Assert1 {
  public static void main(String[] args) {
    assert false;
  }
}
/* Output:
___[ Error Output ]___
Exception in thread "main" java.lang.AssertionError
        at Assert1.main(Assert1.java:9)
*/
