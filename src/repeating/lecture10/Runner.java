package repeating.lecture10;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * Created by Mateusz on 09.05.2017.
 */
public class Runner {

    private int count = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    private void increment() {
        IntStream.rangeClosed(1, 10000).forEach(i -> count++);
    }

    public void firstThread() throws InterruptedException {
        lock.lock();

        System.out.println("Waiting...");
        condition.await(); //unlock the lock
        System.out.println("Woken up!");

        try {
            increment();
            System.out.println("Returned to first Thread!");
        } finally {
            System.out.println("First thread unlock the lock!");
            lock.unlock(); //always unlock the lock
        }
    }

    public void secondThread() throws InterruptedException {

        Thread.sleep(1000);

        lock.lock();

        System.out.println("Press the return key!");
        new Scanner(System.in).nextLine();
        System.out.println("Got return key!");


        condition.signal();

        try {
            increment();
            System.out.println("Returned to second Thread!");
        } finally {
            System.out.println("Second thread unlock the lock!");
            lock.unlock(); //always unlock the lock
        }

    }

    public void finished() {
        System.out.println("Count is: " + count);
    }
}
