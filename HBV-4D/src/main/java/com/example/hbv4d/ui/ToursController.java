package com.example.hbv4d.ui;

import com.example.hbv4d.utils.InfoDialog;
import com.example.hbv4d.objects.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.List;

/**
 * Controller for browsing tours.
 * User can browser tours and see tour reviews
 * Logged-in user can browse tours, add tours to wishlist, see reviews and book a tour.
 */
public class ToursController {

    private static final String LOGGED_PATH = "/com/example/hbv4d/HPLogged-view.fxml";
    private static final String UNLOGGED_PATH = "/com/example/hbv4d/HP-view.fxml";
    private static final String BOOKING_PATH = "/com/example/hbv4d/booking-view.fxml";

    public Label fxLoggedIn;
    public TextField searchBar;
    public AnchorPane infoPane;
    public Text descriptionText;
    public Text scheduleText;
    public Label descriptionTitle;
    public Label dateLabel;
    public User user;
    public ListView<Tour> tourList = new ListView<>();

    @FXML
    private Button fxWishlistButton;
    @FXML
    private Button fxBookingButton;
    @FXML
    private ComboBox<String> priceFilter;

    /**
     * Initializes the controller
     * Adds tours from Database to and ObservableList
     * Adds tours from the ObservableList to a filtered list for filtering
     * Enables buttons for logged-in User
     */
    @FXML
    public void initialize(){
        ObservableList<Tour> tours = TourDAO.listTours();
        if (tours != null) {
            for (Tour tour : tours) {
                tourList.getItems().add(tour);
            }
        }

        user = User.getLoggedIn();
        if (user != null) {
            fxLoggedIn.setText("User: " + user.getName());
            fxBookingButton.setDisable(false);
            fxWishlistButton.setDisable(false);
        } else {
            fxBookingButton.setDisable(true);
            fxWishlistButton.setDisable(true);
        }

        searchBar.setOnKeyPressed(e -> {
            if(e.getCode().toString().equals("ENTER")){
                onSearch();
            }
        });
    }

    /**
     * Helper function for getting all information for a selected tour
     * @param tour Selected tour from the ObservableList
     */
    void getTourInformation(Tour tour){
        descriptionTitle.setText(tour.getTourName());
        dateLabel.setText(tour.getDate().toString().formatted("%d/%m/%Y"));
        descriptionText.setText(tour.getDescription());
        scheduleText.setText(tour.getSchedule());
        if (infoPane.isVisible()) {
            return;
        }
        infoPane.setVisible(true);
    }

    /**
     * Switches scenes to booking scene if a tour is selected and Book button is pressed
     */
    public void onBookTour() {
        Tour selectedTour = tourList.getSelectionModel().getSelectedItem();
        if (selectedTour == null) {
            return;
        }
        try {
            Application.bookingScene(BOOKING_PATH, selectedTour);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets all information for selected tour
     */
    public void onClickedTour(){
        Tour selectedTour = tourList.getSelectionModel().getSelectedItem();
        if(selectedTour == null){
            return;
        }
        getTourInformation(selectedTour);
    }

    /**
     *  See reviews for selected tour
     */
    public void onSeeReviews() {
        Tour selectedTour = tourList.getSelectionModel().getSelectedItem();
        if (selectedTour == null) {
            return;
        }

        List<Review> reviews = ReviewDAO.getReviewsForTour(selectedTour.getId());
        InfoDialog alert = new InfoDialog("Tour Reviews","Reviews for " + selectedTour.getTourName());

        if (reviews.isEmpty()) {
            alert.setContentText("No reviews available for this tour.");
        } else {
            StringBuilder reviewText = new StringBuilder();
            for (Review review : reviews) {
                reviewText.append("Rating ").append(review.getRating()).append("/5 - ")
                        .append(review.getFirstName()).append("\n")
                        .append("\"").append(review.getComment()).append("\"\n\n");
            }
            alert.setContentText(reviewText.toString());
        }

        alert.showAndWait();
    }

    /**
     * Adds selected tour to wishlist
     */
    public void onAddToWishlist() {
        Tour selectedTour = tourList.getSelectionModel().getSelectedItem();
        if (selectedTour != null) {
            Wishlist.addTour(selectedTour);
        }

        assert selectedTour != null;
        InfoDialog alert = new InfoDialog("Added to Wishlist","Tour Added Successfully!");
        alert.setContentText(selectedTour.getTourName() + " has been added to your wishlist.");
        alert.showAndWait();
    }

    /**
     * Search for a specific tour, city or date
     */
    public void onSearch(){
        String search = searchBar.getText();
        ObservableList<Tour> tours = TourDAO.getToursByFilter(search);
        System.out.println(tours);
        if(tours != null){
            tourList.getItems().setAll(tours);
        }

    }

    /**
     * Switches scenes back to the homepage
     */
    @FXML
    public void onBack() throws Exception {
        user = User.getLoggedIn();
        if (user != null) {
            Application.switchScene(LOGGED_PATH);
        } else {
            Application.switchScene(UNLOGGED_PATH);
        }

    }

    /**
     * Applies filters
     */
    @FXML
    public void applyFilters() {
        String value = priceFilter.getValue();
        String intValue = value.split("kr")[0].trim();
        int price = Integer.parseInt(intValue.replaceAll("\\.",""));
        ObservableList<Tour> filteredTours = TourDAO.listToursByPrice(price);
        if (filteredTours != null) {
            tourList.getItems().setAll(filteredTours);
        }
    }

}
