package producers_consumers;

public class Product {

    private static int globalId;
    private int productId;

    public Product(int productId){
        this.productId = productId;
    }

    public static synchronized Product produce(){
        globalId++;
        System.out.println("Produced id:" + globalId);
        return new Product(globalId);
    }

    public static synchronized void consume(Product product){
        System.out.println("Consumed id:" + product.productId);
    }
}
