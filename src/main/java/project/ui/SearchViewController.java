package project.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import project.Controller.BookingController;
import project.Controller.SearchController;
import project.Model.DayTour;
import project.Model.Flight;
import project.Model.Hotel;
import project.Model.User;
import project.ui.listItems.DayTourItem;
import project.ui.listItems.FlightItem;
import project.ui.listItems.HotelItem;

import java.io.IOException;
import java.util.ArrayList;

public class SearchViewController {

    //Changing fxml elements
    @FXML
    private Button checkoutButton;
    @FXML private Button addToFavButton;
    @FXML private Button skipButton;
    @FXML private Label ResultLabel=new Label("Flights: Departure - Arrival, Date, Time, Price");
    @FXML private Label fromLabel;
    @FXML private Label toLabel;
    @FXML private Label startDateLabel;
    @FXML private Label endDateLabel;
    @FXML private Label roomsLabel;
    @FXML private ComboBox<String> fromField;
    @FXML private ComboBox<String> toField;
    @FXML private Slider priceSlider;
    @FXML private ComboBox<Integer> peopleField=new ComboBox<>();
    @FXML private ComboBox<Integer> roomsField=new ComboBox<>();
    @FXML private DatePicker startDateField;
    @FXML private DatePicker endDateField;
    @FXML private ListView<HBox> ResultsListView;

    @FXML private ListView<HBox> MyTripListView;
    @FXML private Label totalPriceLabel;
    @FXML private HBox sliderHBox;
    @FXML private Label priceDisplay;
    @FXML private Label reqLabel;

    private User user;

    private Flight selectedFItem;
    private Hotel selectedHItem;
    private DayTour selectedDTItem;

    private final SearchController sc = new SearchController(this);

