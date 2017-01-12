package lecture12semaphores;

import java.util.concurrent.Semaphore;

/**
 * Created by Mateusz on 12.01.2017.
 */

public class Connection {

    private static Connection instance;

    private Semaphore semaphore = new Semaphore(10,true);

    private int currentConnections = 0;

    private Connection(){
    }

    public static Connection getInstance(){
        if(instance==null)
            instance = new Connection();

        return instance;
    }

    public void connect(){
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            doConnect();
        }finally {
            semaphore.release();
        }
    }
    private void doConnect(){
        synchronized (this){
            currentConnections++;
            System.out.println("Current connections: "+currentConnections);
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (this){
            currentConnections--;
            System.out.println("Current connections: "+currentConnections);
        }
    }
}
