package playground;

import java.util.concurrent.*;

public class ExecutorExamples {
    public static void main(String... args) {
        ExecutorService cached = Executors.newCachedThreadPool();
        ExecutorService fixed = Executors.newFixedThreadPool(10);
        ExecutorService scheduled = Executors.newScheduledThreadPool(10);
        ExecutorService single = Executors.newSingleThreadExecutor();
        ExecutorService singleScheduled = Executors.newSingleThreadScheduledExecutor();
        ExecutorService workStealing = Executors.newWorkStealingPool();
    }
}
