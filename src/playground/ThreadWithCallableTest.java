package playground;

import org.junit.*;

import java.util.concurrent.*;

import static org.junit.Assert.*;

public class ThreadWithCallableTest {
    @Test
    public void testingThread() throws Throwable {
        ExecutorService pool = Executors.newSingleThreadExecutor();
        try {
            Future<?> future = pool.submit(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new CancellationException();
                }
                fail("This did not work");
            });

            pool.shutdown();

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
