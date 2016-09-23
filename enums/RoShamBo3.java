// enums/RoShamBo3.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Using constant-specific methods
// {java enums.RoShamBo3}
package enums;
import static enums.Outcome.*;

public enum RoShamBo3 implements Competitor<RoShamBo3> {
  PAPER {
    @Override
    public Outcome compete(RoShamBo3 it) {
      switch(it) {
        default: // To placate the compiler
        case PAPER: return DRAW;
        case SCISSORS: return LOSE;
        case ROCK: return WIN;
      }
    }
  },
  SCISSORS {
    @Override
    public Outcome compete(RoShamBo3 it) {
      switch(it) {
        default:
        case PAPER: return WIN;
        case SCISSORS: return DRAW;
        case ROCK: return LOSE;
      }
    }
  },
  ROCK {
    @Override
    public Outcome compete(RoShamBo3 it) {
      switch(it) {
        default:
        case PAPER: return LOSE;
        case SCISSORS: return WIN;
        case ROCK: return DRAW;
      }
    }
  };
  @Override
  public abstract Outcome compete(RoShamBo3 it);
  public static void main(String[] args) {
    RoShamBo.play(RoShamBo3.class, 20);
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
