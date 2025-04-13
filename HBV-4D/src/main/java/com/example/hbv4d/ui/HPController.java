package com.example.hbv4d.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Controller for the homepage if a user is not logged into the system
 */
public class HPController {
    private static final String TOURS_PATH = "/com/example/hbv4d/tours-view.fxml";
    private static final String LOGIN_PATH = "/com/example/hbv4d/login-view.fxml";

    @FXML
    public Label fxLoggedIn;


    @FXML
    public void initialize(){
        fxLoggedIn.setText(" ");
    }

    /**
     * Switches scenes to Login
     */
    @FXML
    public void onLogin() throws Exception {
        Application.switchScene(LOGIN_PATH);
    }

    /**
     * Switches scenes to Browse Tours
     */
    @FXML
    public void onBrowseTours() throws Exception {
        Application.switchScene(TOURS_PATH);
    }
}
