//: ui/SubmitSwingProgram.java
// ©2015 MindView LLC: see Copyright.txt
import javax.swing.*;
import java.util.concurrent.*;

public class SubmitSwingProgram extends JFrame {
  JLabel label;
  public SubmitSwingProgram() {
    super("Hello Swing");
    label = new JLabel("A Label");
    add(label);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(300, 100);
    setVisible(true);
  }
  static SubmitSwingProgram ssp;
  public static void main(String[] args) throws Exception {
    SwingUtilities.invokeLater(() ->
      ssp = new SubmitSwingProgram());
    TimeUnit.SECONDS.sleep(1);
    SwingUtilities.invokeLater(() ->
      ssp.label.setText("Hey! This is Different!"));
  }
} ///:~
