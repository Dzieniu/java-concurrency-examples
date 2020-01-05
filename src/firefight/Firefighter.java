package firefight;

import java.util.concurrent.Exchanger;

public class Firefighter implements Runnable{

    private Bucket bucket_held;
    private Exchanger<Bucket> exchanger_left, exchanger_right;
    private Fire fire;

    public Firefighter(Bucket bucket_held, Exchanger<Bucket> exchanger_left, Exchanger<Bucket> exchanger_right, Fire fire){
        this.bucket_held = bucket_held;
        this.exchanger_left = exchanger_left;
        this.exchanger_right = exchanger_right;
        this.fire = fire;
    }

    @Override
    public void run() {
        if(exchanger_left == null){
            extinguishTheFire();
        } else
        if(exchanger_right == null){
            drawWater();
        } else {
            passTheBucket();
        }
    }

    private void extinguishTheFire(){
        while (fire.getSize()>0){
            try {
                Thread.sleep(1000);
                fire.extinguish(bucket_held.empty());
                bucket_held = exchanger_right.exchange(bucket_held);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void drawWater(){
        while (fire.getSize()>0){
            try {
                Thread.sleep(2000);
                bucket_held.fill();
                bucket_held = exchanger_left.exchange(bucket_held);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void passTheBucket(){
        while (fire.getSize()>0){
            try {
                Thread.sleep(1000);
                bucket_held = exchanger_left.exchange(bucket_held);
                bucket_held = exchanger_right.exchange(bucket_held);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Bucket getBucket_held() {
        return bucket_held;
    }
}
