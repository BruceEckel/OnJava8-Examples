// moreaboutcollections/QueueBehavior.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Compares the behavior of some of the queues
import java.util.concurrent.*;
import java.util.*;

public class QueueBehavior {
  private static String[] s =
    ("one two three four five six seven " +
    "eight nine ten").split(" ");
  static void test(Queue<String> queue) {
    for(String ss : s)
      queue.offer(ss);
    while(queue.peek() != null)
      System.out.print(queue.remove() + " ");
    System.out.println();
  }
  public static void main(String[] args) {
   int count = 10;
   test(new LinkedList<>());
    test(new PriorityQueue<>());
    test(new ArrayBlockingQueue<>(count));
    test(new ConcurrentLinkedQueue<>());
    test(new LinkedBlockingQueue<>());
    test(new PriorityBlockingQueue<>());
  }
}
/* Output:
one two three four five six seven eight nine ten
eight five four nine one seven six ten three two
one two three four five six seven eight nine ten
one two three four five six seven eight nine ten
one two three four five six seven eight nine ten
eight five four nine one seven six ten three two
*/
