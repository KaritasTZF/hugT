package project.Controller;

import project.Model.DayTour;
import java.time.LocalDate;
import java.util.ArrayList;

public class DayTourController {

    private final SearchController searchController;

    public DayTourController(SearchController searchController) {
        this.searchController = searchController;
    }

    /**
     * Leitar að dagferðum á tilteknum dagsetningum með tilgreindri staðsetningu og fjölda manna.
     *
     * @param startDate Upphafsdagsetning leitarinnar
     * @param endDate Lokadagsetning leitarinnar
     * @param location Staðsetning sem notandi vill leita eftir
     * @param people Fjöldi manna sem á að mæta
     * @return Listi af fundnum DayTour hlutum
     */
    public ArrayList<DayTour> searchDayTours(LocalDate startDate, LocalDate endDate, String location, int people) {
        // Setjum upp leitarskilyrði í SearchController
        searchController.setStartDate(startDate);
        searchController.setEndDate(endDate);
        searchController.setLocation(location);
        searchController.setPeople(people);

        // Köllum á aðferðina í SearchController sem leitar að dagferðum
        return searchController.findAvailableDayTours();
    }
}
