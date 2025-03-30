package project.Model;

import java.time.LocalDate;

public class FTeamMock {
    //Mock database values
    String[] flugdataTo = {"Reykjavík", "Egilsstaðir", "Ísafjörður"};
    String flugdataFrom = "Akureyri";
    int flugdataMaxPeople = 50;
    int flugdataDays = 2;
    LocalDate flugdataStart = LocalDate.parse("2025-05-12");
    LocalDate flugdataEnd = LocalDate.parse("2025-05-14");
    int flugdataMaxPrice = 24000;
    String flugdataLoc = "Akureyri";

    public FlightDB flightDB;

    //Constructor
    public FTeamMock() {
        flightDB = new FlightDB();
        for (int i = 0; i<3; i++) {
            flightDB.createFlight(flugdataTo[i],flugdataFrom,flugdataMaxPeople,flugdataDays,flugdataStart,flugdataEnd,flugdataMaxPrice,flugdataLoc);
        }
    }
}
