// com/mindviewinc/util/MouseClick.java
// Helper interface to allow lambda expressions
package com.mindviewinc.util;
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
