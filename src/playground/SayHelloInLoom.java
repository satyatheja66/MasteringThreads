package playground;

public class SayHelloInLoom {
    public static void main(String... args) throws InterruptedException {
        Runnable task = () -> {
            System.out.println("Hello Safari Students");
        };
        Thread.startVirtualThread(task);

//        Thread.sleep(50);
    }
}
