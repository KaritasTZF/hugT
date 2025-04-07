package project.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import project.Model.Flight;

public class FlightItem {
    @FXML private Label fromLabel;
    @FXML private Label toLabel;
    @FXML private Label depLabel;
    @FXML private Label arrLabel;
    @FXML private Label dateLabel;
    @FXML private Label priceLabel;

    private Flight flight;

    public void setData(Flight flight) {
        this.flight = flight;
        fromLabel.setText(flight.getFrom());
        toLabel.setText(flight.getTo());
        depLabel.setText(flight.getStartTime().toString());
        arrLabel.setText(flight.getEndTime().toString());
        dateLabel.setText(flight.getDate().toString());
        priceLabel.setText(String.valueOf(flight.getPrice()));
    }
}
