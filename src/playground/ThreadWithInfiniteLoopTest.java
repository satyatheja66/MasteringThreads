package playground;

import org.junit.*;

import java.util.*;
import java.util.concurrent.*;

public class ThreadWithInfiniteLoopTest {
    @Test
    public void testingThread() throws Throwable {
        ExecutorService pool = Executors.newSingleThreadExecutor();
        try {
            Future<?> future = pool.submit(() -> {
                while (!Thread.currentThread().isInterrupted()) ;
                System.out.println("Interrupted - exiting");
            });

            List<Runnable> runnables = pool.shutdownNow();
            System.out.println("Uncompleted jobs: " + runnables);

            try {
                future.get();
            } catch (ExecutionException e) {
                throw e.getCause();
            }
        } finally {
            while (!pool.awaitTermination(1, TimeUnit.SECONDS)) {
                System.out.println("Waiting for pool shutdown");
            }
        }
    }
}
