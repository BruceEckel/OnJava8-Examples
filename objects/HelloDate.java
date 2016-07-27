// objects/HelloDate.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
import java.util.*;

/** The first On Java example program.
 * Displays a String and today's date.
 * @author Bruce Eckel
 * @author www.MindviewInc.com
 * @version 5.0
 */
public class HelloDate {
  /** Entry point to class & application.
   * @param args array of String arguments
   * @throws exceptions No exceptions thrown
   */
  public static void main(String[] args) {
    System.out.println("Hello, it's: ");
    System.out.println(new Date());
  }
}
/* Output:
Hello, it's:
Wed Jul 27 10:50:45 MDT 2016
*/
