package repeating.lecture13;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Mateusz on 09.05.2017.
 */
public class App {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        Future<Integer> future = executor.submit(() -> {
            Random random = new Random();

            int duration = random.nextInt(4000);

            System.out.println("Starting...");

            try {
                Thread.sleep(duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Finished.");

            return duration;
        });

        executor.shutdown();


        try {
            if (future.isDone())
                System.out.println("DONE!");
            else
                System.out.println("UNDONE!");
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if (future.isDone())
            System.out.println("DONE!");
        else
            System.out.println("UNDONE!");
    }
}
