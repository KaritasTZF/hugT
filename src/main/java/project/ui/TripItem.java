package project.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import project.Model.Trip;

public class TripItem {

    @FXML private Label locLabel;
    @FXML private Label startDateLabel;
    @FXML private Label endateLabel;
    @FXML private Label priceLabel;

    private FavoriteViewController view;
    private Trip trip;

    public void setView(FavoriteViewController view) {
        this.view = view;
    }

    public void setData(Trip trip) {
        this.trip = trip;
        if (trip.getHotelItems() != null && !trip.getHotelItems().isEmpty()) {
            locLabel.setText(trip.getHotelItems().getFirst().getLocation());
        } else {
            locLabel.setVisible(false);
        }

        startDateLabel.setText(trip.getStartDate().toString());
        endateLabel.setText(trip.getEndDate().toString());
        priceLabel.setText(String.valueOf(trip.getPrice())+" kr.");
    }

    public void handleSelection() {
        if (view !=null) {
            this.view.handleSelection(this.trip);
        }
    }
}
