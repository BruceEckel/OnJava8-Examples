// ui/HTMLButton.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// Putting HTML text on Swing components
import javax.swing.*;
import java.awt.*;
import static onjava.SwingConsole.*;

public class HTMLButton extends JFrame {
  private JButton b = new JButton(
    "<html><b><font size=+2>" +
    "<center>Hello!<br><i>Press me now!");
  public HTMLButton() {
    b.addActionListener(e -> {
      add(new JLabel("<html>" +
              "<i><font size=+4>Kapow!"));
      // Force a re-layout to include the new label:
      validate();
    });
    setLayout(new FlowLayout());
    add(b);
  }
  public static void main(String[] args) {
    run(new HTMLButton(), 200, 500);
  }
}
