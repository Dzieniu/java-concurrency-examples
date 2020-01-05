
package buses;

public class Main {

    public static void main(String[] args) {

        BusStation busStation1 = new BusStation("Dworzec centralny");

        Bus bus1 = new Bus("0", busStation1, 5);
        Bus bus2 = new Bus("18", busStation1, 8);

        new Thread(bus1).start();
        new Thread(bus2).start();
    }
}
