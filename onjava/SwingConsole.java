// onjava/SwingConsole.java
// Tool for running Swing demos from the
// console, both applets and JFrames.
package onjava;
import javax.swing.*;

public class SwingConsole {
  public static void
  run(final JFrame f, final int width, final int height) {
    SwingUtilities.invokeLater(() -> {
      f.setTitle(f.getClass().getSimpleName());
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f.setSize(width, height);
      f.setVisible(true);
    });
  }
}
