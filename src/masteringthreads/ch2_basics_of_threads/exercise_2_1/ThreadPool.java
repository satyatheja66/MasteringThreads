package masteringthreads.ch2_basics_of_threads.exercise_2_1;

import net.jcip.annotations.*;

import java.util.*;

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
    // Create a LinkedList field containing Runnable
    @GuardedBy("jobs")
    private final LinkedList<Runnable> jobs = new LinkedList<>();
    // Hint: Since LinkedList is not thread-safe, we need to synchronize it

    public ThreadPool(int poolSize) {
        // create several Worker threads in the thread group
        for (int i = 0; i < poolSize; i++) {
            Worker worker = new Worker(group, "worker-" + groupNumber + "-" + (i + 1));
            worker.start();
        }
    }

    private Runnable take() throws InterruptedException {
        // if the LinkedList is empty, we wait
        //
        // remove the first job from the LinkedList and return it
        throw new UnsupportedOperationException("not implemented");
    }

    public void submit(Runnable job) {
        // Add the job to the LinkedList and notifyAll
    }

    public int getRunQueueLength() {
        // return the length of the LinkedList
        // remember to also synchronize!
        throw new UnsupportedOperationException("not implemented");
    }

    @SuppressWarnings("deprecation")
    public void shutdown() {
        // this should call stop() on the ThreadGroup.
    }

    private class Worker extends Thread {
        public Worker(ThreadGroup group, String name) {
            super(group, name);
        }

        public void run() {
            // we run in an infinite loop:
            // remove the next job from the linked list using take()
            // we then call the run() method on the job
        }
    }
}
