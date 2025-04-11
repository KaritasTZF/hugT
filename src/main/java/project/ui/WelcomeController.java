package project.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import project.Controller.BookingController;
import project.Controller.UserController;
import project.Model.User;

public class WelcomeController {
    @FXML private Button toSearch;
    @FXML private Button toUser;
    @FXML private Button toBookings;
    @FXML private Button toFavTrips;  // breytt í toFavTrips, eins og í FXML

    // Bættu við Label reitum fyrir titlana
    @FXML private Label welcomeLabel;
    @FXML private Label subtitleLabel;

    private User user;

    public void setUser(User user) {
        this.user = user;

        // Uppfæra welcomeLabel með nafni notanda og notum stóra stafi í textanum
        welcomeLabel.setText("VELKOMIN/N " + user.getName().toUpperCase() + "!");
        // Ef þörf er á að uppfæra undirtitilinn (hér látið hann vera stöðugur):
        subtitleLabel.setText("FERÐALAGIÐ HEFST HÉR");
    }

    public void goToSearch() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/ui/Search.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) toSearch.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
            SearchViewController controller = loader.getController();
            controller.setUser(user);
            stage.show();
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void goToUser() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/ui/User.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) toUser.getScene().getWindow();
            UserController controller = loader.getController();
            controller.setUser(user);
            controller.showData();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
            stage.show();
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void goToBookings() {
        try {
            // Hlaða upp Bookings.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/ui/Booking.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) toBookings.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
            BookingController controller = loader.getController();
            controller.setUser(user);
            controller.showData();
            stage.show();
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void goToFavTrips() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/ui/FavoriteView.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) toFavTrips.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
            FavoriteViewController controller = loader.getController();
            controller.setUser(user); // Pass user through to next page
            controller.showData(); //
            stage.show();
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

}
