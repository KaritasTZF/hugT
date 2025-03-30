package project.Controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.Model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class SearchControllerTest {
    SearchController sc;
    FlightDB flightDB;

    @BeforeEach
    void setUp() {
        FTeamMock fteam = new FTeamMock();
        FlightDB flightDB = fteam.flightDB;
        //FlightDB object with example data stored in FTeamMock
        // null values for hotelDB and dayTourDB
        sc = new SearchController(flightDB,null,null);
    }

    @AfterEach
    void tearDown() {
        sc = null;
        flightDB = null;
    }

    // FlightFindParameters:
    // assert that all flights returned have the searched-for parameters
    @Test
    void testFlightFindParametersTo() {
        sc.setTo("Reykjavík");
        ArrayList<Flight> flights = sc.findAvailableFlights();

        for (Flight flight : flights) {
            assertEquals(flight.getTo(), "Reykjavík");
        }
    }

    @Test
    void testFlightFindParametersFrom() {
        sc.setFrom("Akureyri");
        ArrayList<Flight> flights = sc.findAvailableFlights();

        for (Flight flight : flights) {
            assertEquals(flight.getFrom(), "Akureyri");
        }
    }

    @Test
    void testFlightFindParametersPeople() {
        sc.setPeople(2);
        ArrayList<Flight> flights = sc.findAvailableFlights();

        for (Flight flight : flights) {
            assertTrue(flight.getavailableSeats()>= 2);
        }
    }

    @Test
    void testFlightFindParametersStartDate() {
        LocalDate date = LocalDate.parse("2025-05-12");
        sc.setStartDate(date);
        ArrayList<Flight> flights = sc.findAvailableFlights();

        for (Flight flight : flights) {
            assertTrue(flight.getDate().isEqual(date) || flight.getDate().isAfter(date));
        }
    }

    @Test
    void testFlightFindParametersEndDate() {
        LocalDate date = LocalDate.parse("2025-05-14");
        sc.setEndDate(date);
        ArrayList<Flight> flights = sc.findAvailableFlights();

        for (Flight flight : flights) {
            assertTrue(flight.getDate().isEqual(date) || flight.getDate().isBefore(date));
        }
    }

    @Test
    void testFlightFindParametersMaxPrice() {
        sc.setMaxPrice(100000);
        ArrayList<Flight> flights = sc.findAvailableFlights();

        for (Flight flight : flights) {
            assertTrue(flight.getPrice()>=100000);
        }
    }

    // testFlightFindAll:
    // assert that there does not exist a flight with searched-for parameters
    // that was not returned
    @Test
    void testFlightFindAllTo() {
        sc.setTo("Reykjavík");
        ArrayList<Flight> returnedFlights = sc.findAvailableFlights();

        for (Flight flight : flightDB.getFlightList()) {
            if (Objects.equals(flight.getTo(), "Reykjavík")) {
                assertTrue(returnedFlights.contains(flight));
            }
        }
    }

    @Test
    void testFlightFindAllFrom() {
        sc.setFrom("Akureyri");
        ArrayList<Flight> returnedFlights = sc.findAvailableFlights();

        for (Flight flight : flightDB.getFlightList()) {
            if (Objects.equals(flight.getFrom(), "Akureyri")) {
                assertTrue(returnedFlights.contains(flight));
            }
        }
    }

    @Test
    void testFlightFindAllPeople() {
        sc.setPeople(20);
        ArrayList<Flight> returnedFlights = sc.findAvailableFlights();

        for (Flight flight : flightDB.getFlightList()) {
            if (flight.getavailableSeats() >= 20) {
                assertTrue(returnedFlights.contains(flight));
            }
        }
    }

    @Test
    void testFlightFindAllStartDate() {
        LocalDate date = LocalDate.parse("2025-05-12");
        sc.setStartDate(date);
        ArrayList<Flight> returnedFlights = sc.findAvailableFlights();

        for (Flight flight : flightDB.getFlightList()) {
            if (flight.getDate().isEqual(date) || flight.getDate().isAfter(date)) {
                assertTrue(returnedFlights.contains(flight));
            }
        }
    }

    @Test
    void testFlightFindAllEndDate() {
        LocalDate date = LocalDate.parse("2025-05-14");
        sc.setEndDate(date);
        ArrayList<Flight> returnedFlights = sc.findAvailableFlights();

        for (Flight flight : flightDB.getFlightList()) {
            if (flight.getDate().isEqual(date) || flight.getDate().isBefore(date)) {
                assertTrue(returnedFlights.contains(flight));
            }
        }
    }

    @Test
    void testFlightFindAllMaxPrice() {
        sc.setMaxPrice(100000);
        ArrayList<Flight> returnedFlights = sc.findAvailableFlights();

        for (Flight flight : flightDB.getFlightList()) {
            if (flight.getPrice() <= 100000) {
                assertTrue(returnedFlights.contains(flight));
            }
        }
    }

    //Test getters and setters
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
