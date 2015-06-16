//: exceptions/Switch.java
// ©2015 MindView LLC: see Copyright.txt
import static com.mindviewinc.util.Print.*;

public class Switch {
  private boolean state = false;
  public boolean read() { return state; }
  public void on() { state = true; print(this); }
  public void off() { state = false; print(this); }
  @Override
  public String toString() { return state ? "on" : "off"; }
} ///:~
