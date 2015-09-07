// containersindepth/StringHashCode.java
// ©2015 MindView LLC: see Copyright.txt

public class StringHashCode {
  public static void main(String[] args) {
    String[] hellos = "Hello Hello".split(" ");
    System.out.println(hellos[0].hashCode());
    System.out.println(hellos[1].hashCode());
  }
}
/* Output:
69609650
69609650
*/
