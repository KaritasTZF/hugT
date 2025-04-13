package com.example.hbv4d.ui;

import com.example.hbv4d.objects.Booking;
import com.example.hbv4d.objects.BookingDAO;
import com.example.hbv4d.objects.Tour;
import com.example.hbv4d.objects.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

import java.util.ArrayList;

/**
 * Controller for user bookings
 */
public class UserBookingsController {
    private static final String LOGGED_PATH = "/com/example/hbv4d/HPLogged-view.fxml";

    public User user;
    public Text descriptionText;
    public Label tourName;
    public Button fxCancelButton;
    public ListView<Booking> bookingListView = new ListView<>();

    /**
     * Initializes the controller. Gets bookings for the user from the database
     */
    @FXML
    public void initialize() {
        user = User.getLoggedIn();
        int usedId = user.getId();
        ArrayList<Booking> bookings = BookingDAO.listBookingsForUser(usedId);

        if (bookings != null) {
            for(Booking booking : bookings){
                Tour tour = BookingDAO.getTourForBooking(booking.getTourId());
                booking.setTour(tour);
                bookingListView.getItems().add(booking);
                System.out.println(tour);
            }
        }

        bookingListView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null && newValue.getTour() != null) {
                        tourName.setText(newValue.getTour().getTourName());
                        descriptionText.setText(newValue.getTour().getDescription());
                    } else {
                        tourName.setText("");
                        descriptionText.setText("");
                    }
                }
        );
    }

    //TODO:
    public void modify() {
    }

    /**
     * Canceling a booking that has been made by a user.
     */
    public void cancel() {
        Booking selectedBooking = bookingListView.getSelectionModel().getSelectedItem();

        if(selectedBooking != null){
            BookingDAO.deleteBooking(selectedBooking.getTourId());
        }
    }

    /**
     * Switces scenes back to the homepage
     */
    public void onBack() throws Exception {
        Application.switchScene(LOGGED_PATH);
    }
}
