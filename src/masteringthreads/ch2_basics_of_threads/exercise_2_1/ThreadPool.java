package masteringthreads.ch2_basics_of_threads.exercise_2_1;

import net.jcip.annotations.*;

import java.util.*;
import java.util.concurrent.locks.*;

public class ThreadPool {
    private static final Object nextGroupNumberMonitor = new Object();
    @GuardedBy("nextGroupNumberMonitor")
    private static int nextGroupNumber = 1;
    private final int groupNumber;

    {
        synchronized (nextGroupNumberMonitor) {
            groupNumber = nextGroupNumber++;
        }
    }

    // Create a thread group field
    private final ThreadGroup group = new ThreadGroup("thread-pool-group-" + groupNumber);
    private final Lock lock = new ReentrantLock();
    private final Condition jobsNotEmpty = lock.newCondition();
    @GuardedBy("lock")
    private final List<Runnable> jobs = new LinkedList<>();

    public ThreadPool(int poolSize) {
        // create several Worker threads in the thread group
        for (int i = 0; i < poolSize; i++) {
            Worker worker = new Worker(group, "worker-" + groupNumber + "-" + (i + 1));
            worker.start();
        }
    }

    private Runnable take() throws InterruptedException {
        // if the LinkedList is empty, we wait
        lock.lock();
        try {
            while (jobs.isEmpty()) jobsNotEmpty.await();
            return jobs.remove(0);
        } finally {
            lock.unlock();
        }
    }

    public void submit(Runnable job) {
        lock.lock();
        try {
            jobs.add(job);
            jobsNotEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public int getRunQueueLength() {
        lock.lock();
        try {
            return jobs.size();
        } finally {
            lock.unlock();
        }
    }

    @SuppressWarnings("deprecation")
    public void shutdown() {
        group.stop();
    }

    private class Worker extends Thread {
        public Worker(ThreadGroup group, String name) {
            super(group, name);
        }

        public void run() {
            while (true) {
                try {
                    Runnable task = take();
                    task.run();
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                }
            }
        }
    }
}
