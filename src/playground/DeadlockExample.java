package playground;

public class DeadlockExample {
    public static void main(String... args) {
        int UPTO = Integer.parseInt(args[0]);
        var pc = new HappyLockerHybridStampedLock();
        var fcaller = new Thread(() -> {
            for (int i = 0; i < UPTO; i++) pc.f();
            System.out.println("No deadlock");
        }, "f()");
        var gcaller = new Thread(() -> {
            for (int i = 0; i < UPTO; i++) pc.g();
        }, "g()");
        fcaller.start();
        gcaller.start();
    }
}

