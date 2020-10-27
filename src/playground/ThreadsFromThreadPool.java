package playground;

import java.util.*;
import java.util.concurrent.*;

public class ThreadsFromThreadPool {
    public static void main(String... args) throws InterruptedException {
        Collection<Thread> poolThreads =
            Collections.synchronizedSet(
                Collections.newSetFromMap(new WeakHashMap<>()));
        ThreadFactory defaultFactory = Executors.defaultThreadFactory();
        ThreadFactory factory = r -> {
            Thread thread = defaultFactory.newThread(r);
            poolThreads.add(thread);
            return thread;
        };

        ExecutorService pool = Executors.newCachedThreadPool(factory);

        pool.submit(() -> {
            while (true) ;// very bad code
        });

        pool.shutdown();
        if (!pool.awaitTermination(200, TimeUnit.MILLISECONDS)) {
            System.out.println("Ok, shutdown() didn't work ...");
        }

        pool.shutdownNow();
        if (!pool.awaitTermination(200, TimeUnit.MILLISECONDS)) {
            System.out.println("Ok, even shutdownNow() didn't work ...");
        }

        for (Thread poolThread : poolThreads) {
            poolThread.stop(); // NEVER do this in real life
        }
        if (!pool.awaitTermination(200, TimeUnit.MILLISECONDS)) {
            System.out.println("Ok, that should have worked ...");
        }

        if (pool.isTerminated()) {
            System.out.println("Phew, all good now");
        }
    }
}
