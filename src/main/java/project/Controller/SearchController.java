package project.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import project.Model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class SearchController {
    //Changing fxml elements
    @FXML private Button checkoutButton;
    @FXML private Label SearchResults;
    @FXML private TextField fromField;
    @FXML private TextField toField;
    @FXML private TextField priceField;
    @FXML private TextField peopleField;
    @FXML private TextField locationField;
    @FXML private TextField roomsField;
    @FXML private DatePicker startDateField;
    @FXML private DatePicker endDateField;

    //Search parameters
    private String from;// = fromField.getText(); // flights only
    private String location;// virkar sem to fyrir flight
    private LocalDate startDate;//= startDateField.getValue();
    private LocalDate endDate;//= endDateField.getValue();
    private int maxPrice;//= Integer.parseInt(priceField.getText());
    private int people;//= Integer.parseInt(peopleField.getText());
    private int rooms;// = Integer.parseInt(roomsField.getText()); //hotels only

    private final FlightDB flightDB;
    private final HotelDB hotelDB;
    private final DayTourDB dayTourDB;
    private Status status = Status.FROMFLIGHT;

    public SearchController() {
        this.flightDB = new FlightDB();
        this.hotelDB = new HotelDB(); //initializes en leitar ekki strax
        this.dayTourDB = new DayTourDB(); // leitar strax
    }

    //setterar og getterar
    public void setFrom(String from) {
      this.from = from;
    }
    public String getFrom() {
      return this.from;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getLocation() {
        return this.location;
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
    public void setRooms(int rooms) {
        this.rooms = rooms;
    }
    public int getRooms() {
        return this.rooms;
    }

    //Searching methods

    //Leitar eftir flug í flightDB
    public ArrayList<Flight> findAvailableFlights() {
        ArrayList<Flight> flightDBList = flightDB.getFlightList();
        ArrayList<Flight> flightReturnList= new ArrayList<>();

        for (Flight flight : flightDBList) {
            if (Objects.equals(flight.getFrom(), from)) {
                if (Objects.equals(flight.getTo(), location)) {
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
        //4H skilar bara sorteðan lista.
//        ArrayList<Hotel> hotelDBList = hotelDB.getHotelList();
//        ArrayList<Hotel> hotelReturnList= new ArrayList<>();
//
//        for (Hotel hotel : hotelDBList) {
//            if (Objects.equals(hotel.getRooms(), rooms)) {
//                if (Objects.equals(hotel.getStartDate(), startDate)) {
//                    if (Objects.equals(hotel.getEndDate(), endDate)) {
//                        if (hotel.getPrice() <= maxPrice) {
//                            hotelReturnList.add(hotel);
//                        }
//                    }
//                }
//            }
//        }
        return hotelDB.getHotelList(this.location, this.startDate, this.endDate, this.people, this.maxPrice);
    }

    //Leitar eftir dayTour í dayTourDB
    public ArrayList<DayTour> findAvailableDayTours() {
        ArrayList<DayTour> dayTourDBList = dayTourDB.getDayTourList();
        ArrayList<DayTour> dayTourReturnList= new ArrayList<>();

        for (DayTour dayTour : dayTourDBList) {
            if (Objects.equals(dayTour.getPeople(), people)) {
                if (dayTour.getDate().isAfter(startDate) || dayTour.getDate().isEqual(startDate)) {
                    if (dayTour.getDate().isBefore(endDate) || dayTour.getDate().isEqual(endDate)) {
                        if (dayTour.getPrice() <= maxPrice) {
                            dayTourReturnList.add(dayTour);
                        }
                    }
                }
            }
        }
        return dayTourReturnList;
    }

    //Takka virkni

    //Ákveður hvað að gera
    public void onSearch() {
        switch(status) {
            case Status.FROMFLIGHT:
                //
                break;
            case Status.TOFLIGHT:
                //
                break;
            case Status.HOTEL:
                //
                break;
            case Status.DAYTOUR:
                //
                break;
        }
    }
    //Back takki
    public void goToWelcome() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/ui/Welcome.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) fromField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    private enum Status {
        FROMFLIGHT, HOTEL, DAYTOUR, TOFLIGHT;
    }
}
