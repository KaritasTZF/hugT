package project.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import project.Controller.BookingController;
import project.Model.Booking;
import project.Model.Trip;

public class BookingItem {
    @FXML private Label locLabel;
    @FXML private Label startDateLabel;
    @FXML private Label endateLabel;
    @FXML private Label priceLabel;

    private Booking booking;
    private BookingController view;

    public void setData(Booking booking) {
        this.booking = booking;
        Trip trip = booking.getTrip();
        if (trip.getHotelItems() != null && !trip.getHotelItems().isEmpty()) {
            locLabel.setText(trip.getHotelItems().getFirst().getLocation());
        } else {
            locLabel.setVisible(false);
        }

        startDateLabel.setText(trip.getStartDate().toString());
        endateLabel.setText(trip.getEndDate().toString());
        priceLabel.setText(String.valueOf(trip.getPrice())+" kr.");
    }

    public void setView(BookingController view) {
        this.view = view;
    }

    public void handleSelection() {
        if (view !=null) {
            this.view.showBookingDetails(booking);
        }
    }
}