    public SearchViewController() {
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ListView<HBox> getResultsListView() {
        return ResultsListView;
    }



    //Input TextFields
    public void updateFrom() {sc.setFrom(fromField.getValue());}
    public void updateTo() {sc.setLocation(toField.getValue());}
    public void updatePrice() {sc.setMaxPrice((int) priceSlider.getValue());}
    public void updatePeople() {sc.setPeople(peopleField.getValue());}
    public void updateStartDate() {sc.setStartDate(startDateField.getValue());}
    public void updateEndDate() {sc.setEndDate(endDateField.getValue());}
    public void updateRooms() {sc.setRooms(roomsField.getValue());}
    public void updateAllParams() {
        updateFrom();
        updateTo();
        updatePrice();
        updatePeople();
        updateStartDate();
        updateEndDate();
        updateRooms();
    }

    // ----------------------------------------------------------------------------------------------------------------

    // to load flights onto list
    public void loadFlightsToList(ListView<HBox> listView, ArrayList<Flight> flightArrayList) {
        for (Flight flight: flightArrayList ) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/ui/listItems/FlightItem.fxml"));
                Parent flightItem = loader.load();
                FlightItem controller = loader.getController();
                controller.setData(flight);
                controller.setView(this);
                listView.getItems().add((HBox) flightItem);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void loadHotelsToList(ListView<HBox> listView, ArrayList<Hotel> hotelArrayList) {
        for (Hotel hotel: hotelArrayList) {
            System.out.println(hotel.getName());
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/ui/listItems/HotelItem.fxml"));
                Parent hotelItem = loader.load();
                HotelItem controller = loader.getController();
                controller.setData(hotel);
                controller.setView(this);
                listView.getItems().add((HBox) hotelItem);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void loadDayToursToList(ListView<HBox> listView, ArrayList<DayTour> dayTourArrayList) {
        for (DayTour dayTour: dayTourArrayList) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/ui/listItems/DayTourItem.fxml"));
                Parent dayTourItem =loader.load();
                DayTourItem controller =loader.getController();
                controller.setData(dayTour);
                controller.setView(this);
                listView.getItems().add((HBox)dayTourItem);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //Selection í Results listinn. Tekur við selected items
    public void handleFlightSelection(Flight flight) {selectedFItem = flight;}
    public void handleHotelSelection(Hotel hotel) {selectedHItem = hotel;}
    public void handleDayTourSelection(DayTour dayTour) {selectedDTItem = dayTour;}

    // ----------------------------------------------------------------------------------------------------------------
    // Hjálparföll fyrir display dót, þeas listar og birta/sýna réttu inputs osfv

    //Helper, change Input fields, ResultsListView, ResultsLabel
    private void updateStatus() {
        ResultsListView.getItems().clear();
        selectedDTItem = null;
        selectedFItem = null;
        selectedHItem = null;
        switch (sc.getStatus()) {
            case FROMFLIGHT:
                setResultsLabel("Return flights: ");
                startDateLabel.setVisible(false);
                startDateField.setVisible(false);
                GridPane.setRowIndex(fromField,1);
                GridPane.setRowIndex(toField,0);
                GridPane.setRowIndex(endDateLabel,2);
                GridPane.setRowIndex(endDateField,2);
                endDateLabel.setVisible(true);
                endDateField.setVisible(true);
                updateSliderMax(20000);
                sc.setStatus(SearchController.Status.TOFLIGHT);
                break;

            case TOFLIGHT:
                setResultsLabel("Hotels:");
                roomsField.setVisible(true);
                roomsLabel.setVisible(true);
                fromField.setVisible(false);
                toLabel.setVisible(false);
                fromLabel.setText("Location:");
                GridPane.setRowIndex(startDateLabel,1);
                GridPane.setRowIndex(startDateField,1);
                startDateField.setVisible(true);
                startDateLabel.setVisible(true);
                updateSliderMax(50000);
                sc.setStatus(SearchController.Status.HOTEL);
                break;

            case HOTEL:
                setResultsLabel("Day Tours:");
                roomsLabel.setVisible(false);
                roomsField.setVisible(false);
                skipButton.setVisible(false);
                checkoutButton.setVisible(true);
                addToFavButton.setVisible(true);
                sc.setStatus(SearchController.Status.DAYTOUR);
                updateSliderMax(40000);
                break;

            case DAYTOUR:
                System.out.println("error");
        }
    }

    //Helper, add items to MyTrip list
    public void updateMyTripList(){
        MyTripListView.getItems().clear();
        loadFlightsToList(MyTripListView, sc.getMyTrip().getFlightItems());
        loadHotelsToList(MyTripListView,sc.getMyTrip().getHotelItems());
        loadDayToursToList(MyTripListView,sc.getMyTrip().getDayTourItems());
        totalPriceLabel.setText("Total Price: "+ sc.getMyTrip().getPrice()+" kr.");
    }

    // hjalpar fall sem price sliderinn kallar á
    public void updateSlider()  {
        priceDisplay.setText(priceSlider.getValue() + " kr. ");
        updatePrice();
    }

    public void updateSliderMax(int maxPrice) {
        priceSlider.setMax(maxPrice);
        priceSlider.setValue(maxPrice);
        updateSlider();
    }

    // to make the "Please fill in requirements" label text red or black
    // True = red (on), false = black (off)
    public void setRequirementsTextColor(Boolean bool) {
        if (bool) {
            reqLabel.setTextFill(Color.color(1,0,0));
        } else {
            reqLabel.setTextFill(Color.color(0,0,0));
        }
    }

    // Display a "no search results" message / reset
    public void setResultsLabel(String message) {
        ResultLabel.setText(message);
    }

    // ----------------------------------------------------------------------------------------------------------------
    // Takkar

    //sc.onSearch kallar svo á loadDFHlist föllin hér fyrir ofan
    public void onSearch() {
        ResultsListView.getItems().clear();
        updateAllParams();
        sc.onSearch();
    }

    //Skip/Next takkinn. Kallar á updateStatus
    public void handleSkip() {
        updateStatus();
    }

    //Add item to trip takkinn.
    public void  addToMyTrip() {
        sc.addToMyTrip(selectedFItem,selectedHItem,selectedDTItem);
        updateMyTripList();
    }

    // add trip to favorites takkinn
    public void addToFavoriteTrips() {
        user.getFavoriteTrips().add(sc.getMyTrip());
        goToWelcome();
    }

    //Áfram takki
    public void goToCheckout() {
        if (MyTripListView.getItems() != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/ui/Checkout.fxml"));
                Parent root = loader.load();
                CheckoutController controller = loader.getController();
                controller.setUser(this.user);
                controller.setTrip(sc.getMyTrip());
                BookingController bookingController = new BookingController();
                controller.setBookingController(bookingController);
                Stage stage = (Stage) fromField.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
                stage.show();
            } catch (Exception e) {
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
            WelcomeController controller = loader.getController();
            controller.setUser(user);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
            stage.show();
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

}
