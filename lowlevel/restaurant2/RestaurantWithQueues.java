// lowlevel/restaurant2/RestaurantWithQueues.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// {java threads.restaurant2.RestaurantWithQueues 5}
package threads.restaurant2;
import enums.menu.*;
import java.util.concurrent.*;
import java.util.*;

// This is given to the waiter, who gives it to the chef:
class Order { // (A data-transfer object)
  private static int counter = 0;
  private final int id = counter++;
  private final Customer customer;
  private final WaitPerson waitPerson;
  private final Food food;
  public Order(Customer cust, WaitPerson wp, Food f) {
    customer = cust;
    waitPerson = wp;
    food = f;
  }
  public Food item() { return food; }
  public Customer getCustomer() { return customer; }
  public WaitPerson getWaitPerson() { return waitPerson; }
  @Override
  public String toString() {
    return "Order: " + id + " item: " + food +
      " for: " + customer +
      " served by: " + waitPerson;
  }
}

// This comes back from the chef:
class Plate {
  private final Order order;
  private final Food food;
  public Plate(Order ord, Food f) {
    order = ord;
    food = f;
  }
  public Order getOrder() { return order; }
  public Food getFood() { return food; }
  @Override
  public String toString() { return food.toString(); }
}

class Customer implements Runnable {
  private static int counter = 0;
  private final int id = counter++;
  private final WaitPerson waitPerson;
  // Only one course at a time can be received:
  private SynchronousQueue<Plate> placeSetting =
    new SynchronousQueue<>();
  public Customer(WaitPerson w) { waitPerson = w; }
  public void
  deliver(Plate p) throws InterruptedException {
    // Only blocks if customer is still
    // eating the previous course:
    placeSetting.put(p);
  }
  @Override
  public void run() {
    for(Course course : Course.values()) {
      Food food = course.randomSelection();
      try {
        waitPerson.placeOrder(this, food);
        // Blocks until course is delivered:
        System.out.println(
          this + "eating " + placeSetting.take());
      } catch(InterruptedException e) {
        System.out.println(this + "waiting for " +
          course + " interrupted");
        break;
      }
    }
    System.out.println(this + "finished meal, leaving");
  }
  @Override
  public String toString() {
    return "Customer " + id + " ";
  }
}

class WaitPerson implements Runnable {
  private static int counter = 0;
  private final int id = counter++;
  private final Restaurant restaurant;
  BlockingQueue<Plate> filledOrders =
    new LinkedBlockingQueue<>();
  public WaitPerson(Restaurant rest) {
    restaurant = rest;
  }
  public void placeOrder(Customer cust, Food food) {
    try {
      // Shouldn't actually block because this is
      // a LinkedBlockingQueue with no size limit:
      restaurant.orders.put(new Order(cust, this, food));
    } catch(InterruptedException e) {
      System.out.println(
        this + " placeOrder interrupted");
    }
  }
  @Override
  public void run() {
    try {
      while(!Thread.interrupted()) {
        // Blocks until a course is ready
        Plate plate = filledOrders.take();
        System.out.println(this + "received " + plate +
          " delivering to " +
          plate.getOrder().getCustomer());
        plate.getOrder().getCustomer().deliver(plate);
      }
    } catch(InterruptedException e) {
      System.out.println(this + " interrupted");
    }
    System.out.println(this + " off duty");
  }
  @Override
  public String toString() {
    return "WaitPerson " + id + " ";
  }
}

class Chef implements Runnable {
  private static int counter = 0;
  private final int id = counter++;
  private final Restaurant restaurant;
  private static SplittableRandom rand = new SplittableRandom(47);
  public Chef(Restaurant rest) { restaurant = rest; }
  @Override
  public void run() {
    try {
      while(!Thread.interrupted()) {
        // Blocks until an order appears:
        Order order = restaurant.orders.take();
        Food requestedItem = order.item();
        // Time to prepare order:
        TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
        Plate plate = new Plate(order, requestedItem);
        order.getWaitPerson().filledOrders.put(plate);
      }
    } catch(InterruptedException e) {
      System.out.println(this + " interrupted");
    }
    System.out.println(this + " off duty");
  }
  @Override
  public String toString() { return "Chef " + id + " "; }
}

class Restaurant implements Runnable {
  private List<WaitPerson> waitPersons =
    new ArrayList<>();
  private List<Chef> chefs = new ArrayList<>();
  private ExecutorService exec;
  private static SplittableRandom rand = new SplittableRandom(47);
  BlockingQueue<Order>
    orders = new LinkedBlockingQueue<>();
  public Restaurant(ExecutorService e, int nWaitPersons,
    int nChefs) {
    exec = e;
    for(int i = 0; i < nWaitPersons; i++) {
      WaitPerson waitPerson = new WaitPerson(this);
      waitPersons.add(waitPerson);
      exec.execute(waitPerson);
    }
    for(int i = 0; i < nChefs; i++) {
      Chef chef = new Chef(this);
      chefs.add(chef);
      exec.execute(chef);
    }
  }
  @Override
  public void run() {
    try {
      while(!Thread.interrupted()) {
        // A new customer arrives; assign a WaitPerson:
        WaitPerson wp = waitPersons.get(
          rand.nextInt(waitPersons.size()));
        Customer c = new Customer(wp);
        exec.execute(c);
        TimeUnit.MILLISECONDS.sleep(100);
      }
    } catch(InterruptedException e) {
      System.out.println("Restaurant interrupted");
    }
    System.out.println("Restaurant closing");
  }
}

public class RestaurantWithQueues {
  public static void
  main(String[] args) throws Exception {
    ExecutorService es = Executors.newCachedThreadPool();
    Restaurant restaurant = new Restaurant(es, 5, 2);
    es.execute(restaurant);
    if(args.length > 0) // Optional argument
      TimeUnit.SECONDS.sleep(new Integer(args[0]));
    else {
      System.out.println("Press 'Enter' to quit");
      System.in.read();
    }
    es.shutdownNow();
  }
}
/* Output: (First and Last 10 Lines)
WaitPerson 0 received SALAD delivering to Customer 0
Customer 0 eating SALAD
WaitPerson 2 received SPRING_ROLLS delivering to Customer 1
Customer 1 eating SPRING_ROLLS
WaitPerson 1 received SOUP delivering to Customer 2
Customer 2 eating SOUP
WaitPerson 0 received LENTILS delivering to Customer 0
Customer 0 eating LENTILS
WaitPerson 0 received SPRING_ROLLS delivering to Customer 3
Customer 3 eating SPRING_ROLLS
...________...________...________...________...
WaitPerson 2  off duty
Customer 33 finished meal, leaving
Customer 18 finished meal, leaving
Customer 35 finished meal, leaving
Customer 46 finished meal, leaving
WaitPerson 4  off duty
Customer 24 finished meal, leaving
Customer 30 finished meal, leaving
Customer 34 finished meal, leaving
Customer 48 finished meal, leaving
*/
