package project.ui.listItems;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import project.Model.DayTour;
import project.ui.SearchViewController;

public class DayTourItem {
    @FXML private Label nameLabel;
    @FXML private Label toLabel;
    @FXML private Label dateLabel;
    @FXML private Label priceLabel;
    @FXML private Label timeLabel;

    private DayTour dayTour;
    private SearchViewController view;

    public void setData(DayTour dayTour) {
        this.dayTour = dayTour;
        toLabel.setText(dayTour.getLocation());
        dateLabel.setText(dayTour.getDate().toString());
        priceLabel.setText(dayTour.getPrice() +" kr.");
        timeLabel.setText(String.valueOf(dayTour.getSchedule()));
        nameLabel.setText(dayTour.getName());
    }
    public void setView(SearchViewController view) {
        this.view = view;
    }

    public void handleSelection() {
        if (view !=null) {
            this.view.handleDayTourSelection(this.dayTour);
        }

    }
}
