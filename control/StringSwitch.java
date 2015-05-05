//: control/StringSwitch.java
import static net.mindview.util.Print.*;

public class StringSwitch {
  public static void main(String[] args) {
    String color = "red";
    // Old way: using if-then
    if(color == "red") {
      print("RED");
    } else if(color == "green") {
      print("GREEN");
    } else if(color == "blue") {
      print("BLUE");
    } else if(color == "yellow") {
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
