package project.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import project.Model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class SearchController {
    @FXML TextField fromField;
    @FXML TextField toField;
    @FXML TextField priceField;
    @FXML TextField peopleField;
    @FXML TextField locationField;
    @FXML TextField roomsField;
    @FXML DatePicker startDateField;
    @FXML DatePicker endDateField;
    private String from = fromField.getText(); // flights only
    private String to = toField.getText(); // flights only
    private LocalDate startDate= startDateField.getValue();
    private LocalDate endDate= endDateField.getValue();
    private int maxPrice= Integer.parseInt(priceField.getText());
    private int people= Integer.parseInt(peopleField.getText());
    private int rooms = Integer.parseInt(roomsField.getText()); //hotels only
    private String location= locationField.getText(); //hotels and dayTours

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
                    if (flight.getStartDateTime().isBefore(endDate.atTime(23,59)) || flight.getStartDateTime().isEqual(endDate.atTime(23,59))) {
                        if (flight.getEndDateTime().isAfter(startDate.atStartOfDay()) || flight.getEndDateTime().isEqual(startDate.atStartOfDay())) {
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
