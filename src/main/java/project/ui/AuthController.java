package project.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import project.Model.User;
import project.util.Session;

import java.util.UUID;

public class AuthController {

    // --- FXML tilvísanir fyrir Sign In flipann ---
    @FXML
    private TextField signinUsernameField;

    @FXML
    private PasswordField signinPasswordField;

    // --- FXML tilvísanir fyrir Sign Up flipann ---
    @FXML
    private TextField signupUsernameField;

    @FXML
    private PasswordField signupPasswordField;

    @FXML
    private TextField signupEmailField;

    @FXML
    private TabPane authTabPane;

    private User user; //user to log in

    /**
     * Event handler fyrir Sign In takkan.
     */
    @FXML
    public void handleSignIn() {
        String username = signinUsernameField.getText();
        String password = signinPasswordField.getText();

        // Einföld villutékkun til að tryggja að reitir séu ekki tómir.
        if (username == null || username.trim().isEmpty() ||
                password == null || password.trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Sign In Mistókst");
            alert.setHeaderText(null);
            alert.setContentText("Vinsamlegast sláðu inn bæði notendanafn og lykilorð.");
            alert.showAndWait();
            return;
        }
        //Búum til nýtt user
        user = new User("0",username,password,"");

        if (user != null) {
            // Notandi fannst, athugum hvort lykilorðið samsvari.
            if (true) {
                Session.getInstance().setCurrentUser(user);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sign In Tókst");
                alert.setHeaderText(null);
                alert.setContentText("Velkomin/nn " + username + "!");
                alert.showAndWait();

                // Fara í Welcome.fxml
                goToWelcome();
            } else {
                // Lykilorðið er rangt.
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Sign In Mistókst");
                alert.setHeaderText(null);
                alert.setContentText("Rangt lykilorð. Reyndu aftur.");
                alert.showAndWait();
            }
        } else {
            // Notandi fannst ekki í gagnagrunninum.
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Notandi Ekki Finndur");
            alert.setHeaderText(null);
            alert.setContentText("Notandi með þetta notendanafn fannst ekki. Vinsamlegast notaðu Sign Up til að búa til aðgang.");
            alert.showAndWait();
        }
    }

    /**
     * Event handler fyrir Sign Up takkan.
     */
    @FXML
    public void handleSignUp() {
        String username = signupUsernameField.getText();
        String password = signupPasswordField.getText();
        String email = signupEmailField.getText();

        // Villutékkun: skoða að allir reitir séu fyllt út.
        if (username == null || username.trim().isEmpty() ||
                password == null || password.trim().isEmpty() ||
                email == null || email.trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Sign Up Mistókst");
            alert.setHeaderText(null);
            alert.setContentText("Vinsamlegast sláðu inn notendanafn, lykilorð og tölvupóst.");
            alert.showAndWait();
            return;
        }

        //Búum til nýtt user
        user = new User("0",username,password,"");

        if (user != null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Sign Up Mistókst");
            alert.setHeaderText(null);
            alert.setContentText("Notandi með þetta notendanafn er þegar til.");
            alert.showAndWait();
            return;
        }

        // Búa til einstakt userId fyrir nýja skráningu
        String userId = UUID.randomUUID().toString();
        User newUser = new User(userId, username, password, email);

        // Vistum notandann í gagnagrunninum.
        boolean inserted = true;
        if (inserted) {
            Session.getInstance().setCurrentUser(newUser);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sign Up Tókst");
            alert.setHeaderText(null);
            alert.setContentText("Skráning tókst! Velkomin/nn " + username + "!");
            alert.showAndWait();

            // Fara í Welcome.fxml
            goToWelcome();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sign Up Mistókst");
            alert.setHeaderText(null);
            alert.setContentText("Ekki tókst að búa til aðgang. Reyndu aftur síðar.");
            alert.showAndWait();
        }
    }

    /**
     * Aðferð til að hlaða Welcome.fxml og skipta yfir á nýja scene.
     */
    private void goToWelcome() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/ui/Welcome.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) signinUsernameField.getScene().getWindow();
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
