package yield;

/**
 * Created by Egor on 24.12.2017.
 */

public class YieldExample {


    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {

            OneThreads oneThreads1 = new OneThreads("Threads1");
            OneThreads oneThreads2 = new OneThreads("Threads2");
            OneThreads oneThreads3 = new OneThreads("Threads3");
            TwoThreads twoThreads4 = new TwoThreads("Threads4");

            oneThreads1.start();
            oneThreads2.start();
            oneThreads3.start();
            twoThreads4.start();

        }
    }



}
