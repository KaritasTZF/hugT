package project.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import project.Model.*;
import project.ui.CheckoutController;
import project.ui.DayTourItem;
import project.ui.FlightItem;
import project.ui.HotelItem;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;


public class SearchController {

    //Changing fxml elements
    @FXML private Button checkoutButton;
    @FXML private Button skipButton;
    @FXML private Label ResultLabel=new Label("Flights: ");
    @FXML private Label fromLabel;
    @FXML private Label toLabel;
    @FXML private Label startDateLabel;
    @FXML private Label endDateLabel;
    @FXML private Label priceLabel;
    @FXML private Label peopleLabel;
    @FXML private Label roomsLabel;
    @FXML private ComboBox<String> fromField;
    @FXML private ComboBox<String> toField;
    @FXML private Slider priceSlider;
    @FXML private ComboBox<Integer> peopleField;
    @FXML private ComboBox<Integer> roomsField;
    @FXML private DatePicker startDateField;
    @FXML private DatePicker endDateField;
    @FXML private GridPane gridPane;
    @FXML private ListView<HBox> ResultsListView;
    @FXML private ListView<HBox> MyTripListView;
    @FXML private Label totalPriceLabel;


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
    private final Trip myTrip=new Trip();
    private Flight selectedFItem;
    private Hotel selectedHItem;
    private DayTour selectedDTItem;

