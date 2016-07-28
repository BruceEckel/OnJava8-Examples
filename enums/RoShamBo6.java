// enums/RoShamBo6.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// Enums using "tables" instead of multiple dispatch
// {java enums.RoShamBo6}
package enums;
import static enums.Outcome.*;

enum RoShamBo6 implements Competitor<RoShamBo6> {
  PAPER, SCISSORS, ROCK;
  private static Outcome[][] table = {
    { DRAW, LOSE, WIN }, // PAPER
    { WIN, DRAW, LOSE }, // SCISSORS
    { LOSE, WIN, DRAW }, // ROCK
  };
  @Override
  public Outcome compete(RoShamBo6 other) {
    return table[this.ordinal()][other.ordinal()];
  }
  public static void main(String[] args) {
    RoShamBo.play(RoShamBo6.class, 20);
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
