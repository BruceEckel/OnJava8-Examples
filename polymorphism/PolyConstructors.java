// polymorphism/PolyConstructors.java
// Constructors and polymorphism
// don't produce what you might expect.

class Glyph {
  void draw() { System.out.println("Glyph.draw()"); }
  Glyph() {
    System.out.println("Glyph() before draw()");
    draw();
    System.out.println("Glyph() after draw()");
  }
}

class RoundGlyph extends Glyph {
  private int radius = 1;
  RoundGlyph(int r) {
    radius = r;
    System.out.println("RoundGlyph.RoundGlyph(), radius = " + radius);
  }
  @Override
  void draw() {
    System.out.println("RoundGlyph.draw(), radius = " + radius);
  }
}

public class PolyConstructors {
  public static void main(String[] args) {
    new RoundGlyph(5);
  }
}
/* Output:
Glyph() before draw()
RoundGlyph.draw(), radius = 0
Glyph() after draw()
RoundGlyph.RoundGlyph(), radius = 5
*/
