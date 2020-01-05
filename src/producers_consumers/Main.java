package producers_consumers;

import java.util.concurrent.ArrayBlockingQueue;

public class Main {

    public static void main(String[] args) {

        ArrayBlockingQueue<Product> queue = new ArrayBlockingQueue<>(6);

        (new Thread(new Producer(queue, 12))).start();
        (new Thread(new Producer(queue, 12))).start();
        (new Thread(new Producer(queue, 12))).start();
        (new Thread(new Consumer(queue, 8))).start();
        (new Thread(new Consumer(queue, 10))).start();
    }
}
