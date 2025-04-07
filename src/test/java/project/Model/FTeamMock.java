package project.Model;

import java.time.LocalDateTime;

public class FTeamMock {
    //Mock database values
    String[] to = {"Reykjavík", "Egilsstaðir", "Ísafjörður"};
    String[] from = {"Akureyri", "Ísafjörður", "Egilsstaðir"};
    int seatsAvailable = 50;
    LocalDateTime startDateTime = LocalDateTime.parse("2025-05-12T10:00:00");
    LocalDateTime endDateTime = LocalDateTime.parse("2025-05-14T10:23:59");
    int price = 24000; //in isk

    public FlightDB flightDB;

    //Constructor, creates a FlightDB object with above example data
    public FTeamMock() {
        flightDB = new FlightDB();

        for (int i = 0; i<3; i++) {
            flightDB.createFlight(to[i],from[i],null,seatsAvailable,null, null,price);
        }
    }
}
