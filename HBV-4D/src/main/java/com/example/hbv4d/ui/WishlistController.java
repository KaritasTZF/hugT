package com.example.hbv4d.ui;

import com.example.hbv4d.objects.Tour;
import com.example.hbv4d.objects.Wishlist;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

/**
 * Controller for wishlist
 */
public class WishlistController {

    private static final String HOMEPAGE_PATH = "/com/example/hbv4d/HPLogged-view.fxml";

    @FXML
    public ListView<Tour> wishlistListView;

    /**
     * initializes the wishlist with items added to wishlist in Tour Controller
     */
    @FXML
    public void initialize() {
        wishlistListView.setItems(Wishlist.getWishlist());
    }

    /**
     * Removes selected tour from wishlist
     */
    @FXML
    public void onRemoveFromWishlist() {
        Tour selectedTour = wishlistListView.getSelectionModel().getSelectedItem();
        if (selectedTour != null) {
            Wishlist.removeTour(selectedTour);
        }
    }

    /**
     * Switches scenes back to homepage
     */
    @FXML
    public void onBack() throws Exception {
        Application.switchScene(HOMEPAGE_PATH);
    }
}
