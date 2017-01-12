package lecture12semaphores;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by Mateusz on 12.01.2017.
 */
public class App {
    public static void main(String[] args) throws Exception {
       /* Semaphore semaphore = new Semaphore(1);

        System.out.println("Avaiable permits: " + semaphore.availablePermits());
        semaphore.release(2);
        System.out.println("Avaiable permits: " + semaphore.availablePermits());
        try {
            semaphore.acquire(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Avaiable permits: " + semaphore.availablePermits());*/



        ExecutorService executor = Executors.newCachedThreadPool();

        for(int i=0;i<200;i++){
            executor.submit(()->{
                Connection.getInstance().connect();
            });
        }

        executor.shutdown();


        executor.awaitTermination(1, TimeUnit.DAYS);
    }
}
