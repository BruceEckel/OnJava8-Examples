// verifying/CountedListTest.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// Simple use of JUnit to test CountedList.
package verifying;
import java.util.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class CountedListTest {
  private CountedList list;
  @BeforeEach
  public void initialize() {
    list = new CountedList();
    System.out.println("Set up for " + list.getId());
    for(int i = 0; i < 3; i++)
      list.add(Integer.toString(i));
  }
  @AfterEach
  public void cleanup() {
    System.out.println("Cleaning up " + list.getId());
  }
  // All tests are marked with the @Test annotation:
  @Test
  public void insert() {
    System.out.println("Running testInsert()");
    assertEquals(list.size(), 3);
    list.add(1, "Insert");
    assertEquals(list.size(), 4);
    assertEquals(list.get(1), "Insert");
  }
  @Test
  public void replace() {
    System.out.println("Running testReplace()");
    assertEquals(list.size(), 3);
    list.set(1, "Replace");
    assertEquals(list.size(), 3);
    assertEquals(list.get(1), "Replace");
  }
  // A helper method to reduce code duplication. As long
  // as it isn't annotated with @Test, it will not
  // be automatically executed by JUnit.
  private
  void compare(List<String> lst, String[] strs) {
    String[] array = lst.toArray(new String[0]);
    assertTrue(array.length == strs.length,
      "Arrays not the same length");
    for(int i = 0; i < array.length; i++)
      assertEquals(strs[i], array[i]);
  }
  @Test
  public void order() {
    System.out.println("Running testOrder()");
    compare(list, new String[] { "0", "1", "2" });
  }
  @Test
  public void remove() {
    System.out.println("Running testRemove()");
    assertEquals(list.size(), 3);
    list.remove(1);
    assertEquals(list.size(), 2);
    compare(list, new String[] { "0", "2" });
  }
  @Test
  public void addAll() {
    System.out.println("Running testAddAll()");
    list.addAll(Arrays.asList(new String[] {
      "An", "African", "Swallow"}));
    assertEquals(list.size(), 6);
    compare(list, new String[] { "0", "1", "2",
       "An", "African", "Swallow" });
  }
}
/* Output:
CountedList #0
Running testAddAll()
CountedList #1
Running testInsert()
CountedList #2
Running testRemove()
CountedList #3
Running testOrder()
CountedList #4
Running testReplace()
*/