    public SearchController() {
        this.flightDB = new FlightDB();
        this.hotelDB = new HotelDB();
        this.dayTourDB = null;//ew DayTourDB();
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
        return hotelDB.getHotelList(this.location, this.startDate, this.endDate, this.people, this.rooms);
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

    //Input TextFields
    public void updateFrom() {setFrom(fromField.getValue());}
    public void updateTo() {setLocation(toField.getValue());}
    public void updatePrice() {setMaxPrice((int) priceSlider.getValue());}
    public void updatePeople() {setPeople(peopleField.getValue());}
    public void updateStartDate() {setStartDate(startDateField.getValue());}
    public void updateEndDate() {setEndDate(endDateField.getValue());}
    public void updateRooms() {setRooms(roomsField.getValue());}

    private enum Status {
        FROMFLIGHT, HOTEL, DAYTOUR, TOFLIGHT;
    }

    //helper to load flights onto list
    private void loadFlightsToList(ListView<HBox> listView, ArrayList<Flight> flightArrayList) {
        for (Flight flight: flightArrayList ) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/ui/FlightItem.fxml"));
                Parent flightItem = loader.load();
                FlightItem controller = loader.getController();
                controller.setData(flight);
                controller.setSc(this);
                listView.getItems().add((HBox) flightItem);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private void loadHotelsToList(ListView<HBox> listView, ArrayList<Hotel> hotelArrayList) {
        for (Hotel hotel: hotelArrayList) {
            System.out.println(hotel.getName());
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/ui/HotelItem.fxml"));
                Parent hotelItem = loader.load();
                HotelItem controller = loader.getController();
                controller.setData(hotel);
                controller.setSc(this);
                listView.getItems().add((HBox) hotelItem);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private void loadDayToursToList(ListView<HBox> listView, ArrayList<DayTour> dayTourArrayList) {
        for (DayTour dayTour: dayTourArrayList) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/ui/DayTourItem.fxml"));
                Parent dayTourItem =loader.load();
                DayTourItem controller =loader.getController();
                controller.setData(dayTour);
                controller.setSc(this);
                listView.getItems().add((HBox)dayTourItem);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //Helper, change Input fields, ResultsListView, ResultsLabel
    private void updateStatus() {
        ResultsListView.getItems().clear();
        selectedDTItem = null;
        selectedFItem = null;
        selectedHItem = null;
        switch (status) {
            case FROMFLIGHT:
                ResultLabel.setText("Return flights: ");
                startDateLabel.setVisible(false);
                startDateField.setVisible(false);
                gridPane.setRowIndex(fromField,1);
                gridPane.setRowIndex(toField,0);
                gridPane.setRowIndex(endDateLabel,2);
                gridPane.setRowIndex(endDateField,2);
                endDateLabel.setVisible(true);
                endDateField.setVisible(true);
                this.status = Status.TOFLIGHT;
                break;

            case TOFLIGHT:
                ResultLabel.setText("Hotels:");
                roomsField.setVisible(true);
                roomsLabel.setVisible(true);
                fromField.setVisible(false);
                toLabel.setVisible(false);
                fromLabel.setText("Location:");
                gridPane.setRowIndex(startDateLabel,1);
                gridPane.setRowIndex(startDateField,1);
                startDateField.setVisible(true);
                startDateLabel.setVisible(true);
                this.status = Status.HOTEL;
                break;

            case HOTEL:
                ResultLabel.setText("Day Tours:");
                roomsLabel.setVisible(false);
                roomsField.setVisible(false);
                skipButton.setVisible(false);
                checkoutButton.setVisible(true);
                this.status = Status.DAYTOUR;
                break;

            case DAYTOUR:
                System.out.println("error");
        }


    }

    //Helper, add items to MyTrip list
    public void updateMyTripList(){
        MyTripListView.getItems().clear();
        loadFlightsToList(MyTripListView, myTrip.getFlightItems());
        loadHotelsToList(MyTripListView,myTrip.getHotelItems());
        loadDayToursToList(MyTripListView,myTrip.getDayTourItems());
        totalPriceLabel.setText("Total Price: "+ myTrip.getPrice()+" kr.");
    }


    //Takka virkni

    //Search takkinn. Ákveður hvað að gera
    public void onSearch() {
        ResultsListView.getItems().clear();
        switch(status) {
            case Status.FROMFLIGHT:
                updateFrom();
                updateTo();
                updateStartDate();
                if (from != null && location != null && startDate != null) {
                    ArrayList<Flight> flightsArrayList = findAvailableFlights(from,location,startDate);
                    //TODO null checking
                    loadFlightsToList(ResultsListView,flightsArrayList);
                }
                break;
            case Status.TOFLIGHT:
                updateFrom();
                updateTo();
                updateEndDate();
                if (from != null && location != null && endDate != null && endDate.isAfter(startDate)) {
                    ArrayList<Flight> flightsArrayList = findAvailableFlights(location,from,endDate);
                    loadFlightsToList(ResultsListView,flightsArrayList);
                }
                break;
            case Status.HOTEL:
                updateTo();
                updateStartDate();
                updateEndDate();
                updatePeople();
                updatePrice();
                updateRooms();
                // tjekka að leit sé ekki alveg tóm. people&price hafa default gildi.
                if (location != null && endDate != null && startDate != null && endDate.isAfter(startDate)) {
                    ArrayList<Hotel> hotelsArrayList = findAvailableHotels();
                    loadHotelsToList(ResultsListView,hotelsArrayList);
                }
                break;
            case Status.DAYTOUR:
                updateStartDate();
                updateEndDate();
                updatePrice();
                updateTo();
                if (location != null && endDate != null && startDate != null && endDate.isAfter(startDate)) {
                    ArrayList<DayTour> dayToursArrayList = findAvailableDayTours();
                    loadDayToursToList(ResultsListView,dayToursArrayList);
                }
                break;
        }
    }

    //Skip takkinn. Kallar á updateStatus
    public void handleSkip() {
        updateStatus();
    }

    //Selection í Results listinn. Tekur við selected items
    public void handleFlightSelection(Flight flight) {selectedFItem = flight;}
    public void handleHotelSelection(Hotel hotel) {selectedHItem = hotel;}
    public void handleDayTourSelection(DayTour dayTour) {selectedDTItem = dayTour;}

    //Add takkinn. Finnur hvað er valið í ResultsListView og setur það inn í Trip item
    public void addToMyTrip() {
        switch (status) {
            case FROMFLIGHT, TOFLIGHT:
                myTrip.addFlightItem(selectedFItem);
                break;
            case HOTEL:
                myTrip.addHotelItem(selectedHItem);
                break;
            case DAYTOUR:
                myTrip.addDayTourItem(selectedDTItem);
                break;
        }
        updateMyTripList();
    }

    //Áfram takki
    public void goToCheckout() {
        if (MyTripListView.getItems() !=null){
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/ui/Checkout.fxml"));
                Parent root = loader.load();
                CheckoutController controller = loader.getController();
                controller.setTrip(myTrip);
                Stage stage = (Stage) fromField.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            }catch(Exception e){
                throw new RuntimeException(e);
            }
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
}
