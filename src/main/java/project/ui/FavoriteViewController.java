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

import java.io.IOException;
import java.util.Objects;

public class FavoriteViewController {

    @FXML
    private Label favoriteLabel;

    @FXML
    private ListView<HBox> favoriteTripsList;

    @FXML private Trip selectedTrip;

    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    public void showData() {
        for (Trip trip: user.getFavoriteTrips()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/ui/TripItem.fxml"));
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

    public void handleSelection(Trip trip) {
        this.selectedTrip = trip;
    }

    public void goToWelcome() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/ui/Welcome.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) favoriteLabel.getScene().getWindow();
            WelcomeController controller = loader.getController();
            controller.setUser(user);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
            stage.show();
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    public void checkout() {
        if (selectedTrip !=null){
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/ui/Checkout.fxml"));
                Parent root = loader.load();
                CheckoutController controller = loader.getController();
                controller.setUser(this.user);
                controller.setTrip(selectedTrip);
                BookingController bookingController = new BookingController();
                controller.setBookingController(bookingController);
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

    public void removeFromFavorites() {
        user.getFavoriteTrips().removeIf(trip -> Objects.equals(trip.getTripID(), selectedTrip.getTripID()));
        selectedTrip = null;
        goToWelcome();
    }
}
