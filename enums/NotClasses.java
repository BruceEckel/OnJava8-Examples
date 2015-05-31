//: enums/NotClasses.java
// ©2015 MindView LLC: see Copyright.txt
// {Exec: javap -c LikeClasses}
import static net.mindview.util.Print.*;

enum LikeClasses {
  WINKEN {@Override
 void behavior() { print("Behavior1"); } },
  BLINKEN {@Override
 void behavior() { print("Behavior2"); } },
  NOD {@Override
 void behavior() { print("Behavior3"); } };
  abstract void behavior();
}

public class NotClasses {
  // void f1(LikeClasses.WINKEN instance) {} // Nope
} /* Output: (First 7 Lines)
Compiled from "NotClasses.java"
abstract class LikeClasses extends java.lang.Enum{
public static final LikeClasses WINKEN;

public static final LikeClasses BLINKEN;

public static final LikeClasses NOD;
...
*///:~
