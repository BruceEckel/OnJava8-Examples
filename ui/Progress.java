// ui/Progress.java
// Using sliders, progress bars and progress monitors.
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import static com.mindviewinc.util.SwingConsole.*;

public class Progress extends JFrame {
  private JProgressBar pb = new JProgressBar();
  private ProgressMonitor pm = new ProgressMonitor(
    this, "Monitoring Progress", "Test", 0, 100);
  private JSlider sb =
    new JSlider(JSlider.HORIZONTAL, 0, 100, 60);
  public Progress() {
    setLayout(new GridLayout(2,1));
    add(pb);
    pm.setProgress(0);
    pm.setMillisToPopup(1000);
    sb.setValue(0);
    sb.setPaintTicks(true);
    sb.setMajorTickSpacing(20);
    sb.setMinorTickSpacing(5);
    sb.setBorder(new TitledBorder("Slide Me"));
    pb.setModel(sb.getModel()); // Share model
    add(sb);
    sb.addChangeListener(e ->
      pm.setProgress(sb.getValue()));
  }
  public static void main(String[] args) {
    run(new Progress(), 300, 200);
  }
}
