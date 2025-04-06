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

    public void goToBooking() {
        //Open Booking.fxml <- ekki ennþá til
    }
}
