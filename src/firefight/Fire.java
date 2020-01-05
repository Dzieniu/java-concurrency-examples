package firefight;

public class Fire implements Runnable{

    private int size;
    private int spreadSpeed;

    public Fire(int size, int spreadSpeed){
        this.size = size;
        this.spreadSpeed = spreadSpeed;
    }

    public void extinguish(int size){
        this.size -= size;
    }

    public int getSize() {
        return size;
    }

    @Override
    public void run() {
        while (size>0){
            try {
                size += spreadSpeed;
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
