package project.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import project.Model.User;
import project.util.Session;


public class UserViewController {
    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button continueButton;

    @FXML
    private Button proceedButton;

    private User user;

    @FXML
    public void initialize() {
        // Sæki notanda t.d. úr Session
        this.user = Session.getInstance().getCurrentUser();

        // Ef user er ekki null, birtum upplýsingar
        if (user != null) {
            nameField.setText(user.getName());
            emailField.setText(user.getEmail());
            passwordField.setText(user.getPassword()); // ef þú geymir password í user
        }

        // Gera reitina óbreytanlega (ef þú vilt extra öryggi)
        nameField.setEditable(true);
        emailField.setEditable(true);
        passwordField.setEditable(true);
    }

    @FXML
    public void handleProceed() {
        // Uppfærum user með nýju gögnum úr TextField/PasswordField
        if (user != null) {
            user.setName(nameField.getText());
            user.setEmail(emailField.getText());
            user.setPassword(passwordField.getText());
        }

        // Hér gætirðu farið í næsta scene eða gert eitthvað annað
        goToWelcome();
    }

    private void goToWelcome() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/ui/Welcome.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) proceedButton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            // T.d. setja CSS
            scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
            stage.show();
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
