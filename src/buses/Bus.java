package buses;

import java.util.Random;

public class Bus implements Runnable{

    String busNumber;
    BusStation busStation;
    int frequency;
    int passengers = 0;

    public Bus(String busNumber, BusStation busStation, int frequency) {
        this.busNumber = busNumber;
        this.busStation = busStation;
        this.frequency = frequency;
    }

    @Override
    public void run() {
        generatePassengers();
        while (true){
            try {
                Thread.sleep(1000 * frequency);
                busStation.stop(this);
                passengers = 0;
                synchronized (busStation){
                    busStation.notifyAll();
                }
                Thread.sleep(2000);
                busStation.leave(this);
                System.out.println("Autobus nr." + busNumber + " opuszcza "
                        + busStation.stationName + " z " + passengers
                        + " pasazerami");
            } catch (InterruptedException e){}
        }
    }

    public void enter(){
        passengers++;
    }

    private void generatePassengers(){
        Random randomGenerator = new Random();
        Thread busPassengers = new Thread(() -> {
            while (true){
                try {
                    new Thread(new Passenger(busStation, this)).start();
                    Thread.sleep(1000 * randomGenerator.nextInt(3) + 1000);
                } catch (InterruptedException e){}
            }
        });
        busPassengers.start();
    }
}
