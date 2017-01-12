package lecture1.demo2;

/**
 * Created by Mateusz on 21.12.2016.
 */

class Runner implements Runnable{

    @Override
    public void run() {
        for(int i=0; i<10; i++) {
            System.out.println("Hello " + i);
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class App {

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runner());
        Thread t2 = new Thread(new Runner());
        t1.start();
        t2.start();
    }
}
