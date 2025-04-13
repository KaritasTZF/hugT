package com.example.hbv4d.ui;

import com.example.hbv4d.objects.Tour;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.DriverManager;
import java.util.logging.Logger;

public class Application extends javafx.application.Application {
    private static Stage primaryStage;
    private static final String HOMEPAGE_PATH = "/com/example/hbv4d/HP-view.fxml";

    private static boolean loggedIn = false;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        switchScene(HOMEPAGE_PATH);
        dbConn();
    }

    public static void setLoggedIn(boolean status) {
        loggedIn = status;
    }


    /**
     * Connects the application to a Database
     */
    public void dbConn() {
        try {
            Class.forName("org.sqlite.JDBC");
            DriverManager.registerDriver(new org.sqlite.JDBC());

        } catch (Exception e) {
            Logger.getAnonymousLogger().severe(e.getMessage());
        }
    }

    /**
     * Helper function to switch scenes in tour booking application
     * @param fxmlFile fxml file for different views
     */
    public static void switchScene(String fxmlFile) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(fxmlFile));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Helper function to switch to booking scene with tour information
     * @param bookingViewFile fxml file for booking view
     * @param tour tour to book
     */
    public static void bookingScene(String bookingViewFile, Tour tour) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(bookingViewFile));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        BookingController controller = fxmlLoader.getController();

        controller.setTour(tour);
        controller.updateUserInterface();

        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}

