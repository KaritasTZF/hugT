package software;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Database db = new Database();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/SearchView.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("The Hotel Search!!");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
