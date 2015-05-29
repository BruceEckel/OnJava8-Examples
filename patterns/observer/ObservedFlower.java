//: patterns/observer/ObservedFlower.java
// ©2015 MindView LLC: see Copyright.txt
// Demonstration of "observer" pattern.
package patterns.observer;
import java.util.*;
import static net.mindview.util.Print.*;

class Flower {
  private boolean isOpen;
  private OpenNotifier oNotify =
    new OpenNotifier();
  private CloseNotifier cNotify =
    new CloseNotifier();
  public Flower() { isOpen = false; }
  public void open() { // Opens its petals
    isOpen = true;
    oNotify.notifyObservers();
    cNotify.open();
  }
  public void close() { // Closes its petals
    isOpen = false;
    cNotify.notifyObservers();
    oNotify.close();
  }
  public Observable opening() {
    return oNotify;
  }
  public Observable closing() {
    return cNotify;
  }
  private class OpenNotifier extends Observable {
    private boolean alreadyOpen = false;
    @Override
    public void notifyObservers() {
      if(isOpen && !alreadyOpen) {
        setChanged();
        super.notifyObservers();
        alreadyOpen = true;
      }
    }
    public void close() { alreadyOpen = false; }
  }
  private class CloseNotifier extends Observable{
    private boolean alreadyClosed = false;
    @Override
    public void notifyObservers() {
      if(!isOpen && !alreadyClosed) {
        setChanged();
        super.notifyObservers();
        alreadyClosed = true;
      }
    }
    public void open() { alreadyClosed = false; }
  }
}

class Bee {
  private String name;
  private OpenObserver openObsrv =
    new OpenObserver();
  private CloseObserver closeObsrv =
    new CloseObserver();
  public Bee(String nm)  { name = nm; }
  // An inner class for observing openings:
  private class OpenObserver implements Observer{
    @Override
    public void update(Observable ob, Object a) {
      print("Bee " + name + "'s breakfast time!");
    }
  }
  // Another inner class for closings:
  private class CloseObserver implements Observer{
    @Override
    public void update(Observable ob, Object a) {
      print("Bee " + name + "'s bed time!");
    }
  }
  public Observer openObserver() {
    return openObsrv;
  }
  public Observer closeObserver() {
    return closeObsrv;
  }
}

class Hummingbird {
  private String name;
  private OpenObserver openObsrv =
    new OpenObserver();
  private CloseObserver closeObsrv =
    new CloseObserver();
  public Hummingbird(String nm) { name = nm; }
  private class OpenObserver implements Observer{
    @Override
    public void update(Observable ob, Object a) {
      print("Hummingbird " + name +
        "'s breakfast time!");
    }
  }
  private class CloseObserver implements Observer{
    @Override
    public void update(Observable ob, Object a) {
      print("Hummingbird " + name + "'s bed time!");
    }
  }
  public Observer openObserver() {
    return openObsrv;
  }
  public Observer closeObserver() {
    return closeObsrv;
  }
}

public class ObservedFlower {
  public static void main(String args[]) {
    Flower f = new Flower();
    Bee
      ba = new Bee("A"),
      bb = new Bee("B");
    Hummingbird
      ha = new Hummingbird("A"),
      hb = new Hummingbird("B");
    f.opening().addObserver(ha.openObserver());
    f.opening().addObserver(hb.openObserver());
    f.opening().addObserver(ba.openObserver());
    f.opening().addObserver(bb.openObserver());
    f.closing().addObserver(ha.closeObserver());
    f.closing().addObserver(hb.closeObserver());
    f.closing().addObserver(ba.closeObserver());
    f.closing().addObserver(bb.closeObserver());
    // Hummingbird B decides to sleep in:
    f.opening().deleteObserver(hb.openObserver());
    // A change that interests observers:
    f.open();
    f.open(); // It's already open, no change.
    // Bee A doesn't want to go to bed:
    f.closing().deleteObserver(ba.closeObserver());
    f.close();
    f.close(); // It's already closed; no change
    f.opening().deleteObservers();
    f.open();
    f.close();
  }
} ///:~
