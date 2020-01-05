package buses;

public class Passenger implements Runnable{

    BusStation busStation;
    Bus bus;

    public Passenger(BusStation busStation, Bus bus) {
        this.busStation = busStation;
        this.bus = bus;
    }

    @Override
    public void run() {
        busStation.stop(this);
        while (!busStation.getBuses().contains(bus)){
            try {
                synchronized (busStation){
                    busStation.wait();
                }
            } catch (InterruptedException e){}
        }
        bus.enter();
        busStation.leave(this);
    }
}
