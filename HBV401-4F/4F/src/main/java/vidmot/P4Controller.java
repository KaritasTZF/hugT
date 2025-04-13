package vidmot;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.util.HashMap;
import java.util.Map;

public class P4Controller {
    private Scene scene;
    public Button backButton, continueButton;
    
    // Map to store extra baggage count per passenger
    public Map<String, Integer> baggageCounts = new HashMap<>();
    // Map to store assistance choices per passenger (each mapped to a type and boolean value)
    public Map<String, Map<String, Boolean>> assistanceChoices = new HashMap<>();
    
    /**
     * Constructor that builds the extra options page.
     * 
     * @param passengerNames Array of passenger names for which extra options are collected.
     */
    public P4Controller(String[] passengerNames) {
        Label titleLabel = new Label("Extra Baggage and Special Assistance");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.TOP_CENTER);
        
        // Baggage section
        Label baggageLabel = new Label("Extra Baggage:");
        VBox baggageBox = createBaggageBox(passengerNames);
        
        // Assistance section
        Label assistanceLabel = new Label("Special Assistance:");
        VBox assistanceBox = createAssistanceBox(passengerNames);
        
        // Navigation buttons
        backButton = new Button("Back");
        continueButton = new Button("Continue");
        HBox navButtons = new HBox(20, backButton, continueButton);
        navButtons.setAlignment(Pos.CENTER);
        
        layout.getChildren().addAll(titleLabel, baggageLabel, baggageBox, assistanceLabel, assistanceBox, navButtons);
        scene = new Scene(layout, 600, 500);
    }
    
    /**
     * Creates a VBox containing the baggage selection rows for each passenger.
     */
    private VBox createBaggageBox(String[] passengerNames) {
        VBox baggageBox = new VBox(10);
        for (String name : passengerNames) {
            HBox row = new HBox(10);
            row.setAlignment(Pos.CENTER_LEFT);
            Label nameLabel = new Label(name);
            Button minusBtn = new Button("-");
            Label countLabel = new Label("0");
            Button plusBtn = new Button("+");
            
            // Initialize baggage count to 0 for this passenger
            baggageCounts.put(name, 0);
            
            minusBtn.setOnAction(e -> {
                int count = baggageCounts.get(name);
                if (count > 0) {
                    count--;
                    baggageCounts.put(name, count);
                    countLabel.setText(String.valueOf(count));
                }
            });
            plusBtn.setOnAction(e -> {
                int count = baggageCounts.get(name);
                count++;
                baggageCounts.put(name, count);
                countLabel.setText(String.valueOf(count));
            });
            
            row.getChildren().addAll(nameLabel, minusBtn, countLabel, plusBtn);
            baggageBox.getChildren().add(row);
        }
        return baggageBox;
    }
    
    /**
     * Creates a VBox containing the special assistance checkboxes for each passenger.
     */
    private VBox createAssistanceBox(String[] passengerNames) {
        VBox assistanceBox = new VBox(10);
        String[] assistanceTypes = {"Nut Allergy", "Wheelchair", "Blind", "Deaf"};
        
        for (String assistance : assistanceTypes) {
            HBox row = new HBox(10);
            row.setAlignment(Pos.CENTER_LEFT);
            Label typeLabel = new Label(assistance);
            row.getChildren().add(typeLabel);
            
            // For each passenger, add a checkbox for this assistance type.
            for (String name : passengerNames) {
                CheckBox cb = new CheckBox(name);
                row.getChildren().add(cb);
                
                // Initialize assistance choices map if not already present.
                assistanceChoices.putIfAbsent(name, new HashMap<>());
                assistanceChoices.get(name).put(assistance, false);
                
                cb.setOnAction(e -> assistanceChoices.get(name).put(assistance, cb.isSelected()));
            }
            assistanceBox.getChildren().add(row);
        }
        return assistanceBox;
    }
    
    /**
     * Returns the Scene for this page.
     */
    public Scene getScene() {
        return scene;
    }
}
