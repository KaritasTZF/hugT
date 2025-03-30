package project.Model;

import java.time.LocalDate;

public class FTeamMock {
    //Mock database values
    String[] to = {"Reykjavík", "Egilsstaðir", "Ísafjörður"};
    String[] from = {"Akureyri", "Ísafjörður", "Egilsstaðir"};
    int seatsAvailable = 50;
    int days = 2;
    LocalDate startDate = LocalDate.parse("2025-05-12");
    LocalDate endDate = LocalDate.parse("2025-05-14");
    int maxPrice = 24000; //in isk
    String location = "Akureyri";

    public FlightDB flightDB;

    //Constructor, creates a FlightDB object with above example data
    public FTeamMock() {
        flightDB = new FlightDB();

        for (int i = 0; i<3; i++) {
            flightDB.createFlight(to[i],from[i],seatsAvailable,days,startDate,endDate,maxPrice,location);
        }
    }
}
