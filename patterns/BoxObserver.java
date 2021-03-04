// patterns/BoxObserver.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Demonstration of the Observer pattern using
// Java's built-in observer classes.
// {ExcludeFromGradle} // Won't work under WSL2
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import onjava.*;
import onjava.MouseClick;

@SuppressWarnings("deprecation")
class Boxes extends JFrame {
  Observable notifier = new Observable() {
    @Override
    public void notifyObservers(Object b) {
      setChanged();
      super.notifyObservers(b);
    }
  };
  public Boxes(int grid) {
    setTitle("Demonstrates Observer pattern");
    Container cp = getContentPane();
    cp.setLayout(new GridLayout(grid, grid));
    for(int x = 0; x < grid; x++)
      for(int y = 0; y < grid; y++)
        cp.add(new Box(x, y, notifier));
    setSize(500, 400);
    setVisible(true);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
  }
}

@SuppressWarnings("deprecation")
class Box extends JPanel implements Observer {
  int x, y; // Location in grid
  Color color = COLORS[
    (int)(Math.random() * COLORS.length)
  ];
  static final Color[] COLORS = {
    Color.black, Color.blue, Color.cyan,
    Color.darkGray, Color.gray, Color.green,
    Color.lightGray, Color.magenta,
    Color.orange, Color.pink, Color.red,
    Color.white, Color.yellow
  };
  Box(int x, int y, Observable notifier) {
    this.x = x;
    this.y = y;
    notifier.addObserver(this);
    addMouseListener((MouseClick)
      e -> notifier.notifyObservers(Box.this));
  }
  @Override public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(color);
    Dimension s = getSize();
    g.fillRect(0, 0, s.width, s.height);
  }
  @Override
  public void update(Observable o, Object arg) {
    Box clicked = (Box)arg;
    if(nextTo(clicked)) {
      color = clicked.color;
      repaint();
    }
  }
  private boolean nextTo(Box b) {
    return Math.abs(x - b.x) <= 1 &&
           Math.abs(y - b.y) <= 1;
  }
}

public class BoxObserver {
  public static void main(String[] args) {
    int grid = 8;
    if(args.length > 0)
      grid = Integer.parseInt(args[0]);
    new Boxes(grid);
  }
}
