package project.Controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.Model.*;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchControllerTest {
    SearchController sc;
    FlightDB flightDB;

    @BeforeEach
    void setUp() {
        FTeamMock fteam = new FTeamMock();
        FlightDB flightDB = fteam.flightDB;
        sc = new SearchController(flightDB);
    }

    @AfterEach
    void tearDown() {
        sc = null;
        flightDB = null;
    }

    @Test
    void testFlightFinder() {
        sc.setFrom("Reykjavík");
        ArrayList<Flight> flights = new ArrayList<Flight>();
        flights = sc.findAvailableFlights();

        for (Flight flight : flights) {
            assertEquals(flight.getFrom(), "Reykjavík");
        }
    }

    @Test
    void testSetGetFrom() {
        sc.setFrom("test");
        assertEquals(sc.getFrom(),"test");
    }

    @Test
    void testSetGetTo() {
        sc.setTo("test");
        assertEquals(sc.getTo(),"test");
    }

    @Test
    void testSetGetStartDate() {
        sc.setStartDate(LocalDate.parse("2001-11-22"));
        assertEquals(sc.getStartDate(),LocalDate.parse("2001-11-22"));
    }

    @Test
    void testSetGetEndDate() {
        sc.setEndDate(LocalDate.parse("2001-11-22"));
        assertEquals(sc.getEndDate(),LocalDate.parse("2001-11-22"));
    }

    @Test
    void testSetGetMaxPrice() {
        sc.setMaxPrice(1);
        assertEquals(sc.getMaxPrice(),1);
    }

    @Test
    void testSetGetPeople() {
        sc.setPeople(100);
        assertEquals(sc.getPeople(),100);
    }

    @Test
    void testSetGetLocation() {
        sc.setLocation("test");
        assertEquals(sc.getLocation(),"test");
    }
}
