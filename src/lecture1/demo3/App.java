package lecture1.demo3;

/**
 * Created by Mateusz on 21.12.2016.
 */
public class App {

    public static void main(String[] args) {
        //Wyrazenie lambda:
        //Thread t1 = new Thread(() -> System.out.println("Siema!"));
        //t1.start();

        Thread t1 = new Thread(new Runnable() {
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
        });
        t1.start();
    }
}
