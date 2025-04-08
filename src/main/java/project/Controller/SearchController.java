package project.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import project.Model.*;
import project.ui.FlightItem;
import project.ui.HotelItem;
import project.ui.DayTourItem;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;


public class SearchController {

    //Changing fxml elements
    @FXML private Button checkoutButton;
    @FXML private Label ResultLabel;
    @FXML private ComboBox<String> fromField;
    @FXML private ComboBox<String> toField;
    @FXML private Slider priceField;
    @FXML private ComboBox<Integer> peopleField;
    @FXML private ComboBox<Integer> roomsField;
    @FXML private DatePicker startDateField;
    @FXML private DatePicker endDateField;
    @FXML private ListView<HBox> ResultsListView;
    @FXML private Label searchMesageLabel;          //for error message to fill in fields


    //Search parameters
    private String from;            // flights only
    private String location;        // virkar sem to fyrir flight
    private LocalDate startDate;    // ATH 4H tekur bara 3 checkIn dagsetningsar, sjá Readme þeirra
    private LocalDate endDate;      //
    private int maxPrice;           // D&H; 4F er ekki með verð
    private int people;             // Flights only; 4D notar ekki fjöldi manns
    private int rooms;              // hotels only


    private final FlightDB flightDB;
    private final HotelDB hotelDB;
    private final DayTourDB dayTourDB;
    private Status status = Status.FROMFLIGHT;
    private Trip myTrip;

    public SearchController() {
        this.flightDB = new FlightDB();
        this.hotelDB = new HotelDB();
        this.dayTourDB = new DayTourDB();
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
    public ArrayList<Flight> findAvailableFlights(String fromLoc, String toLoc, LocalDate date) {
//        ArrayList<Flight> flightDBList = flightDB.getFlightList();
//        ArrayList<Flight> flightReturnList= new ArrayList<>();
//
//        for (Flight flight : flightDBList) {
//            if (Objects.equals(flight.getFrom(), from)) {
//                if (Objects.equals(flight.getTo(), location)) {
//                    if (flight.getStartDateTime().isBefore(endDate.atTime(23,59)) || flight.getStartDateTime().isEqual(endDate.atTime(23,59))) {
//                        if (flight.getEndDateTime().isAfter(startDate.atStartOfDay()) || flight.getEndDateTime().isEqual(startDate.atStartOfDay())) {
//                            if (flight.getavailableSeats() >= people) {
//                                if (flight.getPrice() <= maxPrice) {
//                                    flightReturnList.add(flight);
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
        return flightDB.getFlightList(fromLoc, toLoc, date);
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
        ResultsListView.getItems().clear();
        switch(status) {
            case Status.FROMFLIGHT:
                updateFrom();
                updateTo();
                updateStartDate();
                if (from != null && location != null && startDate != null) {
                    ArrayList<Flight> flightsArrayList = findAvailableFlights(from,location,startDate);
                    for (Flight flight: flightsArrayList) {
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/ui/FlightItem.fxml"));
                            Parent flightItem =loader.load();
                            FlightItem controller =loader.getController();
                            controller.setData(flight);
                            ResultsListView.getItems().add((HBox)flightItem);

                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                break;
            case Status.TOFLIGHT:
                updateFrom();
                updateTo();
                updateEndDate();
                if (from != null && location != null && endDate != null) {
                    ArrayList<Flight> flightsArrayList = findAvailableFlights(location,from,endDate);
                    for (Flight flight: flightsArrayList) {
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/ui/FlightItem.fxml"));
                            Parent flightItem =loader.load();
                            FlightItem controller =loader.getController();
                            controller.setData(flight);
                            ResultsListView.getItems().add((HBox)flightItem);

                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                break;
            case Status.HOTEL:
                updateTo();
                updateStartDate();
                updateEndDate();
                updatePeople();
                updatePrice();
                // tjekka að leit sé ekki alveg tóm. people&price hafa default gildi.
                if (location != null && endDate != null && startDate != null) {
                    ArrayList<Hotel> hotelsArrayList = findAvailableHotels();
                    for (Hotel hotel: hotelsArrayList) {
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/ui/HotelItem.fxml"));
                            Parent hotelItem =loader.load();
                            HotelItem controller =loader.getController();
                            controller.setData(hotel);
                            ResultsListView.getItems().add((HBox)hotelItem);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                break;
            case Status.DAYTOUR:
                updateStartDate();
                updateEndDate();
                updatePrice();
                updateTo();
                if (location != null && endDate != null && startDate != null) {
                    ArrayList<DayTour> dayToursArrayList = findAvailableDayTours();
                    for (DayTour dayTour: dayToursArrayList) {
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/ui/DayTourItem.fxml"));
                            Parent dayTourItem =loader.load();
                            DayTourItem controller =loader.getController();
                            controller.setData(dayTour);
                            ResultsListView.getItems().add((HBox)dayTourItem);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                break;
        }
    }

    //Input TextFields
    public void updateFrom() {setFrom(fromField.getValue());}
    public void updateTo() {setLocation(toField.getValue());}
    public void updatePrice() {setMaxPrice((int) priceField.getValue());}
    public void updatePeople() {setPeople((int) peopleField.getValue());}
    public void updateStartDate() {setStartDate(startDateField.getValue());}
    public void updateEndDate() {setEndDate(endDateField.getValue());}


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

    private void updateStatus(Status newStatus) {
        //change Input fields, ResultsTable, ResultsLabel
    }

    public void skipStatus() {
        //case status, veldur næsta og kallar á updateStatus
    }

    public void addToMyTrip() {
        //finnur hvað er valið í ResultsListView og setur það inn í Trip item
    }
}
