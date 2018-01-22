package yield;

/**
 * Created by Egor on 24.12.2017.
 */

public class TwoThreads extends Thread {
    String name;

    public TwoThreads(String name){
        this.name = name;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread.yield();
        System.out.println("Приостановка текущего потока:" + name );
    }
}
