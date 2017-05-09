package repeating.lecture11;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * Created by Mateusz on 09.05.2017.
 */
public class Runner implements repeating.Runner {

    private Account acc1 = new Account();
    private Account acc2 = new Account();

    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();


    private void acquireLocks(Lock firstLock, Lock secondLock) throws InterruptedException {
        while (true) {
            //Acquire locks
            boolean gotFirstLock = false;
            boolean gotSecondLock = false;

            try {
                gotFirstLock = firstLock.tryLock();
                gotSecondLock = firstLock.tryLock();
            } finally {
                if (gotFirstLock && gotSecondLock)
                    return;

                if (gotFirstLock) {
                    firstLock.unlock();
                }

                if (gotSecondLock) {
                    secondLock.unlock();
                }
            }

            //Locks not acquired
            Thread.sleep(1);
        }
    }

    @Override
    public void firstThread() throws InterruptedException {
        Random random = new Random();

        IntStream.rangeClosed(1, 10000).forEach(i -> {
            try {
                acquireLocks(lock1, lock2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Account.transfer(acc1, acc2, random.nextInt(100));
            lock1.unlock();
            lock2.unlock();
        });
    }

    @Override
    public void secondThread() throws InterruptedException {
        Random random = new Random();

        IntStream.rangeClosed(1, 10000).forEach(i -> {
            try {
                acquireLocks(lock1, lock2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Account.transfer(acc2, acc1, random.nextInt(100));
            lock1.unlock();
            lock2.unlock();
        });
    }

    @Override
    public void finished() {
        System.out.println("Account 1 balance is: " + acc1.getBalance());
        System.out.println("Account 2 balance is: " + acc2.getBalance());
        System.out.println("Totoal balance: " + (acc1.getBalance() + acc2.getBalance()));
    }
}
