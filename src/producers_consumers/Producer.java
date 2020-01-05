package producers_consumers;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable{

    private BlockingQueue<Product> queue;
    private int productionSpeed;

    public Producer(BlockingQueue<Product> queue, int productionSpeed) {
        this.queue = queue;
        this.productionSpeed = productionSpeed;
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(productionSpeed * 100);
                queue.put(Product.produce());
            } catch (InterruptedException e) {}
        }
    }
}
