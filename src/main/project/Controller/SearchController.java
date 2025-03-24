package project.Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import project.Model.*;

public class SearchController {
    private String from;
    private String to;
    private LocalDate startDate;
    private LocalDate endDate;
    private double maxPrice;
    private int people;
    private String location;

    // Spurning hvort þetta sé ekki rangt hjá mér. Þarf að skoða betur.

    public SearchController(){
    }

    //setterar og getterar
    public void setFrom(String from) {
      this.from = from;
    }
    public String getFrom() {
      return this.from;
    }

    //
    public ArrayList<Flight> findAvailableFlights() {
      FlightDBMock flightDB = new FlightDBMock();
      ArrayList<Flight> flightDBList = flightDB.getFlightList();
      ArrayList<Flight> flightReturnList= new ArrayList<Flight>();

      for (int i = 0; i< flightDBList.size();i++) {
        if (flightDBList.get(i).getFrom()==from) {
          // +if fyrir hvert parameter
          flightReturnList.add(flightDBList.get(i));
        }
      }
      return flightReturnList;
    }

}
