// ui/TextPane.java
// The JTextPane control is a little editor.
import javax.swing.*;
import java.awt.*;
import java.util.function.*;
import com.mindviewinc.util.*;
import static com.mindviewinc.util.SwingConsole.*;

public class TextPane extends JFrame {
  private JButton b = new JButton("Add Text");
  private JTextPane tp = new JTextPane();
  private static Supplier sg =
    new RandomSupplier.String(7);
  public TextPane() {
    b.addActionListener(e -> {
      for(int i = 1; i < 10; i++)
        tp.setText(tp.getText() + sg.get() + "\n");
    });
    add(new JScrollPane(tp));
    add(BorderLayout.SOUTH, b);
  }
  public static void main(String[] args) {
    run(new TextPane(), 475, 425);
  }
}
