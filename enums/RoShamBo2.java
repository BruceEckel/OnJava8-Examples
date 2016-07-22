// enums/RoShamBo2.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// Switching one enum on another
// {main: enums.RoShamBo2}
package enums;
import static enums.Outcome.*;

public enum RoShamBo2 implements Competitor<RoShamBo2> {
  PAPER(DRAW, LOSE, WIN),
  SCISSORS(WIN, DRAW, LOSE),
  ROCK(LOSE, WIN, DRAW);
  private Outcome vPAPER, vSCISSORS, vROCK;
  RoShamBo2(Outcome paper,Outcome scissors,Outcome rock) {
    this.vPAPER = paper;
    this.vSCISSORS = scissors;
    this.vROCK = rock;
  }
  @Override
  public Outcome compete(RoShamBo2 it) {
    switch(it) {
      default:
      case PAPER: return vPAPER;
      case SCISSORS: return vSCISSORS;
      case ROCK: return vROCK;
    }
  }
  public static void main(String[] args) {
    RoShamBo.play(RoShamBo2.class, 20);
  }
}
/* Output:
PAPER vs. ROCK: WIN
PAPER vs. SCISSORS: LOSE
ROCK vs. SCISSORS: WIN
ROCK vs. ROCK: DRAW
ROCK vs. SCISSORS: WIN
SCISSORS vs. PAPER: WIN
ROCK vs. SCISSORS: WIN
PAPER vs. PAPER: DRAW
PAPER vs. ROCK: WIN
PAPER vs. SCISSORS: LOSE
PAPER vs. ROCK: WIN
SCISSORS vs. PAPER: WIN
ROCK vs. ROCK: DRAW
PAPER vs. PAPER: DRAW
SCISSORS vs. SCISSORS: DRAW
PAPER vs. SCISSORS: LOSE
PAPER vs. ROCK: WIN
PAPER vs. SCISSORS: LOSE
ROCK vs. SCISSORS: WIN
ROCK vs. PAPER: LOSE
*/
