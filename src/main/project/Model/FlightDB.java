package project.Model;

import java.time.LocalDate;
import java.util.ArrayList;

public class FlightDB {
    private ArrayList<Flight> flightList;

    public FlightDB() {
    }

    public void createFlight(String flugdataTo, String flugdataFrom, int flugdataMaxPeople, int flugdataDays, LocalDate flugdataStart, LocalDate flugdataEnd, int flugdataMaxPrice, String flugdataLoc) {
        flightList.add(new Flight(flugdataTo,flugdataFrom,flugdataMaxPeople,flugdataDays,flugdataStart,flugdataEnd,flugdataMaxPrice,flugdataLoc));
    }

    public ArrayList<Flight> getFlightList(){
        return this.flightList;
    }

}
