package masteringthreads.ch3_the_secrets_of_concurrency.exercise_3_2;

import java.util.*;

public class BankAccountTest {
    public static void main(String... args) throws InterruptedException {
        // create a BankAccount instance
        BankAccount account = new BankAccount(1000);
        // create a Runnable lambda
        // in the run() method, call repeatedly:
        Runnable depositWithdraw = () -> {
            while (true) {
                // -XX:-Inline stops the inling
                account.deposit(100);
                account.withdraw(100);
            }
        };

        // create two thread instances, both pointing at
        // your Runnable
        Thread thread1 = new Thread(depositWithdraw);
        Thread thread2 = new Thread(depositWithdraw);
        thread1.start();
//        Thread.sleep(100);
        thread2.start();

        // create a timer task to once a second prints
        // out the balance
        Timer timer = new Timer(true); // daemon timer thread
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Balance should be 1000, 1100 or 1200, nothing else
                System.out.println(account.getBalance());
            }
        }, 1000, 1000);

    }
}
