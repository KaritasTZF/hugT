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

    //Event handler fyrir Sign In takkan.
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
        //Búum til nýtt user - dummy info fyrir demo
        user = new User("0",username,password,"something@hi.is");

        if (user != null) {
            // Notandi fannst, athugum hvort lykilorðið samsvari.

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sign In Tókst");
            alert.setHeaderText(null);
            alert.setContentText("Velkomin/nn " + username + "!");
            alert.showAndWait();

            // Fara í Welcome.fxml
            goToWelcome();

        }
    }

    //Event handler fyrir Sign Up takkan.
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

        //Búum til nýtt user - dummy info fyrir demo
        user = new User("0",username,"1234","");

        // Búa til einstakt userId fyrir nýja skráningu
        //String userId = UUID.randomUUID().toString();
        //User newUser = new User(userId, username, password, email);

        //user = newUser;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sign Up Tókst");
        alert.setHeaderText(null);
        alert.setContentText("Skráning tókst! Velkomin/nn " + username + "!");
        alert.showAndWait();

        // Fara í Welcome.fxml
        goToWelcome();

    }

    //Aðferð til að hlaða Welcome.fxml og skipta yfir á nýja scene.
    private void goToWelcome() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/ui/Welcome.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) signinUsernameField.getScene().getWindow();

            //pass current User through to new scene
            WelcomeController controller = loader.getController();
            controller.setUser(user);

            //CSS
            Scene scene = new Scene(root);
            stage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
            stage.show();
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
