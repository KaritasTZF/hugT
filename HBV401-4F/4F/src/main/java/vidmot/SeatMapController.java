package vidmot;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import vinnsla.Seat;
import vinnsla.SeatStatus;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SeatMapController {

    @FXML
    private GridPane seatGrid;
    
    @FXML
    private Button saveButton; // Acts as "continue"
    
    @FXML
    private Button exitButton; // Acts as "back"
    
    private Button[][] seatButtons = new Button[10][4];
    private Seat[][] seatsModel = new Seat[10][4];
    
    private int selectedRow = -1;
    private int selectedCol = -1;
    
    private boolean selectionLocked = false;
    
    private Scene scene;
    
    // Constructor: load FXML, set this as the controller, and add the CSS stylesheet.
    public SeatMapController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/seatmap.fxml"));
            // Set this instance as the controller because fx:controller has been removed from the FXML.
            loader.setController(this);
            VBox root = loader.load();
            scene = new Scene(root);
            // Add CSS stylesheet to the scene.
            scene.getStylesheets().add(getClass().getResource("/css/seatStyles.css").toExternalForm());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void initialize() {
        System.out.println("Initializing SeatMapController...");
        System.out.println("seatGrid has " + seatGrid.getChildren().size() + " children.");
        
        // Map seat buttons from seatGrid into our 10x4 array
        for (javafx.scene.Node node : seatGrid.getChildren()) {
            if (node instanceof Button) {
                Button btn = (Button) node;
                Integer gridRow = GridPane.getRowIndex(btn);
                Integer gridCol = GridPane.getColumnIndex(btn);
                if (gridRow == null) gridRow = 0;
                if (gridCol == null) gridCol = 0;
                // Skip column index 2 if it is reserved for spacing.
                if (gridCol == 2) continue;
                int logicalCol = (gridCol < 2) ? gridCol : gridCol - 1;
                int logicalRow = gridRow;
                if (logicalRow < 10 && logicalCol < 4) {
                    Seat seat = new Seat(logicalRow, logicalCol);
                    seatsModel[logicalRow][logicalCol] = seat;
                    seatButtons[logicalRow][logicalCol] = btn;
                    btn.setUserData(new int[]{logicalRow, logicalCol});
                    btn.setOnAction(this::handleSeatSelection);
                }
            }
        }
        
        // Build list of valid seats.
        Random random = new Random();
        List<int[]> validSeats = new ArrayList<>();
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 4; col++) {
                if (seatButtons[row][col] != null) {
                    validSeats.add(new int[]{row, col});
                }
            }
        }
        if (validSeats.isEmpty()) {
            throw new IllegalStateException("No seat buttons were found in the GridPane!");
        }
        
        // Randomly select an initial seat.
        int[] pos = validSeats.get(random.nextInt(validSeats.size()));
        selectedRow = pos[0];
        selectedCol = pos[1];
        seatsModel[selectedRow][selectedCol].setStatus(SeatStatus.SELECTED);
        
        // Randomly mark a number of seats as UNAVAILABLE (between 10 and 25), skipping the selected seat.
        int numUnavailable = random.nextInt(16) + 10;
        int count = 0;
        int attempts = 0;
        int maxAttempts = 1000; // safeguard to prevent infinite loop
        while (count < numUnavailable && attempts < maxAttempts) {
            int[] p = validSeats.get(random.nextInt(validSeats.size()));
            int row = p[0], col = p[1];
            if (row == selectedRow && col == selectedCol) {
                attempts++;
                continue;
            }
            if (seatsModel[row][col].getStatus() != SeatStatus.UNAVAILABLE) {
                seatsModel[row][col].setStatus(SeatStatus.UNAVAILABLE);
                count++;
            }
            attempts++;
        }
        if (attempts >= maxAttempts) {
            System.out.println("Warning: Maximum attempts reached while marking seats as UNAVAILABLE. Marked " + count + " seats.");
        }
        
        updateSeatStates();
        System.out.println("SeatMapController initialization complete.");
    }
    
    private void handleSeatSelection(ActionEvent event) {
        if (selectionLocked) return;
        Button clickedButton = (Button) event.getSource();
        int[] pos = (int[]) clickedButton.getUserData();
        int row = pos[0], col = pos[1];
        Seat clickedSeat = seatsModel[row][col];
        if (clickedSeat.getStatus() == SeatStatus.UNAVAILABLE || (row == selectedRow && col == selectedCol)) {
            return;
        }
        // Reset previous seat and select new seat.
        seatsModel[selectedRow][selectedCol].setStatus(SeatStatus.AVAILABLE);
        clickedSeat.setStatus(SeatStatus.SELECTED);
        selectedRow = row;
        selectedCol = col;
        updateSeatStates();
    }
    
    private void updateSeatStates() {
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 4; col++) {
                Button btn = seatButtons[row][col];
                Seat seat = seatsModel[row][col];
                btn.setText(seat.getSeatLabel());
                btn.getStyleClass().removeAll("seat-available", "seat-selected", "seat-unavailable");
                if (seat.getStatus() == SeatStatus.SELECTED) {
                    btn.getStyleClass().add("seat-selected");
                } else if (seat.getStatus() == SeatStatus.AVAILABLE) {
                    btn.getStyleClass().add("seat-available");
                } else if (seat.getStatus() == SeatStatus.UNAVAILABLE) {
                    btn.getStyleClass().add("seat-unavailable");
                }
            }
        }
    }
    
    @FXML
    private void handleSave(ActionEvent event) {
        String selectedSeatId = seatsModel[selectedRow][selectedCol].getSeatLabel();
        try (FileWriter writer = new FileWriter("selectedSeat.txt")) {
            writer.write(selectedSeatId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        selectionLocked = true;
        saveButton.setDisable(true);
    }
    
    @FXML
    private void handleExit(ActionEvent event) {
        Platform.exit();
    }
    
    // Public getter for the Scene.
    public Scene getScene() {
        return scene;
    }
    
    // Public getter for the selected seat label.
    public String getSelectedSeat() {
        return seatsModel[selectedRow][selectedCol].getSeatLabel();
    }
    
    // Public getters for the navigation buttons.
    public Button getSaveButton() {
        return saveButton;
    }
    
    public Button getExitButton() {
        return exitButton;
    }
}
