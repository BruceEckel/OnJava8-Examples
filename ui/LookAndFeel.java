// ui/LookAndFeel.java
// ©2016 MindView LLC: see Copyright.txt
// Selecting different looks & feels.
// {Args: motif}
import javax.swing.*;
import java.awt.*;
import java.util.*;
import static onjava.SwingConsole.*;

public class LookAndFeel extends JFrame {
  private String[] choices =
    "Eeny Meeny Minnie Mickey Moe Larry Curly"
    .split(" ");
  private Component[] samples = {
    new JButton("JButton"),
    new JTextField("JTextField"),
    new JLabel("JLabel"),
    new JCheckBox("JCheckBox"),
    new JRadioButton("Radio"),
    new JComboBox<>(choices),
    new JList<>(choices),
  };
  private static
  Map<String,String> optionMap = new HashMap<>();
  private static String options = "";
  public LookAndFeel() {
    super("Look And Feel");
    setLayout(new FlowLayout());
    for(Component component : samples)
      add(component);
  }
  public static void initOptions() {
    if(optionMap.isEmpty()) {
      UIManager.LookAndFeelInfo[] lafi =
        UIManager.getInstalledLookAndFeels();
      for(UIManager.LookAndFeelInfo lf : lafi) {
        String classname = lf.getClassName();
        String[] parts = classname.split("\\.");
        String option = parts[parts.length - 2];
        optionMap.put(option, classname);
      }
      for(String option : optionMap.keySet())
        options += option + " | ";
      options =
        options.substring(0, options.length() - 3);
      options = "cross | system | " + options;
    }
  }
  private static void usageError() {
    System.out.println(
      "Usage:LookAndFeel [ " + options + " ]");
    System.exit(1);
  }
  public static void main(String[] args) {
    initOptions();
    if(args.length == 0) usageError();
    try {
      switch(args[0]) {
        case "cross":
          UIManager.setLookAndFeel(UIManager.
            getCrossPlatformLookAndFeelClassName());
          break;
      case "system":
          UIManager.setLookAndFeel(UIManager.
            getSystemLookAndFeelClassName());
          break;
      case "motif":
        UIManager.setLookAndFeel("com.sun.java."+
          "swing.plaf.motif.MotifLookAndFeel");
        break;
      default:
        if(optionMap.containsKey(args[0]))
          UIManager.setLookAndFeel(
            optionMap.get(args[0]));
        else
          usageError();
      }
    } catch(ClassNotFoundException |
            InstantiationException |
            IllegalAccessException |
            UnsupportedLookAndFeelException e) {
      usageError();
    }
    // Note the look & feel must be set before
    // any components are created.
    run(new LookAndFeel(), 300, 300);
  }
}
