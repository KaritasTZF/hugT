package project.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import project.Model.DayTour;

public class DayTourItem {
    @FXML private Label toLabel;
    @FXML private Label dateLabel;
    @FXML private Label priceLabel;
    @FXML private Label timeLabel;

    private DayTour dayTour;

    public void setData(DayTour dayTour) {
        this.dayTour = dayTour;
        toLabel.setText(dayTour.getLocation());
        dateLabel.setText(dayTour.getDate().toString());
        priceLabel.setText(String.valueOf(dayTour.getPrice()));
        //timeLabel.setText(String.valueOf(dayTour.getTime()));
    }

    public DayTour getDayTour() {return this.dayTour;}
}
