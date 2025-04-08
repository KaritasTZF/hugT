package project.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
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

    public void setData(Hotel hotel){
        this.hotel = hotel;
        //nameLabel.setText(hotel.getName());
        locLabel.setText(hotel.getLocation());
        //peopleLabel.setText(hotel.getPeople()); <-room property
        startDateLabel.setText(hotel.getStartDate().toString());
        endDateLabel.setText(hotel.getEndDate().toString());
        priceLabel.setText(String.valueOf(hotel.getPrice()));
        //roomsLabel.setText( <-rooms thing
    }
    //TODO: laga rooms stuff
    public Hotel getHotel() {return this.hotel;}
}
