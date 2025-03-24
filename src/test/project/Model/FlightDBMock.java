package project.Model;

import java.time.LocalDate;
import java.util.ArrayList;

public class FlightDBMock {
    String[] flugdataTo = {"Reykjavík", "Egilsstaðir", "Ísafjörður"};
    String flugdataFrom = "Akureyri";
    int flugdataMaxPeople = 2;
    int flugdataDays = 2;
    LocalDate flugdataStart = LocalDate.parse("2025-05-12");
    LocalDate flugdataEnd = LocalDate.parse("2025-05-14");
    double flugdataMaxPrice = 2.4;
    String flugdataLoc = "Akureryri";

    private ArrayList<Flight> flightList;

    //Constructor
    public FlightDBMock() {
        for (int i = 0; i<3; i++) {
            createFlight(i);
        }
    }

    //TODO leita eftir MaxPeople, flight object heldur (raun) people fjöldi
    public void createFlight(int i) {
        flightList.add(new Flight(flugdataTo[i],flugdataFrom,flugdataMaxPeople,flugdataDays,flugdataStart,flugdataEnd,flugdataPrice,flugdataLoc));
    }

    public ArrayList<Flight> getFlightList(){
        return this.flightList;
    }
}
