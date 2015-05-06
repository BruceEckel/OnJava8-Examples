//: gui/LookAndFeel.java
// Selecting different looks & feels.
// {Args: motif}
import javax.swing.*;
import java.awt.*;
import static net.mindview.util.SwingConsole.*;

public class LookAndFeel extends JFrame {
  private String[] choices =
    "Eeny Meeny Minnie Mickey Moe Larry Curly".split(" ");
  private Component[] samples = {
    new JButton("JButton"),
    new JTextField("JTextField"),
    new JLabel("JLabel"),
    new JCheckBox("JCheckBox"),
    new JRadioButton("Radio"),
    new JComboBox<String>(choices),
    new JList<String>(choices),
  };
  public LookAndFeel() {
    super("Look And Feel");
    setLayout(new FlowLayout());
    for(Component component : samples)
      add(component);
  }
  private static void usageError() {
    System.out.println(
      "Usage:LookAndFeel [cross|system|motif]");
    System.exit(1);
  }
  public static void main(String[] args) {
    if(args.length == 0) usageError();
    switch(args[0]) {
      case "cross":
        try {
          UIManager.setLookAndFeel(UIManager.
            getCrossPlatformLookAndFeelClassName());
        } catch(ClassNotFoundException |
                InstantiationException |
                IllegalAccessException |
                UnsupportedLookAndFeelException e) {
          e.printStackTrace();
        }
        break;
      case "system":
        try {
          UIManager.setLookAndFeel(UIManager.
            getSystemLookAndFeelClassName());
        } catch(ClassNotFoundException |
                InstantiationException |
                IllegalAccessException |
                UnsupportedLookAndFeelException e) {
          e.printStackTrace();
        }
        break;
      case "motif":
        try {
          UIManager.setLookAndFeel("com.sun.java."+
            "swing.plaf.motif.MotifLookAndFeel");
        } catch(ClassNotFoundException |
                InstantiationException |
                IllegalAccessException |
                UnsupportedLookAndFeelException e) {
          e.printStackTrace();
        }
        break;
      default:
        usageError();
    }
    // Note the look & feel must be set before
    // any components are created.
    run(new LookAndFeel(), 300, 300);
  }
} ///:~
