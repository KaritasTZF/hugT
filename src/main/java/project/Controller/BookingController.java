package project.Controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import project.Model.Booking;
import project.Model.Trip;
import project.util.DBHelper;
import project.util.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class BookingController {

    @FXML private ListView<Booking> bookingsListView;
    @FXML private Label bookingIdLabel;
    @FXML private Label tripLabel;
    @FXML private Label statusLabel;
    @FXML private Label confirmationLabel;
    @FXML private Button backButton;

    // Listi til að geyma bókanir
    private final List<Booking> bookings = new ArrayList<>();
    // Gagnateljari til að útbúa einstakt bookingID
    private int bookingCounter = 1;

    private BookingController bookingController;

    // Lista af Trips; notað til að "leita" að réttu Trip hlutnum.
    private final List<Trip> trips = new ArrayList<>();

    public void addTrip(Trip trip) {
        if (getTripById(trip.getTripID()) == null) {
            trips.add(trip);
        }
    }

    public Booking createBooking(String userID, String tripID) {

        // Leitum að Trip með því að nota getTripById(String)
        Trip chosenTrip = getTripById(tripID);
        if (chosenTrip == null) {
            System.out.println("Trip not found for ID: " + tripID);
            return null;
        }
        // Bæta við Trip ef hann er ekki í lista
        if (getTripById(chosenTrip.getTripID()) == null) {
            trips.add(chosenTrip);
        }
        // Smíðum nýja Booking
        Booking newBooking = new Booking(
                userID,               // notendauðkenni
                chosenTrip,           // Trip hlutinn
                bookingCounter++,     // auto-inkrement bookingID
                "Pending",            // upphafsstöðu (Pending)
                0                     // confirmationNr byrjar sem 0
        );
        // Bætum bókuninni í listann
        bookings.add(newBooking);
        
//TODO kalla á FHD bookings
        boolean inserted = true; 
        if (!inserted) {
            System.out.println("Could not insert booking into the database.");
            return null;
        }
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
        for (Booking b : bookings) {
            if (b.getBookingID() == bookingID) {
                return b;
            }
        }
        return null;
    }

    public void setBookingController(BookingController bookingController) {
        this.bookingController = bookingController;
    }

    private int generateConfirmationNumber() {
        Random rand = new Random();
        return 100000 + rand.nextInt(900000);  // Númer á bilinu 100000 til 999999
    }

    private Trip getTripById(String tripID) {
        for (Trip t : trips) {
            if (t.getTripID().equals(tripID)) {
                return t;
            }
        }
        return null;
    }

    @FXML
    public void initialize() {
        // Sóttum innskráðan notanda
        String currentUserID = Session.getInstance().getCurrentUser().getUserID();
        // Sóttum bókunirnar úr gagnagrunninum fyrir þennan user
        //List<Booking> userBookings = DBHelper.getBookingsByUser(currentUserID);
        List<Booking> userBookings = new List<Booking>();
        bookingsListView.setItems(FXCollections.observableArrayList(userBookings));

        // Setjum listener á val á ListView til að sýna nánari upplýsingar.
        bookingsListView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldBooking, newBooking) -> showBookingDetails(newBooking)
        );
    }

    private void showBookingDetails(Booking booking) {
        if (booking != null) {
            bookingIdLabel.setText("Booking ID: " + booking.getBookingID());
            // Hér er bara sýndur Trip ID; breyttu ef þú þarft að sýna meira
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
            stage.setScene(new Scene(root));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


}
