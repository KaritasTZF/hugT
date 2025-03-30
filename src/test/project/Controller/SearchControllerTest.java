package project.Controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.Model.Flight;
import project.Model.FlightDB;

import java.util.ArrayList;

public class SearchControllerTest {
    SearchController sc;
    FlightDB flightDB;

    @BeforeEach
    void setUp() {
        flightDB = new FlightDB();
        sc = new SearchController(flightDB);
    }

    @AfterEach
    void tearDown() {
        sc = null;
        flightDB = null;
    }

    @Test
    void testFlightSetter() {
        sc.setFrom("Reykjavík");
        ArrayList<Flight> flights = new ArrayList<Flight>();
        flights = sc.findAvailableFlights();

        for (int i = 0; i< flights.size();i++) {
            if (flights.get(i).getFrom()!="Reykjavík") {
                System.out.println(flights.get(i).getFrom());
            }
          }
    }
}
