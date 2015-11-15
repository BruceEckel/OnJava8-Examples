// streams/Bubble.java
// ©2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
import java.util.function.*;

public class Bubble {
  public final int i;
  public Bubble(int n) { i = n; }
  @Override
  public String toString() {
    return "Bubble(" + i + ")";
  }
  private static
  class BubbleFactory implements Supplier<Bubble> {
    private static int count = 0;
    @Override
    public Bubble get() { return new Bubble(count++); }
  }
  public static final
  BubbleFactory factory = new BubbleFactory();
}
