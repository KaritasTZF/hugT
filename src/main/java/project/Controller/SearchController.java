package project.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import project.Model.*;
import project.ui.FlightItem;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;


public class SearchController {

    private static final ObservableList<String> Flocations = FXCollections.observableArrayList(
            "Keflavík",     //FH
            "Ísafjörður",   //FH
            "Reykjavík",    //FH
            "Akureyri",     //FH
            "Egilsstaðir",  //FH
            "Vestmannaeyjar",//FH
            "Selfoss",      //FH
            "Ólafsvík",     //F
            "Blönduós",     //F
            "Selfoss",      //F
            "Höfn í Hornafirði",//F
            "Vopnafjörður", //F
            "Vatnajökull"   //F
    );

    private static final ObservableList<String> Hlocations = FXCollections.observableArrayList(
            "Borganes",     //H
            "Keflavík",     //FH
            "Vík",          //H
            "Mývatnssveit", //H
            "Ísafjörður",   //FH
            "Sauðárkrókur", //H
            "Flúðir",       //H
            "Grindavík",    //H
            "Snæfellsnes",  //H
            "Hvolsvöllur",  //H
            "Siglufjörður", //H
            "Reykjavík",    //FH
            "Akureyri",     //FH
            "Egilsstaðir",  //FH
            "Vestmannaeyjar",//FH
            "Selfoss"      );

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

    public SearchController() {
        this.flightDB = new FlightDB();
        this.hotelDB = new HotelDB();
        this.dayTourDB = new DayTourDB();
        initComboBox();
    }

    public void initComboBox() {
        ObservableList<String> options = FXCollections.observableArrayList("Reykjavík","Akureyri");
        final ComboBox testBox = new ComboBox(options);
        fromField = new ComboBox(Flocations);
        //fromField.getItems(Flocations);
        toField = new ComboBox(Flocations);
        fromField.setPromptText("Depart from");
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
        System.out.println("startDate set: "+startDate);
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
        ArrayList<DayTour> dayTourReturnList = new ArrayList<>();

        for (DayTour dayTour : dayTourDBList) {
            // Filter eftir staðsetningu: Athuga hvort dayTour location sé sú sama (óháð stórum/lágu stöfum)
            if (!dayTour.getLocation().equalsIgnoreCase(location)) {
                continue;
            }
            // Filtera eftir dagsetningu: dayTour verður að vera á eða eftir startDate og á eða fyrir endDate
            if (!(dayTour.getDate().isAfter(startDate) || dayTour.getDate().isEqual(startDate))) {
                continue;
            }
            if (!(dayTour.getDate().isBefore(endDate) || dayTour.getDate().isEqual(endDate))) {
                continue;
            }
            // Filtera eftir verði: dayTour verð verður að vera minni eða jafnt og maxPrice
            if (dayTour.getPrice() <= maxPrice) {
                continue;
            }
            // Ef allar skilyrði eru uppfyllt, bæta dayTour við í niðurstöðulistann
            dayTourReturnList.add(dayTour);
        }
        return dayTourReturnList;
    }


    //Takka virkni

    //Ákveður hvað að gera
    public void onSearch() {
        System.out.println("searching...");
        ResultsListView.getItems().removeAll();
        switch(status) {
            case Status.FROMFLIGHT:
                System.out.println("searching Flights");
                updateFrom();
                updateTo();
                updateStartDate();
                if (from != null && location != null && startDate != null) {
                    ArrayList<Flight> flightsArrayList = findAvailableFlights(from,location,startDate);
                    System.out.println("created list");
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
}
