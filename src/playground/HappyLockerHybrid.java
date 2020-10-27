package playground;

import java.util.concurrent.locks.*;

// this class can cause deadlock if f() and g()
// are called by two threads
public class HappyLockerHybrid implements HappyLocker {
    private final Lock lock = new ReentrantLock();

    public synchronized void f() {
        lock.lock();
        try {
            // do something ...
        } finally {
            lock.unlock();
        }
    }

    // a Thread that calls g() first acquires lock
    // and then this through f()
    public void g() {
        lock.lock();
        try {
            f();
        } finally {
            lock.unlock();
        }
    }
}
