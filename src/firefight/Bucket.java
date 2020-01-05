package firefight;

public class Bucket {

    private int size;
    private int full;

    public Bucket(int size, int full){
        this.size = size;
        this.full = full;
    }

    public int empty(){
        int full_copy = full;
        full = 0;
        return size * full_copy;
    }

    public int fill(){
        full = 1;
        return size * full;
    }

    public int getSize(){
        return size;
    }
}
