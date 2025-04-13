package project.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/project/ui/Auth.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("TripAdmirer");
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
