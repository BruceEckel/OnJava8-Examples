// functions/LambdaExpressions.java

interface Description {
  String brief();
}

interface Body {
  String detailed(String head);
}

interface Multi {
  String twoArg(String head, Double d);
}

public class LambdaExpressions {
  Description desc = new Description() {
    @Override
    public String brief() {
      return "Short info";
    }
  };
  Description desc2 = () -> "Short info";

  Body bod = (h) -> h + " More details";
  Body bod2 = h -> h + " No Parens!";

  Multi mult = (h, n) -> h + n;
  // Parens are required with multiple args:
  // Multi mult2 = h, n -> h + n; // Nope

  public static void main(String[] args) {
    LambdaExpressions le =
      new LambdaExpressions();
    System.out.println(le.desc.brief());
    System.out.println(le.desc2.brief());
    System.out.println(le.bod.detailed("Hi!"));
    System.out.println(le.bod2.detailed("Oh!"));
    System.out.println(le.mult.twoArg("Pi! ", 3.14159));
  }
}
/* Output:
Short info
Short info
Hi! More details
Oh! No Parens!
Pi! 3.14159
*/
