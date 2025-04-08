package project.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import project.Controller.SearchController;
import project.Model.Hotel;

public class HotelItem {
    @FXML private Label nameLabel;
    @FXML private Label locLabel;
    @FXML private Label peopleLabel;
    @FXML private Label startDateLabel;
    @FXML private Label endDateLabel;
    @FXML private Label priceLabel;
    @FXML private Label roomsLabel;

    private Hotel hotel;
    private SearchController sc;

    public void setData(Hotel hotel){
        this.hotel = hotel;
        nameLabel.setText(hotel.getName());
        locLabel.setText(hotel.getLocation());
        // peopleLabel.setText(hotel);
        startDateLabel.setText(hotel.getStartDate().toString());
        endDateLabel.setText(hotel.getEndDate().toString());
        priceLabel.setText(String.valueOf(hotel.getPrice()));
        roomsLabel.setText(String.valueOf(hotel.getRooms()));
    }
    public void setSc(SearchController sc) {
        this.sc = sc;
    }

    //TODO: laga rooms stuff
    public Hotel getHotel() {return this.hotel;}

    @FXML
    public void handleSelection() {
        if (sc !=null) {
            this.sc.handleHotelSelection(this.hotel);
        }
    }
}
