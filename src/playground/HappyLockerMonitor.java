package playground;

// this class can cause deadlock if f() and g()
// are called by two threads
public class HappyLockerMonitor implements HappyLocker {
    private final Object lock = new Object();

    // a Thread that calls f() first acquires this
    // and then lock
    public synchronized void f() {
        synchronized (lock) {
            // do something ...
        }
    }

    // a Thread that calls g() first acquires lock
    // and then this through f()
    public void g() {
        synchronized (lock) {
            f();
        }
    }
}
