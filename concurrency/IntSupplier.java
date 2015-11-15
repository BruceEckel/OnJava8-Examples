// concurrency/IntSupplier.java
// ©2016 MindView LLC: see Copyright.txt

public abstract class IntSupplier {
  private volatile boolean canceled = false;
  public abstract int next();
  // Allow this to be canceled:
  public void cancel() { canceled = true; }
  public boolean isCanceled() { return canceled; }
}
