package playground;

import java.util.concurrent.locks.*;

// this class can cause deadlock if f() and g()
// are called by two threads
public class HappyLockerHybridStampedLock implements HappyLocker {
    private final StampedLock lock = new StampedLock();

    public synchronized void f() {
        long stamp = lock.writeLock();
        try {
            // do something ...
        } finally {
            lock.unlockWrite(stamp);
        }
    }

    // a Thread that calls g() first acquires lock
    // and then this through f()
    public void g() {
        long stamp = lock.writeLock();
        try {
            f();
        } finally {
            lock.unlockWrite(stamp);
        }
    }
}
