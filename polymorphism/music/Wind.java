// polymorphism/music/Wind.java
// ©2016 MindView LLC: see Copyright.txt
package polymorphism.music;

// Wind objects are instruments
// because they have the same interface:
public class Wind extends Instrument {
  // Redefine interface method:
  @Override
  public void play(Note n) {
    System.out.println("Wind.play() " + n);
  }
}
