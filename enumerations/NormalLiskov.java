// enumerations/NormalLiskov.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
import java.util.stream.*;

interface LifeForm {
  String move();
  String react();
}

class Worm implements LifeForm {
  @Override public String move() {
    return "Worm::move()";
  }
  @Override public String react() {
    return "Worm::react()";
  }
}

class Giraffe implements LifeForm {
  @Override public String move() {
    return "Giraffe::move()";
  }
  @Override public String react() {
    return "Giraffe::react()";
  }
}

public class NormalLiskov {
  public static void main(String[] args) {
    Stream.of(new Worm(), new Giraffe())
      .forEach(lf -> System.out.println(
        lf.move() + " " + lf.react()));
  }
}
/* Output:
Worm::move() Worm::react()
Giraffe::move() Giraffe::react()
*/
