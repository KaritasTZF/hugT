package project.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import project.Model.Booking;

public class WelcomeController {
    @FXML Button toSearch;
    @FXML Button toUser;
    @FXML Button toBookings;
    @FXML Button toFavorites;
    @FXML private ListView<Booking> bookingsListView;
    @FXML private Label bookingIdLabel;
    @FXML private Label tripLabel;
    @FXML private Label statusLabel;
    @FXML private Label confirmationLabel;
    @FXML private Button backButton;


    public void goToSearch() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/ui/Search.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) toSearch.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void goToUser() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/ui/User.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) toUser.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void goToBookings() {
        try {
            // Hlaða upp Bookings.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/ui/Booking.fxml"));
            Parent root = loader.load();
            // Fáum tilvísun á stage-ið út frá knöppnum
            Stage stage = (Stage) toBookings.getScene().getWindow();
            // Setjum nýja scene með Bookings.fxml
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goToFavorites() {
        try {
            // Hleður upp nýrri FXML-skrá fyrir "Manage Favorite" skjáinn
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/ui/FavoriteManager.fxml"));
            Parent root = loader.load();

            // Ef þú vilt setja controller fyrir FavoriteManager, þá getur þú gert það hér.
            // FavoriteManagerController controller = loader.getController();
            // controller.set...(...);

            Stage stage = (Stage)toFavorites.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
