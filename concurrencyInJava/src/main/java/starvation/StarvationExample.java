package starvation;

/**
 * Starvation это ситуация, когда поток находится в состоянии ожидания,
 * поскольку он не получает доступа к общим ресурсам или потому,
 * что приходят потоки с более высоким приоритетом.
 * Created by Egor on 24.12.2017.
 * Пример по ссылке: http://www.codejava.net/java-core/concurrency/understanding-deadlock-livelock-and-starvation-with-code-examples-in-java
 */

public class StarvationExample {

    public static void main(String[] args) {
        Worker worker = new Worker();

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                public void run() {
                    worker.work();
                }
            }).start();
        }
    }
}

