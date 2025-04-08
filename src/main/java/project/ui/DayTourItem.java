package project.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import project.Controller.SearchController;
import project.Model.DayTour;

public class DayTourItem {
    @FXML private Label nameLabel;
    @FXML private Label toLabel;
    @FXML private Label dateLabel;
    @FXML private Label priceLabel;
    @FXML private Label timeLabel;

    private DayTour dayTour;
    private SearchController sc;

    public void setData(DayTour dayTour) {
        this.dayTour = dayTour;
        toLabel.setText(dayTour.getLocation());
        dateLabel.setText(dayTour.getDate().toString());
        priceLabel.setText(String.valueOf(dayTour.getPrice()));
        //timeLabel.setText(String.valueOf(dayTour.getTime()));
        //nameLabel.setText(dayTour.getName());
    }
    public void setSc(SearchController sc) {
        this.sc = sc;
    }

    public DayTour getDayTour() {return this.dayTour;}

    public void handleSelection() {
        if (sc !=null) {
            sc.handleDayTourSelection(dayTour);
        }
    }
}
