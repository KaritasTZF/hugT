package Controller;

import java.time.LocalDate;

public class SearchController {
    private String from;
    private String to;
    private LocalDate startDate;
    private LocalDate endDate;
    private double maxPrice;
    private int people;
    private String location;

    // Spurning hvort þetta sé ekki rangt hjá mér. Þarf að skoða betur.

    public SearchController(
      String from, 
      String to, 
      LocalDate startDate, 
      LocalDate endDate, 
      double maxPrice, 
      int people, 
      String location){
        this.from = from;
        this.to = to;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxPrice = maxPrice;
        this.people = people;
        this.location = location;

    }

}
