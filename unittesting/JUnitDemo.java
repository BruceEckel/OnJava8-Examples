//: unittesting/JUnitDemo.java
// ©2015 MindView LLC: see Copyright.txt
// Simple use of JUnit to test ArrayList
// (Install libraries from www.junit.org)
import java.util.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static java.lang.System.out;

// So we can see the list objects being created,
// and keep track of when they are cleaned up:
class CountedList extends ArrayList<String> {
  private static int counter = 0;
  private int id = counter++;
  public CountedList() {
    out.println("CountedList #" + id);
  }
  public int getId() { return id; }
}

public class JUnitDemo {
  private CountedList list = new CountedList();
  // You can use the constructor instead of setUp():
  public JUnitDemo() {
    for(int i = 0; i < 3; i++)
      list.add("" + i);
  }
  // Thus, setUp() is optional, but is run right
  // before the test:
  protected void setUp() {
    out.println("Set up for " + list.getId());
  }
  // tearDown() is also optional, and is called after
  // each test. setUp() and tearDown() can be either
  // protected or public:
  public void tearDown() {
    out.println("Tearing down " + list.getId());
  }
  // All tests are marked with the @Test annotation:
  @Test
  public void insert() {
    out.println("Running testInsert()");
    assertEquals(list.size(), 3);
    list.add(1, "Insert");
    assertEquals(list.size(), 4);
    assertEquals(list.get(1), "Insert");
  }
  @Test
  public void replace() {
    out.println("Running testReplace()");
    assertEquals(list.size(), 3);
    list.set(1, "Replace");
    assertEquals(list.size(), 3);
    assertEquals(list.get(1), "Replace");
  }
  // A helper method to reduce code duplication. As long
  // as it isn't annotated with @Test, it will not
  // be automatically executed by JUnit.
  private
  void compare(ArrayList<String> lst, String[] strs) {
    String[] array = (String[])lst.toArray();
    assertTrue("Arrays not the same length",
      array.length == strs.length);
    for(int i = 0; i < array.length; i++)
      assertEquals(strs[i], array[i]);
  }
  @Test
  public void order() {
    out.println("Running testOrder()");
    compare(list, new String[] { "0", "1", "2" });
  }
  @Test
  public void remove() {
    out.println("Running testRemove()");
    assertEquals(list.size(), 3);
    list.remove(1);
    assertEquals(list.size(), 2);
    compare(list, new String[] { "0", "2" });
  }
  @Test
  public void addAll() {
    out.println("Running testAddAll()");
    list.addAll(Arrays.asList(new String[] {
      "An", "African", "Swallow"}));
    assertEquals(list.size(), 6);
    compare(list, new String[] { "0", "1", "2",
       "An", "African", "Swallow" });
  }
  public static void main(String[] args) {
    // Invoke JUnit on the class:
    org.junit.runner.JUnitCore.runClasses(
      JUnitDemo.class);
  }
} ///:~
