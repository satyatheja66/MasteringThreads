package playground;

import org.junit.*;

import java.util.concurrent.*;

import static org.junit.Assert.*;

public class ThreadWithCallableTestLoom {
    @Test
    public void testingThread() {
        try (ExecutorService pool = Executors.newSingleThreadExecutor()) {
            Future<?> future = pool.submit(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new CancellationException();
                }
                fail("This did not work");
            });
            try {
                future.join(); // "uninterruptible"
            } catch (CompletionException e) {
                try {
                    throw e.getCause();
                } catch (Error | RuntimeException cause) {
                    throw cause;
                } catch (ExecutionException cause) {
                    try {
                        throw cause.getCause();
                    } catch (Error | RuntimeException cause2) {
                        throw cause2;
                    } catch (Throwable t) {
                        throw e;
                    }
                } catch (Throwable t) {
                    throw e;
                }
            }
        }
    }
}
