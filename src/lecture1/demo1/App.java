package lecture1.demo1;

/**
 * Created by Mateusz on 20.12.2016.
 */


class Runner extends Thread{

    @Override
    public void run() {
        for(int i=0; i<10; i++) {
            System.out.println("Hello" + getName()+ " " + i);
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
        Runner runner1 = new Runner();
        runner1.setName("Runner1");
        Runner runner2 = new Runner();
        runner2.setName("Runner2");
        runner1.start(); //jesli wywolalbym run, uruchomi to samo w głównym wątku aplikacji
        runner2.start();
    }
}
