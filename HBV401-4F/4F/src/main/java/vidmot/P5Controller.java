package vidmot;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.util.Map;

public class P5Controller {
    private Scene scene;
    public Button backButton, confirmButton;
    
    /**
     * Constructs the overview page.
     * 
     * @param flightSummary     The summary string for the selected flight details.
     * @param passengerNames    Array of passenger names.
     * @param baggageCounts     Map linking each passenger name to their extra baggage count.
     * @param assistanceChoices Map linking each passenger name to a map of assistance types and whether they are selected.
     */
    public P5Controller(String flightSummary,
                        String[] passengerNames,
                        Map<String, Integer> baggageCounts,
                        Map<String, Map<String, Boolean>> assistanceChoices) {
        // Flight summary label at the top
        Label summaryLabel = new Label("Flight Summary:\n" + flightSummary);
        summaryLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        summaryLabel.setWrapText(true);
        
        // Create a container to hold ticket details for each passenger
        VBox ticketsBox = new VBox(10);
        for (String name : passengerNames) {
            int bags = baggageCounts.get(name);
            Map<String, Boolean> assistMap = assistanceChoices.get(name);
            StringBuilder assistanceString = new StringBuilder();
            
            // Build a comma-separated string of selected assistance types
            for (Map.Entry<String, Boolean> entry : assistMap.entrySet()) {
                if (entry.getValue()) {
                    if (assistanceString.length() > 0) {
                        assistanceString.append(", ");
                    }
                    assistanceString.append(entry.getKey());
                }
            }
            if (assistanceString.length() == 0) {
                assistanceString.append("None");
            }
            
            Label ticketLabel = new Label(
                "Ticket for " + name + ":\n" +
                "Baggage: " + bags + "\n" +
                "Assistance: " + assistanceString
            );
            ticketLabel.setWrapText(true);
            ticketsBox.getChildren().add(ticketLabel);
        }
        
        // Navigation buttons: Back and Confirm Booking
        backButton = new Button("Back");
        confirmButton = new Button("Confirm Booking");
        HBox navButtons = new HBox(15, backButton, confirmButton);
        navButtons.setAlignment(Pos.CENTER);
        
        // Main layout container
        VBox layout = new VBox(15, summaryLabel, ticketsBox, navButtons);
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
