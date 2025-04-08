package project.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import project.Model.Hotel;

public class HotelItem {
    @FXML private Label nameLabel;
    @FXML private Label locLabel;
    @FXML private Label startDateLabel;
    @FXML private Label endDateLabel;
    @FXML private Label priceLabel;
    @FXML private Label roomsLabel;

    private Hotel hotel;
    private SearchViewController view;

    public void setData(Hotel hotel){
        this.hotel = hotel;
        nameLabel.setText(hotel.getName());
        locLabel.setText(hotel.getLocation());
        startDateLabel.setText(hotel.getStartDate().toString());
        endDateLabel.setText(hotel.getEndDate().toString());
        priceLabel.setText(String.valueOf(hotel.getPrice())+" kr.");
        roomsLabel.setText(STR."\{String.valueOf(hotel.getRooms())} rooms");
    }
    public void setView(SearchViewController view) {
        this.view = view;
    }

    public Hotel getHotel() {return this.hotel;}

    @FXML
    public void handleSelection() {
        if (view !=null) {
            this.view.handleHotelSelection(this.hotel);
        }
    }
}
