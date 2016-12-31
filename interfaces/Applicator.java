// interfaces/Applicator.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
import java.util.*;

class Processor {
  public String name() {
    return getClass().getSimpleName();
  }
  public Object process(Object input) {
    return input;
  }
}

class Upcase extends Processor {
  @Override // Covariant return:
  public String process(Object input) {
    return ((String)input).toUpperCase();
  }
}

class Downcase extends Processor {
  @Override
  public String process(Object input) {
    return ((String)input).toLowerCase();
  }
}

class Splitter extends Processor {
  @Override
  public String process(Object input) {
    // The split() argument divides a String into pieces:
    return Arrays.toString(((String)input).split(" "));
  }
}

public class Applicator {
  public static void apply(Processor p, Object s) {
    System.out.println("Using Processor " + p.name());
    System.out.println(p.process(s));
  }
  public static final String s =
  "Disagreement with beliefs is by definition incorrect";
  public static void main(String[] args) {
    apply(new Upcase(), s);
    apply(new Downcase(), s);
    apply(new Splitter(), s);
  }
}
/* Output:
Using Processor Upcase
DISAGREEMENT WITH BELIEFS IS BY DEFINITION INCORRECT
Using Processor Downcase
disagreement with beliefs is by definition incorrect
Using Processor Splitter
[Disagreement, with, beliefs, is, by, definition,
incorrect]
*/
