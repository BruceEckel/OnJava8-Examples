// threads/NumberOfProcessors.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.

public class NumberOfProcessors {
  public static void main(String[] args) {
    System.out.println(
      Runtime.getRuntime().availableProcessors());
  }
}
/* Output:
8
*/
