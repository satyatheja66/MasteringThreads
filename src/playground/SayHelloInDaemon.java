package playground;

public class SayHelloInDaemon {
    public static void main(String... args) throws InterruptedException {
        Runnable task = () -> {
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            System.out.println("Hello Safari Students"); // might not show
        };
        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }
}
