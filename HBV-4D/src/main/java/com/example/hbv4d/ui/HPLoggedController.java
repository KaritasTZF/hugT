package com.example.hbv4d.ui;

import com.example.hbv4d.objects.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Controller for the homepage if a user is logged into the system
 */
public class HPLoggedController {
    private static final String TOURS_PATH = "/com/example/hbv4d/tours-view.fxml";
    private static final String BOOKINGS_PATH = "/com/example/hbv4d/UserBookings-view.fxml";
    private static final String WISHLIST_PATH = "/com/example/hbv4d/wishlist-view.fxml";
    private static final String REVIEWS_PATH = "/com/example/hbv4d/review-view.fxml";

    @FXML
    public Label fxLoggedIn;

    /**
     * Initializes the controller if a user is logged in
     */
    @FXML
    public void initialize(){
        User user = User.getLoggedIn();
        if (user != null) {
            fxLoggedIn.setText("Welcome " + user.getName());
        } else {
            fxLoggedIn.setText(" ");
        }
    }

    /**
     * Switches scenes to Browse Tours
     */
    @FXML
    public void onBrowseTours() throws Exception {
        Application.switchScene(TOURS_PATH);
    }

    /**
     * Switches scenes to User Bookings
     */
    @FXML
    public void onManageBookings() throws Exception {
        Application.switchScene(BOOKINGS_PATH);
    }

    /**
     * Switches scenes to Wishlist
     */
    @FXML
    public void onWishlist() throws Exception {
        Application.switchScene(WISHLIST_PATH);
    }

    //TODO: má ekki sleppa þessu?
    @FXML
    public void onReviews() throws Exception {
        Application.switchScene(REVIEWS_PATH);
    }
}
