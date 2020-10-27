package playground;

import java.util.concurrent.*;

public interface StampedSemaphore {
    long acquire() throws InterruptedException;
    long acquireUninterruptibly();
    long tryAcquire(); // 0 if unsuccessful
    long tryAcquire(long timeout, TimeUnit unit) throws InterruptedException; // 0 if unsuccessful
    void release(long stamp);

    long[] acquire(int permits) throws InterruptedException;
    long[] acquireUninterruptibly(int permits);
    long[] tryAcquire(int permits);
    long[] tryAcquire(int permits, long timeout, TimeUnit unit) throws InterruptedException;
    void release(long[] stamps);

    int availablePermits();

    int drainPermits();
}
