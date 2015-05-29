//: net/mindview/util/SwingConsole.java
// ©2015 MindView LLC: see Copyright.txt
// Tool for running Swing demos from the
// console, both applets and JFrames.
package net.mindview.util;
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
} ///:~
