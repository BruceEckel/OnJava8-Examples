// collections/CopyRecord.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// {NewFeature} Since JDK 16

record R(int a, double b, char c) {}

public class CopyRecord {
  public static void main(String[] args) {
    var r1 = new R(11, 2.2, 'z');
    var r2 = new R(r1.a(), r1.b(), r1.c());
    System.out.println(r1.equals(r2));
  }
}
/* Output:
true
*/
