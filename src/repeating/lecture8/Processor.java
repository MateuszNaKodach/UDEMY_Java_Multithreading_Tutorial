package repeating.lecture8;

import java.util.Scanner;

/**
 * Created by Mateusz on 09.05.2017.
 */
public class Processor {

    void produce() throws InterruptedException {
        synchronized (this) {
            System.out.println("Producer thread running... ");
            wait();
            System.out.println("Resumed.");
        }
    }


    void consume() throws InterruptedException {

        Scanner scanner = new Scanner(System.in);
        Thread.sleep(2000);

        synchronized (this) {
            System.out.println("Waiting for return key...");
            scanner.nextLine();
            System.out.println("Return key pressed.");
            notify();
        }
    }


//https://www.udemy.com/java-multithreading/learn/v4/t/lecture/124594?start=0
}
