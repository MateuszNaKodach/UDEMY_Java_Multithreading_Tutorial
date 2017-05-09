package firsttime.lecture4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Mateusz on 12.01.2017.
 */
public class Worker {

    private Random random = new Random();

    private Object lock1 = new Object();
    private Object lock2 = new Object();

    private List<Integer> list1 = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();

    public void stageOne(){
        synchronized (lock1) {
            sleepTimeInMilis(1);
            addRandomValueToList(list1);
        }
    };

    public void stageTwo(){
        synchronized (lock2) {
            sleepTimeInMilis(1);
            addRandomValueToList(list2);
        }
    };

    public void process(){
        for(int i=0;i<1000;i++){
            stageOne();
            stageTwo();
        }
    }

    private void sleepTimeInMilis(long milis){
        try {
            Thread.sleep(milis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void addRandomValueToList(List<Integer> list){
        list.add(random.nextInt(100));
    }


    public void main() {
        System.out.println("Starting...");
        long start = System.currentTimeMillis();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                process();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                process();
            }
        });

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();

        System.out.println("Time taken:" + (end - start));
        System.out.println("List 1: " + list1.size());
        System.out.println("Lista 2: "+ list2.size());
    }
}
