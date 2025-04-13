package vidmot;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/seatmap.fxml"));
        Scene scene = new Scene(loader.load());
        scene.getStylesheets().add(getClass().getResource("/css/seatStyles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Airplane Seat Map");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
