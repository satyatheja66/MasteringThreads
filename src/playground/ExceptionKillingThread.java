package playground;

import java.util.*;
import java.util.concurrent.*;

public class ExceptionKillingThread {
    // not correct, but seems to work
    private static Queue<Integer> queue = new ConcurrentLinkedQueue<>();

    public static void main(String... args) throws InterruptedException {
        Runnable job = () -> {
            while(queue.isEmpty());
            System.out.println("queue is not empty");
        };
        Thread thread = new Thread(job);
        thread.start();

        Thread.sleep(500);

        queue = null;
    }
}
