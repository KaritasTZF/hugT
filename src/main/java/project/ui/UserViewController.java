package project.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UserViewController {
    @FXML
    private TextField nameField;

    public void proceedButton() {

    }

    public void handleBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/ui/Welcome.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) nameField.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
            stage.show();
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
