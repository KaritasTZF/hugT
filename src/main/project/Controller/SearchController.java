package project.Controller;

import project.Model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class SearchController {
    private String from = null; // flights only
    private String to = null; // flights only
    private LocalDate startDate= null;
    private LocalDate endDate= null;
    private int maxPrice= 0;
    private int people= 0;
    private int rooms = 0; //hotels only
    private String location= null; //hotels and dayTours

    private final FlightDB flightDB;
    private final HotelDB hotelDB;
    private final DayTourDB dayTourDB;

    // DFH- DB er þegar til, þurfum ekki að constructa nýtt alltaf
    public SearchController(FlightDB flightDB, HotelDB hotelDB, DayTourDB dayTourDB){
        this.flightDB = flightDB;
        this.hotelDB = hotelDB;
        this.dayTourDB = dayTourDB;
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
    public void setRooms(int rooms) {
        this.rooms = rooms;
    }
    public int getRooms() {
        return this.rooms;
    }

    //Leitar eftir flug í flightDB
    public ArrayList<Flight> findAvailableFlights() {
        ArrayList<Flight> flightDBList = flightDB.getFlightList();
        ArrayList<Flight> flightReturnList= new ArrayList<Flight>();

        for (Flight flight : flightDBList) {
            if (Objects.equals(flight.getFrom(), from)) {
                if (Objects.equals(flight.getTo(), to)) {
                    if (flight.getDate().isBefore(endDate) || flight.getDate().isEqual(endDate)) {
                        if (flight.getDate().isAfter(startDate) || flight.getDate().isEqual(startDate)) {
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

    //Leitar eftir hotel í hotelDB
    public ArrayList<Hotel> findAvailableHotels() {
        ArrayList<Hotel> hotelDBList = hotelDB.getHotelList();
        ArrayList<Hotel> hotelReturnList= new ArrayList<Hotel>();

        for (Hotel hotel : hotelDBList) {
            if (Objects.equals(hotel.getRooms(), rooms)) {
                if (Objects.equals(hotel.getLocation(), location)) {
                    if (Objects.equals(hotel.getStartDate(), startDate)) {
                        if (Objects.equals(hotel.getEndDate(), endDate)) {
                            if (hotel.getPrice() <= maxPrice) {
                                hotelReturnList.add(hotel);
                            }
                        }
                    }
                }
            }
        }
        return hotelReturnList;
    }

    //Leitar eftir dayTour í dayTourDB
    public ArrayList<DayTour> findAvailableDayTours() {
        ArrayList<DayTour> dayTourDBList = dayTourDB.getDayTourList();
        ArrayList<DayTour> dayTourReturnList= new ArrayList<DayTour>();

        for (DayTour dayTour : dayTourDBList) {
            if (Objects.equals(dayTour.getPeople(), people)) {
                if (Objects.equals(dayTour.getLocation(), location)) {
                    if (dayTour.getDate().isAfter(startDate) || dayTour.getDate().isEqual(startDate)) {
                        if (dayTour.getDate().isBefore(endDate) || dayTour.getDate().isEqual(endDate)) {
                            if (dayTour.getPrice() <= maxPrice) {
                                dayTourReturnList.add(dayTour);
                            }
                        }
                    }
                }
            }
        }
        return dayTourReturnList;
    }

}
