// patterns/TestSingle.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.

public class TestSingle {
  public static void main(String[] args) {
    Single<String> ss = new Single<>("hello");
    System.out.println(ss.get());
    try {
      Single<String> ss2 = new Single<>("world");
    } catch(Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
/* Output:
hello
Attempt to reassign Single<String>
*/
