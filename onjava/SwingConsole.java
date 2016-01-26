// onjava/SwingConsole.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// Tool for running Swing demos from the
// console, both applets and JFrames
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
