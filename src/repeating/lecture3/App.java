package repeating.lecture3;

import java.util.stream.IntStream;

/**
 * Created by Mateusz on 09.05.2017.
 */
public class App {

    private int count = 0;

    synchronized void increment() {
        count++;
    }

    public static void main(String[] args) {
        App app = new App();
        app.doWork();
    }

    void doWork() {
        Runnable runnable = () -> {
            IntStream.rangeClosed(1, 100).forEach(i -> increment());
        };

        Thread t1 = new Thread() {
            @Override
            public void run() {
                IntStream.rangeClosed(1, 100).forEach(i -> {
                    System.out.println(getName());
                    increment();
                });
            }
        };
        Thread t2 = new Thread() {
            @Override
            public void run() {
                IntStream.rangeClosed(1, 100).forEach(i -> {
                    System.out.println(getName());
                    increment();
                });
            }
        };

        t1.setName("Thread 1");
        t2.setName("Thread 2");

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Count is " + count);


    }
}
