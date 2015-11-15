// generics/CountedObject.java
// ©2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.

public class CountedObject {
  private static long counter = 0;
  private final long id = counter++;
  public long id() { return id; }
  @Override
  public String toString() { return "CountedObject " + id;}
}
