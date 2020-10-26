package playground;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.util.concurrent.locks.*;

public class ThreadCreationTestLoom {
    public static void main(String... args) {
        var threads_created = new AtomicInteger(0);
        Collection<Thread> threads = new ConcurrentLinkedQueue<>();
        while (true) {
            threads.add(
                Thread.startVirtualThread(new Runnable() {
                    public void run() {
                        System.out.println("threads created: " +
                            threads_created.incrementAndGet());
                        LockSupport.park();
                    }
                })
            );
        }
    }
}
