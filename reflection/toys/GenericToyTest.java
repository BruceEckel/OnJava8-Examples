// reflection/toys/GenericToyTest.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Testing class Class
// {java reflection.toys.GenericToyTest}
package reflection.toys;

public class GenericToyTest {
  public static void
  main(String[] args) throws Exception {
    Class<FancyToy> ftc = FancyToy.class;
    // Produces exact type:
    FancyToy fancyToy =
      ftc.getConstructor().newInstance();
    Class<? super FancyToy> up = ftc.getSuperclass();
    // This won't compile:
    // Class<Toy> up2 = ftc.getSuperclass();
    // Only produces Object:
    Object obj = up.getConstructor().newInstance();
  }
}
