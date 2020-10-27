package masteringthreads.ch5_threading_problems.exercise_5_1;

import playground.*;

public class DeadlockExample {

    private static final int UPTO = 100_000;

    public static void main(String... args) {
        var pc = new PrinterClass();
        var fcaller = new Thread(() -> {
            for (int i = 0; i < UPTO; i++) pc.setPrintingEnabled(false);
            System.out.println("No deadlock");
        }, "setPrintingEnabled()");
        var gcaller = new Thread(() -> {
            for (int i = 0; i < UPTO; i++) pc.print("test");
        }, "print()");
        fcaller.start();
        gcaller.start();
    }
}

