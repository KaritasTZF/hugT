package project.Controller;
import project.Model.Hotel;
import java.util.Date;
import java.util.List;

public class HotelController {

    private final SearchController searchController;

    public HotelController(SearchController searchController) {
        this.searchController = searchController;
    }

    public List<Hotel> searchHotel(Date date, String location, int people) {
        // Kallar á aðferðina í SearchController sem sækir hótel
        List<Hotel> hotels = searchController.findAvailableHotels(date, location, people);
        // Hér vantar kannski frekari úrvinnslu, átta mig ekki alveg á því
        return hotels;
    }
}
