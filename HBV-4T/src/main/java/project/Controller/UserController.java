package project.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import project.Model.User;
import project.ui.WelcomeController;


public class UserController {
    // input fields
    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;

    @FXML private Button proceedButton; // update info

    private User user;

    // constructor workaround. alltaf kallað þegar skipt er á User síðu (þe frá welcome)
    public void setUser(User user) {
        this.user = user;
    }

    // constructor workaround. alltaf kallað þegar skipt er á User síðu (þe frá welcome)
    public void showData() {
        // Ef user er ekki null, birtum upplýsingar
        if (user != null) {
            nameField.setText(user.getName());
            emailField.setText(user.getEmail());
            passwordField.setText(user.getPassword());
        }
    }

    public void handleProceed() {
        // Uppfærum user með nýju gögnum úr TextField/PasswordField
        if (user != null) {
            user.setName(nameField.getText());
            user.setEmail(emailField.getText());
            user.setPassword(passwordField.getText());
        }

        goToWelcome();
    }

    public void goToWelcome() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/ui/Welcome.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) proceedButton.getScene().getWindow();
            Scene scene = new Scene(root);
            WelcomeController controller = loader.getController();
            controller.setUser(user);
            stage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
            stage.show();
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
