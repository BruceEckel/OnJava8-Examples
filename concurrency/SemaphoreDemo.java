// concurrency/SemaphoreDemo.java
// ©2015 MindView LLC: see Copyright.txt
// Testing the Pool class
import java.util.concurrent.*;
import java.util.*;
import static com.mindviewinc.util.Print.*;

// A task to check a resource out of a pool:
class CheckoutTask<T> implements Runnable {
  private static int counter = 0;
  private final int id = counter++;
  private Pool<T> pool;
  public CheckoutTask(Pool<T> pool) {
    this.pool = pool;
  }
  @Override
  public void run() {
    try {
      T item = pool.checkOut();
      print(this + "checked out " + item);
      TimeUnit.SECONDS.sleep(1);
      print(this +"checking in " + item);
      pool.checkIn(item);
    } catch(InterruptedException e) {
      // Acceptable way to terminate
    }
  }
  @Override
  public String toString() {
    return "CheckoutTask " + id + " ";
  }
}

public class SemaphoreDemo {
  final static int SIZE = 25;
  public static void
  main(String[] args) throws Exception {
    final Pool<Fat> pool = new Pool<>(Fat.class, SIZE);
    ExecutorService exec = Executors.newCachedThreadPool();
    for(int i = 0; i < SIZE; i++)
      exec.execute(new CheckoutTask<>(pool));
    print("All CheckoutTasks created");
    List<Fat> list = new ArrayList<>();
    for(int i = 0; i < SIZE; i++) {
      Fat f = pool.checkOut();
      printnb(i + ": main() thread checked out ");
      f.operation();
      list.add(f);
    }
    Future<?> blocked = exec.submit(() -> {
      try {
        // Semaphore prevents additional checkout,
        // so call is blocked:
        pool.checkOut();
      } catch(InterruptedException e) {
        print("checkOut() Interrupted");
      }
    });
    TimeUnit.SECONDS.sleep(2);
    blocked.cancel(true); // Break out of blocked call
    print("Checking in objects in " + list);
    for(Fat f : list)
      pool.checkIn(f);
    for(Fat f : list)
      pool.checkIn(f); // Second checkIn ignored
    exec.shutdown();
  }
}
/* Output: (First and last 10 Lines)
CheckoutTask 4 checked out Fat id: 2
CheckoutTask 22 checked out Fat id: 24
CheckoutTask 14 checked out Fat id: 23
CheckoutTask 21 checked out Fat id: 22
CheckoutTask 18 checked out Fat id: 21
CheckoutTask 17 checked out Fat id: 20
CheckoutTask 10 checked out Fat id: 19
CheckoutTask 13 checked out Fat id: 18
CheckoutTask 9 checked out Fat id: 17
CheckoutTask 6 checked out Fat id: 16
________...________...________...________...________
20: main() thread checked out Fat id: 5
21: main() thread checked out Fat id: 1
CheckoutTask 7 checking in Fat id: 3
CheckoutTask 0 checking in Fat id: 0
CheckoutTask 8 checking in Fat id: 4
22: main() thread checked out Fat id: 3
23: main() thread checked out Fat id: 0
24: main() thread checked out Fat id: 4
checkOut() Interrupted
Checking in objects in [Fat id: 2, Fat id: 23, Fat id: 24,
Fat id: 22, Fat id: 21, Fat id: 20, Fat id: 19, Fat id: 18,
Fat id: 17, Fat id: 16, Fat id: 15, Fat id: 14, Fat id: 13,
Fat id: 11, Fat id: 12, Fat id: 9, Fat id: 10, Fat id: 8,
Fat id: 7, Fat id: 6, Fat id: 5, Fat id: 1, Fat id: 3, Fat
id: 0, Fat id: 4]
*/
