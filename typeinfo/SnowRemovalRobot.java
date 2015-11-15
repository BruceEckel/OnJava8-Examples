// typeinfo/SnowRemovalRobot.java
// ©2016 MindView LLC: see Copyright.txt
import java.util.*;

public class SnowRemovalRobot implements Robot {
  private String name;
  public SnowRemovalRobot(String name) {this.name = name;}
  @Override
  public String name() { return name; }
  @Override
  public String model() { return "SnowBot Series 11"; }
  public List<Operation> operations() {
    return Arrays.asList(
      new Operation() {
        @Override
        public String description() {
          return name + " can shovel snow";
        }
        @Override
        public void command() {
          System.out.println(name + " shoveling snow");
        }
      },
      new Operation() {
        @Override
        public String description() {
          return name + " can chip ice";
        }
        @Override
        public void command() {
          System.out.println(name + " chipping ice");
        }
      },
      new Operation() {
        @Override
        public String description() {
          return name + " can clear the roof";
        }
        @Override
        public void command() {
          System.out.println(name + " clearing roof");
        }
      }
    );
  }
  public static void main(String[] args) {
    Robot.Test.test(new SnowRemovalRobot("Slusher"));
  }
}
/* Output:
Robot name: Slusher
Robot model: SnowBot Series 11
Slusher can shovel snow
Slusher shoveling snow
Slusher can chip ice
Slusher chipping ice
Slusher can clear the roof
Slusher clearing roof
*/
