package project.Controller;

import project.Model.Flight;

import java.time.LocalDate;
import java.util.ArrayList;

public class FlightController {

    private final SearchController searchController;

    // fær SearchController til að leita að flugum
    public FlightController(SearchController searchController) {
        this.searchController = searchController;
    }

    //searchFlight(date, departure, arrival, people): List<Flight>
    //Kallar á SearchController til að sækja tiltæk flug.

    public ArrayList<Flight> searchFlight(LocalDate startDate,LocalDate endDate, String departure, String arrival, int people) {
        searchController.setFrom(departure);
        searchController.setTo(arrival);
        searchController.setStartDate(startDate);
        searchController.setEndDate(endDate);
        searchController.setPeople(people);
        // Kallar á SearchController-aðgerðina sem leitar að flugum
        return searchController.findAvailableFlights();
    }
}
