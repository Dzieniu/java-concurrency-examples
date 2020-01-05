package firefight;

import java.util.concurrent.Exchanger;

public class Main {

    public static void main(String[] args) {

        Bucket bucket1 = new Bucket(90, 1);
        Bucket bucket2 = new Bucket(80, 1);
        Bucket bucket3 = new Bucket(70, 1);

        Exchanger<Bucket> ex1 = new Exchanger<>();
        Exchanger<Bucket> ex2 = new Exchanger<>();

        Fire fire1 = new Fire(300, 20);
        Firefighter firefighter1 = new Firefighter(bucket1, null, ex1, fire1);
        Firefighter firefighter2 = new Firefighter(bucket2, ex1 , ex2, fire1);
        Firefighter firefighter3 = new Firefighter(bucket3, ex2, null, fire1);

        Thread fireThread = new Thread(fire1);
        fireThread.start();
        new Thread(firefighter1).start();
        new Thread(firefighter2).start();
        new Thread(firefighter3).start();

        long time = System.currentTimeMillis();
        while (fireThread.isAlive()){
            try {
                System.out.println("|" + fire1.getSize()
                        + "|    [" + firefighter1.getBucket_held().getSize()
                        + "]    [" + firefighter2.getBucket_held().getSize()
                        + "]    [" + firefighter3.getBucket_held().getSize() + "]");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Fire was extinguished in: " +
                (System.currentTimeMillis() - time)/1000 + "s");
    }
}
