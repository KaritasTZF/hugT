package project.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class WelcomeController {
    @FXML Button toSearch;
    @FXML Button toUser;
    @FXML Button toBooking;

    public void goToSearch() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/ui/Search.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) toSearch.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
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
            Scene scene = new Scene(root);
            stage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void goToBookings() {
        //Open Bookings.fxml <- ekki ennþá til
    }
}
