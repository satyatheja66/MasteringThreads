package playground;

import java.util.concurrent.locks.*;

// this class can cause deadlock if f() and g()
// are called by two threads
public class HappyLockerReentrantLock implements HappyLocker {
    private final Lock lock1 = new ReentrantLock();
    private final Lock lock2 = new ReentrantLock();

    public void f() {
        lock1.lock();
        try {
            lock2.lock();
            try {
                // do something ...
            } finally {
                lock2.unlock();
            }
        } finally {
            lock1.unlock();
        }
    }

    // a Thread that calls g() first acquires lock
    // and then this through f()
    public void g() {
        lock2.lock();
        try {
            f();
        } finally {
            lock2.unlock();
        }
    }
}
