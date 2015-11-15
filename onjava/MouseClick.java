// onjava/MouseClick.java
// ©2016 MindView LLC: see Copyright.txt
// Helper interface to allow lambda expressions
package onjava;
import java.awt.event.*;

// Default everything except mouseClicked():
public interface MouseClick extends MouseListener {
    @Override
    public default void mouseEntered(MouseEvent e) {}
    @Override
    public default void mouseExited(MouseEvent e) {}
    @Override
    public default void mousePressed(MouseEvent e) {}
    @Override
    public default void mouseReleased(MouseEvent e) {}
}
