// ui/Button2b.java
// Using anonymous inner classes.
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static onjava.SwingConsole.*;

public class Button2b extends JFrame {
  private JButton
    b1 = new JButton("Button 1"),
    b2 = new JButton("Button 2");
  private JTextField txt = new JTextField(10);
  private ActionListener bl = e -> {
    String name1 = ((JButton)e.getSource()).getText();
    txt.setText(name1);
  };
  public Button2b() {
    b1.addActionListener(bl);
    b2.addActionListener(bl);
    setLayout(new FlowLayout());
    add(b1);
    add(b2);
    add(txt);
  }
  public static void main(String[] args) {
    run(new Button2b(), 200, 150);
  }
}
