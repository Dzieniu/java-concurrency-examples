package buses;

import java.util.ArrayList;

public class BusStation {

    String stationName;
    ArrayList<Bus> buses = new ArrayList<>();
    ArrayList<Passenger> passengers = new ArrayList<>();

    public BusStation(String stationName) {
        this.stationName = stationName;
    }

    public void stop(Bus bus){
        buses.add(bus);
        System.out.println("Przyjechal autobus nr: " + bus.busNumber);
    }

    public void stop(Passenger passenger){
        passengers.add(passenger);
        System.out.println("Nowy pasażer czeka na autobus nr: " + passenger.bus.busNumber);
    }

    public void leave(Bus bus){
        buses.remove(bus);
    }

    public void leave(Passenger passenger){
        passengers.remove(passenger);
    }

    public ArrayList<Bus> getBuses() {
        return buses;
    }

    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }
}
