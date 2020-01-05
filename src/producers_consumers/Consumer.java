package producers_consumers;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{

    private BlockingQueue<Product> queue;
    private int consumptionSpeed;

    public Consumer(BlockingQueue<Product> queue, int consumptionSpeed) {
        this.queue = queue;
        this.consumptionSpeed = consumptionSpeed;
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(consumptionSpeed * 100);
                Product.consume(queue.take());
            } catch (InterruptedException e) {}
        }
    }
}
