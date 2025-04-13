package com.example.hbv4d.ui;

import com.example.hbv4d.utils.InfoDialog;
import com.example.hbv4d.objects.User;
import com.example.hbv4d.objects.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * Controller for logging a user in
 */
public class UserController {
    private static final String HOMEPAGE_PATH = "/com/example/hbv4d/HP-view.fxml";
    private static final String LOGGED_PATH = "/com/example/hbv4d/HPLogged-view.fxml";

    @FXML
    public TextField fxUsernameInput;

    /**
     * Logs a user in
     * Goes back to homepage scene when user is logged in
     */
    @FXML
    public void onLogin() throws IOException {
        String username = fxUsernameInput.getText();
        User user = UserDAO.getUser(username);

        if (user == null) {
            InfoDialog alert = new InfoDialog("Login failed", "User not found");
            alert.show();
            return;
        }

        User.setLoggedIn(user);
        Application.setLoggedIn(true);
        Application.switchScene(LOGGED_PATH);
    }

    /**
     * Switches scenes back to the homepage
     */
    @FXML
    public void onBack() throws Exception {
        Application.switchScene(HOMEPAGE_PATH);
    }

}
