package playground;

import java.util.concurrent.*;

public class ExecutorExamples {
    public static void main(String... args) {
        ExecutorService cached = new ThreadPoolExecutor(
            0, Integer.MAX_VALUE,
            60L, TimeUnit.SECONDS,
            new SynchronousQueue<>());
        ExecutorService limitedCached = new ThreadPoolExecutor(
            0, 1000,
            60L, TimeUnit.SECONDS,
            new SynchronousQueue<>(),
            new ThreadPoolExecutor.CallerRunsPolicy());

        ExecutorService fixed = new ThreadPoolExecutor(
            10, 10,
            0L, TimeUnit.MILLISECONDS, // irrelevant
            new LinkedBlockingQueue<>()); // unbounded queue

        ExecutorService pointless = new ThreadPoolExecutor(
            10, 100,
            1L, TimeUnit.MINUTES, // irrelevant
            new LinkedBlockingQueue<>()); // unbounded queue

        ExecutorService scheduled = new ScheduledThreadPoolExecutor(10);

        ExecutorService sortOfSingle = Executors.newFixedThreadPool(1);
        ((ThreadPoolExecutor)sortOfSingle).setCorePoolSize(2);

        ExecutorService single = Executors.newSingleThreadExecutor(); // cannot inline
        ExecutorService singleScheduled = Executors.newSingleThreadScheduledExecutor(); // cannot inline

        ExecutorService workStealing = new ForkJoinPool
            (Runtime.getRuntime().availableProcessors(),
                ForkJoinPool.defaultForkJoinWorkerThreadFactory,
                null, true);
    }
}
