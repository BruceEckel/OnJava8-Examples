//: containersindepth/QueueBehavior.java
// ©2015 MindView LLC: see Copyright.txt
// Compares the behavior of some of the queues
import java.util.concurrent.*;
import java.util.*;
import com.mindviewinc.util.*;

public class QueueBehavior {
  private static int count = 10;
  static <T> void test(Queue<T> queue, Generator<T> gen) {
    for(int i = 0; i < count; i++)
      queue.offer(gen.next());
    while(queue.peek() != null)
      System.out.print(queue.remove() + " ");
    System.out.println();
  }
  static class Gen implements Generator<String> {
    String[] s = ("one two three four five six seven " +
      "eight nine ten").split(" ");
    int i;
    @Override
    public String next() { return s[i++]; }
  }
  public static void main(String[] args) {
    test(new LinkedList<>(), new Gen());
    test(new PriorityQueue<>(), new Gen());
    test(new ArrayBlockingQueue<>(count), new Gen());
    test(new ConcurrentLinkedQueue<>(), new Gen());
    test(new LinkedBlockingQueue<>(), new Gen());
    test(new PriorityBlockingQueue<>(), new Gen());
  }
} /* Output:
one two three four five six seven eight nine ten
eight five four nine one seven six ten three two
one two three four five six seven eight nine ten
one two three four five six seven eight nine ten
one two three four five six seven eight nine ten
eight five four nine one seven six ten three two
*///:~
