package lecture2.demo1;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * Created by Mateusz on 21.12.2016.
 */

class Processor extends Thread{
    private volatile boolean running = true;
    @Override
    public void run() {
        while(running) {
            System.out.println("Hello!");

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

     void shutdown(){
        running=false;
    }
}
public class App {

    public static void main(String[] args){
        Processor proc1 = new Processor();

        proc1.start();

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        proc1.shutdown();
    }
}
