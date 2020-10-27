package masteringthreads.ch4_applied_threading_techniques.exercise_4_1;

import java.util.concurrent.*;

public class ThreadPool {
    private final ThreadGroup group = new ThreadGroup("ThreadPoolGroup");
    private final BlockingQueue<Runnable> tasks = new LinkedBlockingQueue<>();
    private boolean running = true;

    public ThreadPool(int poolSize) {
        for (int i = 0; i < poolSize; i++) {
            var worker = new Worker(group, "worker-" + i);
            worker.start();
        }
    }

    private Runnable take() throws InterruptedException {
        return tasks.take();
    }

    public void submit(Runnable job) {
        tasks.add(job);
//        boolean offer = tasks.offer(job); // if you have offer, check return value
//        if (!offer) throw new RejectedExecutionException("Job " + job + " rejected");
//        tasks.put(job); // throws InterruptedException and blocks caller
    }

    public int getRunQueueLength() {
        return tasks.size();
    }

    public void shutdown() {
        running = false;
        group.interrupt();
    }

    private class Worker extends Thread {
        public Worker(ThreadGroup group, String name) {
            super(group, name);
        }

        public void run() {
            while (running) {
                try {
                    take().run();
                } catch (InterruptedException consumeAndExit) {
                    return;
                }
            }
        }
    }

}
