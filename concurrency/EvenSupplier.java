// concurrency/EvenSupplier.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// When threads collide.

public class EvenSupplier extends IntSupplier {
  private int currentEvenValue = 0;
  @Override
  public int next() {
    ++currentEvenValue; // Danger point here!
    ++currentEvenValue;
    return currentEvenValue;
  }
  public static void main(String[] args) {
    EvenChecker.test(new EvenSupplier());
  }
}
/* Output:
Press Control-C to exit
1649 not even!
1657 not even!
1655 not even!
1653 not even!
1651 not even!
*/
