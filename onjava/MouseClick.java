// onjava/MouseClick.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
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
