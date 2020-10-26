package playground;

import org.junit.*;

import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

import static org.junit.Assert.*;

public class ThreadTest {
    @Test
    public void testingThread() throws Throwable {
        AtomicReference<Throwable> throwable = new AtomicReference<>();
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new CancellationException();
            }
            fail("This did not work");
        });
        thread.setUncaughtExceptionHandler((t, e) -> throwable.set(e));
        thread.start();
        thread.join();
        if (throwable.get() != null) {
            throw throwable.get();
        }
    }
}
