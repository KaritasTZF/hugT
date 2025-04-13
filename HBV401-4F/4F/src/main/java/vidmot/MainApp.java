package vidmot;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.util.Map;

public class MainApp extends Application {
    private Stage primaryStage;
    
    // Shared data across pages
    private String selectedTripType;
    private String selectedDepart;
    private String selectedArrive;
    private LocalDate selectedDepartureDate;
    private LocalDate selectedReturnDate;
    private int passengerCount;
    
    // Flight selections
    private String selectedOutboundFlight;
    private String selectedOutboundClass;
    private String selectedReturnFlight;
    private String selectedReturnClass;
    
    // Flight summary
    private String flightSummary;
    
    // Passenger info
    private String[] passengerNames;
    
    // Extras
    private Map<String, Integer> baggageCounts;
    private Map<String, Map<String, Boolean>> assistanceChoices;
    
    // Selected seat from SeatMap
    private String selectedSeat;
    
    // Cache a single instance of SeatMapController
    private SeatMapController seatMapController;
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        showP1();
    }
    
    private void showP1() {
        P1Controller p1 = new P1Controller();
        p1.findFlightButton.setOnAction(e -> {
            selectedTripType = p1.tripTypeBox.getValue();
            selectedDepart = p1.departBox.getValue();
            selectedArrive = p1.arriveBox.getValue();
            selectedDepartureDate = p1.departDatePicker.getValue();
            selectedReturnDate = p1.returnDatePicker.getValue();
            passengerCount = p1.passengerCount;
            
            if (selectedDepart == null || selectedArrive == null || selectedDepartureDate == null) {
                showAlert("Please select departure, arrival, and departure date.");
            } else if (passengerCount < 1) {
                showAlert("Select at least 1 passenger.");
            } else {
                showP2();
            }
        });
        
        primaryStage.setScene(p1.getScene());
        primaryStage.setTitle("Flight Booking - Search");
        primaryStage.show();
    }
    
    private void showP2() {
        P2Controller p2 = new P2Controller(selectedDepart, selectedArrive, selectedTripType, selectedDepartureDate, selectedReturnDate);
        p2.backButton.setOnAction(e -> showP1());
        p2.continueButton.setOnAction(e -> {
            selectedOutboundFlight = p2.getSelectedOutboundFlight();
            selectedOutboundClass  = p2.getSelectedOutboundClass();
            if ("Two-way".equals(selectedTripType)) {
                selectedReturnFlight = p2.getSelectedReturnFlight();
                selectedReturnClass  = p2.getSelectedReturnClass();
            }
            if (selectedOutboundFlight == null || selectedOutboundClass == null ||
                ("Two-way".equals(selectedTripType) && (selectedReturnFlight == null || selectedReturnClass == null))) {
                showAlert("Please select flight(s) and class(es) for your journey.");
            } else {
                String outboundSummary = selectedOutboundFlight + "\nDate: " + selectedDepartureDate + "\nClass: " + selectedOutboundClass;
                if ("Two-way".equals(selectedTripType)) {
                    String returnSummary = selectedReturnFlight + "\nDate: " + selectedReturnDate + "\nClass: " + selectedReturnClass;
                    flightSummary = "Outbound:\n" + outboundSummary + "\n\nReturn:\n" + returnSummary;
                } else {
                    flightSummary = "Outbound:\n" + outboundSummary;
                }
                showSeatMap();
            }
        });
        
        primaryStage.setScene(p2.getScene());
        primaryStage.setTitle("Flight Booking - Flight Selection");
    }
    
    private void showSeatMap() {
        if (seatMapController == null) {
            seatMapController = new SeatMapController();
            seatMapController.getExitButton().setOnAction(e -> showP2());
            seatMapController.getSaveButton().setOnAction(e -> {
                selectedSeat = seatMapController.getSelectedSeat();
                if (selectedSeat == null) {
                    showAlert("Please select a seat.");
                } else {
                    showP3();
                }
            });
        }
        primaryStage.setScene(seatMapController.getScene());
        primaryStage.setTitle("Flight Booking - Seat Selection");
    }
    
    private void showP3() {
        P3Controller p3 = new P3Controller(flightSummary, passengerCount);
        p3.backButton.setOnAction(e -> showSeatMap());
        p3.continueButton.setOnAction(e -> {
            passengerNames = new String[passengerCount];
            for (int i = 0; i < passengerCount; i++) {
                String name = p3.nameFields.get(i).getText();
                if (name == null || name.isEmpty() || p3.dobPickers.get(i).getValue() == null) {
                    showAlert("Please fill in full name and date of birth for all passengers.");
                    return;
                }
                passengerNames[i] = name;
            }
            showP4();
        });
        
        primaryStage.setScene(p3.getScene());
        primaryStage.setTitle("Flight Booking - Passenger Information");
    }
    
    private void showP4() {
        P4Controller p4 = new P4Controller(passengerNames);
        p4.backButton.setOnAction(e -> showP3());
        p4.continueButton.setOnAction(e -> {
            baggageCounts = p4.baggageCounts;
            assistanceChoices = p4.assistanceChoices;
            showP5();
        });
        
        primaryStage.setScene(p4.getScene());
        primaryStage.setTitle("Flight Booking - Extras");
    }
    
    private void showP5() {
        P5Controller p5 = new P5Controller(flightSummary, passengerNames, baggageCounts, assistanceChoices);
        p5.backButton.setOnAction(e -> showP4());
        p5.confirmButton.setOnAction(e -> showAlert("Your booking is confirmed!"));
        
        primaryStage.setScene(p5.getScene());
        primaryStage.setTitle("Flight Booking - Overview");
    }
    
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING, message, ButtonType.OK);
        alert.showAndWait();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
