package project.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import project.Controller.BookingController;
import project.Model.*;
import project.util.Session;

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

    // --- NÝTT: BookingController breyta + setter ---
    private BookingController bookingController;
    public void setBookingController(BookingController bookingController) {
        this.bookingController = bookingController;
    }

    private Trip trip;


    public void setTrip(Trip trip) {

        this.trip = trip;

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

    @FXML
    public void book() {
        // Fá innslátt frá kreditkortareitunum
        String ccNumber = ccField.getText();
        String cvv = cvvField.getText();
        Object mm = mmField.getValue();
        Object yy = yyField.getValue();

        // Einföld villutékkun
        if(ccNumber == null || ccNumber.isBlank() ||
                cvv == null || cvv.isBlank() ||
                mm == null || yy == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation Error");
            alert.setHeaderText(null);
            alert.setContentText("Vinsamlegast sláðu inn gildar kortaupplýsingar.");
            alert.showAndWait();
            return;
        }

        // Athuga að BookingController og Trip séu til staðar
        if(bookingController == null || trip == null) {
            System.out.println("Vantar booking controller eða trip uppsetningu.");
            return;
        }

        // Sækum user id úr Session; hér er gert ráð fyrir að getUserId() skili int.
        // Ef notandinn geymir userId sem String sem þarf að umbreyta, breyttu eftir þörfum.
        String userID;
        try {
            userID = Session.getInstance().getCurrentUser().getUserID();
        } catch(NumberFormatException e) {
            System.err.println("User ID er ekki gild tala: " + Session.getInstance().getCurrentUser().getUserID());
            return;
        }
        String tripID = trip.getTripID();

        // Búa til bókun – kalla á BookingController til að búa til bókun
        Booking booking = bookingController.createBooking(userID, tripID);
        if(booking == null) {
            System.out.println("Ekki tókst að búa til bókun.");
            return;
        }
        // Staðfesta bókunina
        Booking confirmed = bookingController.confirmBooking(booking.getBookingID());
        if(confirmed == null) {
            System.out.println("Bókun staðfesting mistókst.");
            return;
        }

        // Birtum tilkynningu um að bókun hafi verið staðfest
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Bókun Tókst");
        alert.setHeaderText(null);
        alert.setContentText("Bókun þín hefur verið staðfest!\nStaðfestingar númer: " + confirmed.getConfirmationNr());
        alert.showAndWait();

        // Nú skiptum við yfir á Welcome.fxml
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/ui/Welcome.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) bookButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
