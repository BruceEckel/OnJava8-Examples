// enumerations/Planets.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// {NewFeature} Since JDK 14

enum CelestialBody {
  MERCURY, VENUS, EARTH, MARS, JUPITER,
  SATURN, URANUS, NEPTUNE, PLUTO
}

public class Planets {
  public static String classify(CelestialBody b) {
    var result = switch(b) {
      case  MERCURY, VENUS, EARTH,
            MARS, JUPITER,
            SATURN, URANUS, NEPTUNE -> {
              System.out.print("A planet: ");
              yield b.toString();
            }
      case  PLUTO -> {
              System.out.print("Not a planet: ");
              yield b.toString();
            }
    };
    return result;
  }
  public static void main(String[] args) {
    System.out.println(classify(CelestialBody.MARS));
    System.out.println(classify(CelestialBody.PLUTO));
  }
}
/* Output:
A planet: MARS
Not a planet: PLUTO
*/
