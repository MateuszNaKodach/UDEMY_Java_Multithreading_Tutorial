package lecture6;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Mateusz on 12.01.2017.
 */

class Processor implements Runnable{
    private CountDownLatch latch;
    private int id;

    public Processor(int id, CountDownLatch latch){
        this.id = id;
        this.latch = latch;
    }
    @Override
    public void run() {
        System.out.println("Started!"+id);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        latch.countDown();
        System.out.println("Completed " + id);
        System.out.println("Latch count:" + latch.getCount());
}
}
public class App {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(3);
        ExecutorService executor = Executors.newFixedThreadPool(2);

        for(int i=0;i<3;i++){
            executor.submit(new Processor(i,latch));
        }

        executor.shutdown();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Completed all!");
    }
}
