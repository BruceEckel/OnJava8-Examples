// ui/LongRunningTask.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// A badly designed program.
import javax.swing.*;
import java.awt.*;
import java.util.concurrent.*;
import static onjava.SwingConsole.*;

public class LongRunningTask extends JFrame {
  private JButton
    b1 = new JButton("Start Long Running Task"),
    b2 = new JButton("End Long Running Task");
  public LongRunningTask() {
    b1.addActionListener(e -> {
      try {
        TimeUnit.SECONDS.sleep(3);
      } catch(InterruptedException ex) {
        System.out.println("Task interrupted");
        return;
      }
      System.out.println("Task completed");
    });
    b2.addActionListener(e -> {
      // Interrupt yourself?
      Thread.currentThread().interrupt();
    });
    setLayout(new FlowLayout());
    add(b1);
    add(b2);
  }
  public static void main(String[] args) {
    run(new LongRunningTask(), 200, 150);
  }
}
