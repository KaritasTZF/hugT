package project.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import project.Controller.FavoriteController;
import project.Model.Trip;
import project.Model.User;

import java.io.IOException;

public class FavoriteViewController {

    @FXML
    private Label favoriteLabel;

    @FXML
    private ListView<HBox> favoriteTripsList;

    private FavoriteController favoriteController;
    private User user;

    public void setFavoriteController(FavoriteController favoriteController) {
        this.favoriteController = favoriteController;
    }
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
}
