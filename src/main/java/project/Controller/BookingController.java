package project.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import project.Model.*;
import project.ui.listItems.BookingItem;
import project.ui.WelcomeController;

import java.io.IOException;
import java.util.Random;

public class BookingController {

    @FXML private ListView<HBox>  bookingsListView;
    @FXML private Label bookingIdLabel;
    @FXML private Label tripLabel;
    @FXML private Label statusLabel;
    @FXML private Label confirmationLabel;
    @FXML private Button backButton;

    // Gagnateljari til að útbúa einstakt bookingID
    private int bookingCounter = 1;
    private User user;

    public Booking createBooking(User user, Trip trip) {
        if (trip == null) {
            System.out.println("Trip not found for ID: " + trip.getTripID());
            return null;
        }

        // Smíðum nýja Booking
        Booking newBooking = new Booking(
                trip,                   // Trip hlutinn
                bookingCounter++,     // auto-inkrement bookingID
                "Pending",            // upphafsstöðu (Pending)
                0                     // confirmationNr byrjar sem 0
        );
        // Bætum bókuninni í listann
        user.getBookedTrips().add(newBooking);

        //Bókum hjá database 4H og 4D
        HotelBookInDB bookH = new HotelBookInDB(user);
        DayTourBookInDB bookD = new DayTourBookInDB(user);
        for (Hotel hotel: trip.getHotelItems()) {
            bookH.bookHotel(hotel);
        }
        for (DayTour dayTour: trip.getDayTourItems()) {
            bookD.bookDayTour(dayTour);
        }
        //Engin bókun hjá F

        // Skilum nýju bókuninni
        return newBooking;
    }


    public Booking confirmBooking(int bookingID) {
        // Leitum að bókuninni með getBookingByID
        Booking booking = getBookingByID(bookingID);
        if (booking == null) {
            System.out.println("No booking found with ID: " + bookingID);
            return null;
        }
        // Uppfærum stöðu og setjum staðfestingarnúmer
        booking.setStatus("Confirmed");
        booking.setConfirmationNr(generateConfirmationNumber());
        return booking;
    }

    private Booking getBookingByID(int bookingID) {
        for (Booking b : user.getBookedTrips()) {
            if (b.getBookingID() == bookingID) {
                return b;
            }
        }
        return null;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private int generateConfirmationNumber() {
        Random rand = new Random();
        return 100000 + rand.nextInt(900000);  // Númer á bilinu 100000 til 999999
    }

    @FXML
    public void showData() {
        for (Booking booking: user.getBookedTrips() ) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/ui/listItems/BookingItem.fxml"));
                Parent bookingItem = loader.load();
                BookingItem controller = loader.getController();
                controller.setData(booking);
                controller.setView(this);
                bookingsListView.getItems().add((HBox) bookingItem);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void showBookingDetails(Booking booking) {
        if (booking != null) {
            bookingIdLabel.setText("Booking ID: " + booking.getBookingID());
            tripLabel.setText("Trip: " + booking.getTrip().getTripID());
            statusLabel.setText("Status: " + booking.getStatus());
            confirmationLabel.setText("Confirmation #: " + booking.getConfirmationNr());
        }
    }

    @FXML
    public void handleBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/ui/Welcome.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) backButton.getScene().getWindow();
            WelcomeController controller = loader.getController();
            controller.setUser(user);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
            stage.show();
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
