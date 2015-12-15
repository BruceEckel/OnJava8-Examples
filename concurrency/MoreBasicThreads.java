// concurrency/MoreBasicThreads.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// Adding more threads.

public class MoreBasicThreads {
  public static void main(String[] args) {
    for(int i = 0; i < 5; i++)
      new Thread(new LiftOff()).start();
    System.out.println("Waiting for LiftOff");
  }
}
/* Output:
Waiting for LiftOff
#3(9), #2(9), #3(8), #2(8), #3(7), #2(7), #3(6), #2(6),
#3(5), #2(5), #3(4), #2(4), #3(3), #2(3), #3(2), #2(2),
#3(1), #2(1), #3(Liftoff!), #2(Liftoff!), #1(9), #0(9),
#4(9), #1(8), #4(8), #0(8), #1(7), #0(7), #4(7), #1(6),
#4(6), #0(6), #1(5), #0(5), #4(5), #1(4), #4(4), #0(4),
#1(3), #0(3), #4(3), #1(2), #4(2), #0(2), #1(1), #0(1),
#4(1), #0(Liftoff!), #4(Liftoff!), #1(Liftoff!),
*/
