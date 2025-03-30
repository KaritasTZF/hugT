package project.Controller;

import project.Model.Flight;
import project.Model.FlightDB;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class SearchController {
    private String from = null;
    private String to = null;
    private LocalDate startDate= null;
    private LocalDate endDate= null;
    private int maxPrice= 0;
    private int people= 0;
    private String location= null;
    private FlightDB flightDB;

    // flightDB er þegar til, þurfum ekki að constructa nýtt alltaf
    public SearchController(FlightDB flightDB){
        this.flightDB = flightDB;
    }

    //setterar og getterar
    public void setFrom(String from) {
      this.from = from;
    }
    public String getFrom() {
      return this.from;
    }
    public void setTo(String to) {
        this.to = to;
    }
    public String getTo() {
        return this.to;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public LocalDate getStartDate() {
        return this.startDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    public LocalDate getEndDate() {
        return this.endDate;
    }
    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }
    public int getMaxPrice() {
        return this.maxPrice;
    }
    public void setPeople(int people) {
        this.people = people;
    }
    public int getPeople() {
        return this.people;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getLocation() {
        return this.location;
    }

    //Leitar eftir flug í flightDB
    public ArrayList<Flight> findAvailableFlights() {
      ArrayList<Flight> flightDBList = flightDB.getFlightList();
      ArrayList<Flight> flightReturnList= new ArrayList<Flight>();

      for (int i = 0; i< flightDBList.size();i++) {
          Flight flight = flightDBList.get(i);
          if (Objects.equals(flight.getFrom(), from)) {
             if (Objects.equals(flight.getTo(), to)) {
                if (Objects.equals(flight.getStartDate(), startDate)) {
                    if (Objects.equals(flight.getEndDate(), endDate)) {
                        if (flight.getavailableSeats() >= people) {
                            if (flight.getPrice() <= maxPrice) {
                                flightReturnList.add(flight);
                            }
                        }
                    }
                }
             }
          }
      }
      return flightReturnList;
    }

}
