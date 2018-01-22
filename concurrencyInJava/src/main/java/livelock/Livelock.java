package livelock;

/**
 * Livelock это рекурсивная ситуация, когда два или более потока будут продолжать повторять конкретную логику кода.
 * Created by Egor on 24.12.2017.
 * Пример по ссылке: http://logicbig.com/tutorials/core-java-tutorial/java-multi-threading/thread-livelock/
 */

public class Livelock {

    public static void main (String[] args) {
        final Worker worker1 = new Worker("Worker 1 ", true);
        final Worker worker2 = new Worker("Worker 2", true);

        final CommonResource s = new CommonResource(worker1);

        new Thread(() -> {
            worker1.work(s, worker2);
        }).start();

        new Thread(() -> {
            worker2.work(s, worker1);
        }).start();
    }
}