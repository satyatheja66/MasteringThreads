package playground;

import java.util.concurrent.atomic.*;

public class ThreadCreationTest {
    public static void main(String... args) {
        var threads_created = new AtomicInteger(0);
        while (true) {
            new Thread() {
                public void run() {
                    System.out.println("threads created: " +
                        threads_created.incrementAndGet());
                    synchronized (this) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            }.start();
        }
    }
}
