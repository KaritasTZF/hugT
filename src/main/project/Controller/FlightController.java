package project.Controller;

import project.Model.Flight;
import java.util.Date;
import java.util.List;

public class FlightController {

    private final SearchController searchController;

    // fær SearchController til að leita að flugum
    public FlightController(SearchController searchController) {
        this.searchController = searchController;
    }

    //searchFlight(date, departure, arrival, people): List<Flight>
    //Kallar á SearchController til að sækja tiltæk flug.

    public List<Flight> searchFlight(Date date, String departure, String arrival, int people) {
        // Kallar á SearchController-aðgerðina
        List<Flight> flights = searchController.findAvailableFlights(date, departure, arrival, people);
        // Fæ villu fyrir parametrana - Þarf að laga!
        return flights;
    }
}
