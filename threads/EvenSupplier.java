// threads/EvenSupplier.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// When threads collide
import onjava.TimedAbort;

public class EvenSupplier extends IntSupplier {
  private int currentEvenValue = 0;
  @Override
  public int next() {
    ++currentEvenValue; // Danger point here!
    ++currentEvenValue;
    return currentEvenValue;
  }
  public static void main(String[] args) {
    new TimedAbort(4);
    EvenChecker.test(new EvenSupplier());
  }
}
/* Output:
Press Control-C to exit
1305 not even!
1307 not even!
1303 not even!
*/
