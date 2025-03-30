package project.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class FlightDB {
    private final ArrayList<Flight> flightList;

    public FlightDB() {
        flightList = new ArrayList<>();
    }

    public void createFlight(String from, String to, int availableSeats, LocalDateTime startTime, LocalDateTime endTime, LocalDate date, int price) {
        flightList.add(new Flight(from,to, availableSeats,startTime,endTime, date,price));
    }

    public ArrayList<Flight> getFlightList(){
        return this.flightList;
    }

}
