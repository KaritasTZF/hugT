package project.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import project.Controller.SearchController;
import project.Model.Flight;

public class FlightItem {
    @FXML private Label fromLabel;
    @FXML private Label toLabel;
    @FXML private Label depLabel;
    @FXML private Label arrLabel;
    @FXML private Label dateLabel;
    @FXML private Label priceLabel;

    private Flight flight;
    private SearchController sc;

    public void setData(Flight flight) {
        this.flight = flight;
        fromLabel.setText(flight.getFrom());
        toLabel.setText(flight.getTo());
        depLabel.setText(flight.getStartTime().toString());
        arrLabel.setText(flight.getEndTime().toString());
        dateLabel.setText(flight.getDate().toString());
        priceLabel.setText(String.valueOf(flight.getPrice()));
    }
    public void setSc(SearchController sc) {
        this.sc = sc;
    }

    public Flight getFlight() {return this.flight;}

    public void handleSelection() {
        if (sc !=null) {
            this.sc.handleFlightSelection(this.flight);
        }
    }
}
