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

    public void setData(Hotel hotel, SearchController sc){
        this.hotel = hotel;
        this.sc = sc;
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

    @FXML
    public void handleSelection(javafx.scene.input.MouseEvent mouseEvent) {
        this.sc.handleHotelSelection(this.hotel);
    }
}
