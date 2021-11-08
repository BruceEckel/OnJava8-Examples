// enumerations/EnumSwitch.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// {NewFeature} Since JDK 14

public class EnumSwitch {
  enum Signal { GREEN, YELLOW, RED, }
  Signal color = Signal.RED;
  public void change() {
    color = switch(color) {
      case RED -> Signal.GREEN;
      case GREEN -> Signal.YELLOW;
      case YELLOW -> Signal.RED;
    };
  }
}
