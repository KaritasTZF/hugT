package project.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import project.Controller.BookingController;
import project.Model.Trip;
import project.Model.User;
import project.ui.listItems.TripItem;

import java.io.IOException;
import java.util.Objects;

public class FavoriteViewController {

    @FXML private Label favoriteLabel;

    @FXML private ListView<HBox> favoriteTripsList; // The javafx ListView element; items defined in TripItem
    @FXML private Trip selectedTrip; //trip selected, passed from TripItem

    private User user;
    public void setUser(User user) {
        this.user = user;
    }

    //Workaround of constructor; always called upon when switching to Favorites page. Shows the list of trips
    public void showData() {
        for (Trip trip: user.getFavoriteTrips()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/ui/listItems/TripItem.fxml"));
                Parent favoriteItem = loader.load();
                TripItem controller = loader.getController();
                controller.setView(this);
                controller.setData(trip);
                favoriteTripsList.getItems().add((HBox) favoriteItem);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //Called by TripItem when its entry is selected
    public void handleSelection(Trip trip) {
        this.selectedTrip = trip;
    }

    public void goToWelcome() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/ui/Welcome.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) favoriteLabel.getScene().getWindow();
            WelcomeController controller = loader.getController();
            controller.setUser(user); //Pass user through to new scene & controller
            //CSS
            Scene scene = new Scene(root);
            stage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
            stage.show();
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    // go to book the selected trip
    public void goToCheckout() {
        if (selectedTrip !=null){
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/ui/Checkout.fxml"));
                Parent root = loader.load();

                CheckoutController controller = loader.getController();
                controller.setUser(this.user); //Pass user through to new scene & controller
                controller.setTrip(selectedTrip); // pass trip to checkout screen

                // create and set booking controller for the checkout screen
                BookingController bookingController = new BookingController();
                controller.setBookingController(bookingController);

                //put CSS in
                Stage stage = (Stage) favoriteLabel.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
                stage.show();
            }catch(Exception e){
                throw new RuntimeException(e);
            }
        }
    }

    //Remove selected trip from favorites list
    public void removeFromFavorites() {
        user.getFavoriteTrips().removeIf(trip -> Objects.equals(trip.getTripID(), selectedTrip.getTripID()));
        selectedTrip = null;
        goToWelcome();
    }
}
