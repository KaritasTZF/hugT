package vidmot;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import java.util.List;

public class P3Controller {
    private Scene scene;
    
    // Navigation buttons (public for access from Main application)
    public Button backButton, continueButton;
    
    // Lists to store passenger input fields
    public List<TextField> nameFields = new ArrayList<>();
    public List<DatePicker> dobPickers = new ArrayList<>();
    
    /**
     * Constructor for P3Controller.
     * 
     * @param flightSummary  A summary string of the selected flight details.
     * @param passengerCount The number of passengers whose info should be collected.
     */
    public P3Controller(String flightSummary, int passengerCount) {
        // Flight summary label at the top
        Label summaryLabel = new Label("Flight Summary:\n" + flightSummary);
        summaryLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        summaryLabel.setWrapText(true);
        
        // Container for passenger information fields
        VBox passengerInfoBox = new VBox(10);
        passengerInfoBox.setAlignment(Pos.CENTER);
        
        // Create a row (HBox) for each passenger's info
        for (int i = 1; i <= passengerCount; i++) {
            HBox row = new HBox(10);
            row.setAlignment(Pos.CENTER);
            Label label = new Label("Passenger " + i + ":");
            TextField nameField = new TextField();
            nameField.setPromptText("Full Name");
            DatePicker dobPicker = new DatePicker();
            dobPicker.setPromptText("Date of Birth");
            
            row.getChildren().addAll(label, nameField, dobPicker);
            passengerInfoBox.getChildren().add(row);
            
            nameFields.add(nameField);
            dobPickers.add(dobPicker);
        }
        
        // Navigation buttons: Back and Continue
        backButton = new Button("Back");
        continueButton = new Button("Continue");
        HBox navButtons = new HBox(15, backButton, continueButton);
        navButtons.setAlignment(Pos.CENTER);
        
        // Main layout
        VBox layout = new VBox(15, summaryLabel, passengerInfoBox, navButtons);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
        
        scene = new Scene(layout, 600, 500);
    }
    
    /**
     * Returns the Scene for this page.
     * 
     * @return the Scene built by this controller.
     */
    public Scene getScene() {
        return scene;
    }
}
