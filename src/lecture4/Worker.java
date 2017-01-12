package lecture4;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Mateusz on 12.01.2017.
 */
public class Worker {

    private Random random = new Random();

    private List<Integer> list1 = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();

    public void stageOne(){
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        addRandomValueToList(list1);
    };

    public void stageTwo(){
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        addRandomValueToList(list2);
    };

    public void process(){
        for(int i=0;i<1000;i++){
            stageOne();
            stageTwo();
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
