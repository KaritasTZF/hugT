package project.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import project.Model.Flight;

public class FlightItem {
    @FXML private Label fromLabel;
    @FXML private Label toLabel;
    @FXML private Label timeLabel;
    @FXML private Label dateLabel;
    @FXML private Label priceLabel;

    private Flight flight;
    private SearchViewController view;

    public void setData(Flight flight) {
        this.flight = flight;
        fromLabel.setText(flight.getFrom());
        toLabel.setText(flight.getTo());
        timeLabel.setText(STR."\{flight.getStartTime().toString()} - \{flight.getEndTime().toString()}");
        dateLabel.setText(flight.getDate().toString());
        priceLabel.setText(STR."\{flight.getPrice()} kr.");
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
