package playground;

import java.util.concurrent.*;

public class FixedVsWorkStealingPool {
    public static void main(String... args) throws InterruptedException {
        test(Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()));
        test(Executors.newWorkStealingPool()); // great for short jobs
    }

    private static void test(ExecutorService pool) throws InterruptedException {
        long time = System.nanoTime();
        try {
            Runnable job = () -> {
            };
            for (int i = 0; i < 10_000_000; i++) {
                pool.submit(job);
            }
            pool.shutdown();
            while (!pool.awaitTermination(1, TimeUnit.SECONDS)) {
                System.out.print(".");
            }
            System.out.println("done");
        } finally {
            time = System.nanoTime() - time;
            System.out.printf("time = %dms%n", (time / 1_000_000));
        }
    }
}
