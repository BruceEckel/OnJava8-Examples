// verifying/QueueTest.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
package verifying;
import verifying.Queue;
import org.junit.Test;
import static org.junit.Assert.*;

public class QueueTest {
  private Queue queue = new Queue(10);
  private int i = 0;
  @Before
  public void initialize() {
    while(i < 5) // Preload with some data
      queue.put(Integer.toString(i++));
  }
  // Support methods:
  private void showFullness() {
    assertTrue(queue.full());
    assertFalse(queue.empty());
    System.out.println(queue.dump());
  }
  private void showEmptiness() {
    assertFalse(queue.full());
    assertTrue(queue.empty());
    System.out.println(queue.dump());
  }
  @Test
  public void full() {
    System.out.println("testFull");
    System.out.println(queue.dump());
    System.out.println(queue.get());
    System.out.println(queue.get());
    while(!queue.full())
      queue.put(Integer.toString(i++));
    String msg = "";
    try {
      queue.put("");
    } catch(QueueException e) {
      msg = e.getMessage();
      System.out.println(msg);
    }
    assertEquals(msg, "put() into full Queue");
    showFullness();
  }
  @Test
  public void empty() {
    System.out.println("testEmpty");
    while(!queue.empty())
      System.out.println(queue.get());
    String msg = "";
    try {
      queue.get();
    } catch(QueueException e) {
      msg = e.getMessage();
      System.out.println(msg);
    }
    assertEquals(msg, "get() from empty Queue");
    showEmptiness();
  }
  @Test
  public void nullPut() {
    System.out.println("testNullPut");
    String msg = "";
    try {
      queue.put(null);
    } catch(QueueException e) {
      msg = e.getMessage();
      System.out.println(msg);
    }
    assertEquals(msg, "put() null item");
  }
  @Test
  public void circularity() {
    System.out.println("testCircularity");
    while(!queue.full())
      queue.put(Integer.toString(i++));
    showFullness();
    assertTrue(queue.isWrapped());
    while(!queue.empty())
      System.out.println(queue.get());
    showEmptiness();
    while(!queue.full())
      queue.put(Integer.toString(i++));
    showFullness();
    while(!queue.empty())
      System.out.println(queue.get());
    showEmptiness();
  }
}
/* Output:
testNullPut
put() null item
testCircularity
in = 0, out = 0, full() = true, empty() = false, queue =
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
0
1
2
3
4
5
6
7
8
9
in = 0, out = 0, full() = false, empty() = true, queue =
[null, null, null, null, null, null, null, null, null,
null]
in = 0, out = 0, full() = true, empty() = false, queue =
[10, 11, 12, 13, 14, 15, 16, 17, 18, 19]
10
11
12
13
14
15
16
17
18
19
in = 0, out = 0, full() = false, empty() = true, queue =
[null, null, null, null, null, null, null, null, null,
null]
testFull
in = 5, out = 0, full() = false, empty() = false, queue =
[0, 1, 2, 3, 4, null, null, null, null, null]
0
1
put() into full Queue
in = 2, out = 2, full() = true, empty() = false, queue =
[10, 11, 2, 3, 4, 5, 6, 7, 8, 9]
testEmpty
0
1
2
3
4
get() from empty Queue
in = 5, out = 5, full() = false, empty() = true, queue =
[null, null, null, null, null, null, null, null, null,
null]
*/
