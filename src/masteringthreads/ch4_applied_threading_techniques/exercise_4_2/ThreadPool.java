package masteringthreads.ch4_applied_threading_techniques.exercise_4_2;

import java.util.concurrent.*;

// TODO: Replace inner workings of ThreadPool with ExecutorService
public class ThreadPool {
    private final ExecutorService pool;
    private final LinkedBlockingQueue<Runnable> workQueue =
        new LinkedBlockingQueue<>();

    public ThreadPool(int poolSize) {
        pool = new ThreadPoolExecutor(poolSize, poolSize,
            0L, TimeUnit.MILLISECONDS,
            workQueue);
    }

    public void submit(Runnable job) {
        pool.submit(job);
    }

    public int getRunQueueLength() {
        return workQueue.size();
    }

    public void shutdown() {
        pool.shutdownNow();
    }
}
