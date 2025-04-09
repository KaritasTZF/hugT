package project.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import project.Model.DayTour;
import project.Model.Flight;
import project.Model.Hotel;
import project.Model.Trip;

import java.io.IOException;

public class CheckoutController {
    @FXML private Label totalPriceLabel;
    @FXML private Label flavorLabel;
    @FXML private Label datesLabel;
    @FXML private TextField ccField;
    @FXML private TextField cvvField;
    @FXML private Button bookButton;
    @FXML private Button cancelButton;
    @FXML private ComboBox mmField;
    @FXML private ComboBox yyField;
    @FXML private ListView<HBox> tripList;

    public void setTrip(Trip trip) {
        for (Flight flight:trip.getFlightItems() ) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/ui/FlightItem.fxml"));
                Parent flightItem = loader.load();
                FlightItem controller = loader.getController();
                controller.setData(flight);
                tripList.getItems().add((HBox) flightItem);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        for (Hotel hotel: trip.getHotelItems()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/ui/HotelItem.fxml"));
                Parent hotelItem = loader.load();
                HotelItem controller = loader.getController();
                controller.setData(hotel);
                tripList.getItems().add((HBox) hotelItem);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        for (DayTour dayTour: trip.getDayTourItems()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/ui/DayTourItem.fxml"));
                Parent dayTourItem =loader.load();
                DayTourItem controller =loader.getController();
                controller.setData(dayTour);
                tripList.getItems().add((HBox)dayTourItem);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (trip.getHotelItems() != null && !trip.getHotelItems().isEmpty()) {
            flavorLabel.setText("Enjoy your \"sunny\" trip to "+trip.getHotelItems().getFirst().getLocation()+"!");
        }
        totalPriceLabel.setText("Total price: "+trip.getPrice()+" kr.");
        if (trip.getStartDate() != null) {
            datesLabel.setText("From "+trip.getStartDate()+" to "+trip.getEndDate()+", "+trip.getDays()+" days.");
        } else {
            datesLabel.setVisible(false);
        }
    }

    public void book() {

    }

    public void cancel() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/ui/Search.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) bookButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
