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
    private SearchViewController view;

    public void setData(Flight flight) {
        this.flight = flight;
        fromLabel.setText(flight.getFrom());
        toLabel.setText(flight.getTo());
        depLabel.setText(flight.getStartTime().toString());
        arrLabel.setText(flight.getEndTime().toString());
        dateLabel.setText(flight.getDate().toString());
        priceLabel.setText(String.valueOf(flight.getPrice()));
    }
    public void setView(SearchViewController view) {
        this.view = view;
    }

    public Flight getFlight() {return this.flight;}

    public void handleSelection() {
        if (view !=null) {
            this.view.handleFlightSelection(this.flight);
        }
    }
}
