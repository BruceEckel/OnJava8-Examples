// enums/RandomTest.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
import onjava.*;

enum Activity { SITTING, LYING, STANDING, HOPPING,
  RUNNING, DODGING, JUMPING, FALLING, FLYING }

public class RandomTest {
  public static void main(String[] args) {
    for(int i = 0; i < 20; i++)
      System.out.print(
        Enums.random(Activity.class) + " ");
  }
}
/* Output:
JUMPING DODGING SITTING RUNNING FLYING RUNNING DODGING
STANDING DODGING FALLING FALLING SITTING DODGING LYING
JUMPING JUMPING HOPPING DODGING SITTING FALLING
*/
