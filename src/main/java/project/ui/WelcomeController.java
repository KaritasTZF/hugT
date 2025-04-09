package project.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import project.Controller.BookingController;
import project.Model.User;

public class WelcomeController {
    @FXML Button toSearch;
    @FXML Button toUser;
    @FXML Button toBookings;
    @FXML Button toFavorites;
    private User user;

    public void setUser(User user) {
        this.user = user;
        System.out.println("Welcome set user " + user.getUserID());
    }

    public void goToSearch() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/ui/Search.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) toSearch.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
            SearchViewController controller = loader.getController();
            controller.setUser(user);
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
            UserViewController controller = loader.getController();
            controller.setUser(user);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
            stage.show();
        }catch(Exception e){
            throw new RuntimeException(e);
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
            Scene scene = new Scene(root);
            stage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
            BookingController controller = loader.getController();
            controller.setUser(user);
            controller.showData();
            stage.show();
        } catch(Exception e){
            throw new RuntimeException(e);
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
            Scene scene = new Scene(root);
            stage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
