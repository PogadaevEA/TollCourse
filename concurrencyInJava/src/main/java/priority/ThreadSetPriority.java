package priority;

/*
* Created by Egor on 24.12.2017.
*/

class ThreadSetPriority implements Runnable {
    Thread thread = new Thread();

    public ThreadSetPriority() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        System.out.println("Приоритет "+ Thread.currentThread().getName() +" : " + thread.getPriority());
        thread.setPriority(Thread.MIN_PRIORITY);
        System.out.println("После изменения приоритета, новый приоритет " + Thread.currentThread().getName() + "  : " + thread.getPriority());
    }

    public static void main(String[] args) {
        new ThreadSetPriority();

    }

}