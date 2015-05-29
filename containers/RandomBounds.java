//: containers/RandomBounds.java
// ©2015 MindView LLC: see Copyright.txt
// Does Math.random() produce 0.0 and 1.0?
// {TimeOutDuringTesting}
// {Args: lower}
import static net.mindview.util.Print.*;

public class RandomBounds {
  static void usage() {
    print("Usage:");
    print("\tRandomBounds lower");
    print("\tRandomBounds upper");
    System.exit(1);
  }
  public static void main(String[] args) {
    if(args.length != 1) usage();
    switch(args[0]) {
      case "lower":
        while(Math.random() != 0.0)
          ; // Keep trying
        print("Produced 0.0!");
        break;
      case "upper":
        while(Math.random() != 1.0)
          ; // Keep trying
        print("Produced 1.0!");
        break;
      default:
        usage();
    }
  }
} ///:~
