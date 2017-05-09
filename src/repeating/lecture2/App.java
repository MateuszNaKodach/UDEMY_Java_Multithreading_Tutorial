package repeating.lecture2;

import java.util.Scanner;

/**
 * Created by Mateusz on 09.05.2017.
 */

class Processor implements Runnable{

    private volatile boolean running = true;

    @Override
    public void run() {
        while(running){
            System.out.println("Hello!");

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutdown(){
        running = false;
    }
}


public class App {



    public static void main(String[] args) {
        Processor processor1 = new Processor();

        new Thread(processor1).start();

        System.out.println("Press return to stop...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        processor1.shutdown();
    }
}
