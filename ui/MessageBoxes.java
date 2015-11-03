// ui/MessageBoxes.java
// Demonstrates JOptionPane.
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static com.mindviewinc.util.SwingConsole.*;

public class MessageBoxes extends JFrame {
  private JButton[] b = {
    new JButton("Alert"), new JButton("Yes/No"),
    new JButton("Color"), new JButton("Input"),
    new JButton("3 Vals")
  };
  private JTextField txt = new JTextField(15);
  private ActionListener al = e -> {
    String id = ((JButton)e.getSource()).getText();
    switch (id) {
      case "Alert":
        JOptionPane.showMessageDialog(null,
          "There's a bug on you!", "Hey!",
          JOptionPane.ERROR_MESSAGE);
        break;
      case "Yes/No":
        JOptionPane.showConfirmDialog(null,
          "or no", "choose yes",
          JOptionPane.YES_NO_OPTION);
        break;
      case "Color":
        Object[] options = { "Red", "Green" };
        int sel = JOptionPane.showOptionDialog(
          null, "Choose a Color!", "Warning",
          JOptionPane.DEFAULT_OPTION,
          JOptionPane.WARNING_MESSAGE, null,
          options, options[0]);
        if(sel != JOptionPane.CLOSED_OPTION)
          txt.setText("Color Selected: " + options[sel]);
        break;
      case "Input": {
        String val = JOptionPane.showInputDialog(
          "How many fingers do you see?");
        txt.setText(val);
        break;
      }
      case "3 Vals": {
        Object[] selections = {"First", "Second", "Third"};
        Object val = JOptionPane.showInputDialog(
          null, "Choose one", "Input",
          JOptionPane.INFORMATION_MESSAGE,
          null, selections, selections[0]);
        if(val != null)
          txt.setText(val.toString());
        break;
      }
    }
  };
  public MessageBoxes() {
    setLayout(new FlowLayout());
    for(JButton b1 : b) {
      b1.addActionListener(al);
      add(b1);
    }
    add(txt);
  }
  public static void main(String[] args) {
    run(new MessageBoxes(), 200, 200);
  }
}
