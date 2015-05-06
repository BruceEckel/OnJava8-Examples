//: control/StringSwitch.java
import static net.mindview.util.Print.*;

public class StringSwitch {
  public static void main(String[] args) {
    String color = "red";
    // Old way: using if-then
    if("red".equals(color)) {
      print("RED");
    } else if("green".equals(color)) {
      print("GREEN");
    } else if("blue".equals(color)) {
      print("BLUE");
    } else if("yellow".equals(color)) {
      print("YELLOW");
    } else {
      print("Unknown");
    }
    // New way: Strings in switch
    switch(color) {
      case "red":
        print("RED");
        break;
      case "green":
        print("GREEN");
        break;
      case "blue":
        print("BLUE");
        break;
      case "yellow":
        print("YELLOW");
        break;
      default:
        print("Unknown");
        break;
    }
  }
} ///:~
