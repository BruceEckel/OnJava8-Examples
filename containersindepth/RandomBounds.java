// containersindepth/RandomBounds.java
// Does Math.random() produce 0.0 and 1.0?
// {TimeOutDuringTesting}
// {Args: lower}

public class RandomBounds {
  static void usage() {
    System.out.println("Usage:");
    System.out.println("\tRandomBounds lower");
    System.out.println("\tRandomBounds upper");
    System.exit(1);
  }
  public static void main(String[] args) {
    if(args.length != 1) usage();
    switch(args[0]) {
      case "lower":
        while(Math.random() != 0.0)
          ; // Keep trying
        System.out.println("Produced 0.0!");
        break;
      case "upper":
        while(Math.random() != 1.0)
          ; // Keep trying
        System.out.println("Produced 1.0!");
        break;
      default:
        usage();
    }
  }
}
/* Output: (None) */
