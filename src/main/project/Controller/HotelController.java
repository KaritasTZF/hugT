package project.Controller;

import project.Model.Hotel;

import java.time.LocalDate;
import java.util.ArrayList;

public class HotelController {

    private final SearchController searchController;

    public HotelController(SearchController searchController) {
        this.searchController = searchController;
    }

    public ArrayList<Hotel> searchHotel(LocalDate startDate, LocalDate endDate, String location, int people) {
        // Kallar á aðferðina í SearchController sem sækir hótel
        searchController.setStartDate(startDate);
        searchController.setEndDate(endDate);
        searchController.setLocation(location);
        searchController.setPeople(people);
        return searchController.findAvailableHotels();
    }
}
