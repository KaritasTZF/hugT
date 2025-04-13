package vidmot;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

public class P2Controller {
    private Scene scene;
    
    // Navigation buttons
    public Button backButton, continueButton;
    
    // Flight containers and date selectors
    public VBox outboundFlightsContainer, returnFlightsContainer;
    public HBox outboundDateSelector, returnDateSelector;
    
    // Flight selections (for outbound and return)
    private String selectedOutboundFlight = null;
    private String selectedOutboundClass = null;
    private String selectedReturnFlight = null;
    private String selectedReturnClass = null;
    
    // Data received from P1Controller
    private String from, to, tripType;
    private LocalDate departureDate, returnDate;
    
    public P2Controller(String from, String to, String tripType, LocalDate departureDate, LocalDate returnDate) {
        this.from = from;
        this.to = to;
        this.tripType = tripType;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        
        Label title = new Label("Available Flights");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        
        // Outbound flights setup
        Label outboundLabel = new Label("Outbound Flight");
        outboundLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        outboundFlightsContainer = new VBox(5);
        outboundFlightsContainer.setAlignment(Pos.CENTER);
        outboundDateSelector = createDateSelector(from, to, departureDate, outboundFlightsContainer, false);
        VBox outboundContainer = new VBox(5, outboundLabel, outboundDateSelector, outboundFlightsContainer);
        outboundContainer.setAlignment(Pos.CENTER);
        showFlightsForDate(outboundFlightsContainer, from, to, departureDate, false);
        
        VBox mainLayout = new VBox(10, title, outboundContainer);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setAlignment(Pos.TOP_CENTER);
        
        // Return flights section for Two-way trips
        if ("Two-way".equals(tripType) && returnDate != null) {
            Label returnLabel = new Label("Return Flight");
            returnLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
            returnFlightsContainer = new VBox(5);
            returnFlightsContainer.setAlignment(Pos.CENTER);
            returnDateSelector = createDateSelector(to, from, returnDate, returnFlightsContainer, true);
            VBox returnContainer = new VBox(5, returnLabel, returnDateSelector, returnFlightsContainer);
            returnContainer.setAlignment(Pos.CENTER);
            mainLayout.getChildren().add(returnContainer);
            showFlightsForDate(returnFlightsContainer, to, from, returnDate, true);
        }
        
        // Navigation buttons
        backButton = new Button("Back");
        continueButton = new Button("Continue");
        HBox navButtons = new HBox(10, backButton, continueButton);
        navButtons.setAlignment(Pos.CENTER);
        mainLayout.getChildren().add(navButtons);
        
        scene = new Scene(mainLayout, 750, 650);
    }
    
    /**
     * Creates a horizontal date selector for a range of dates around the selected date.
     * Each button displays the day abbreviation, day number, and flight count.
     */
    private HBox createDateSelector(String from, String to, LocalDate selectedDate, VBox flightContainer, boolean isReturn) {
        HBox dateSelector = new HBox(10);
        dateSelector.setAlignment(Pos.CENTER);
        for (int i = -3; i <= 3; i++) {
            LocalDate checkDate = selectedDate.plusDays(i);
            int flightCount = Tengja_gogn.countFlights(from, to, checkDate);
            String dayName = checkDate.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
            String btnText = dayName + " " + checkDate.getDayOfMonth();
            if (flightCount > 0) {
                btnText += " (" + flightCount + " flights)";
            }
            Button dateButton = new Button(btnText);
            if (flightCount == 0) {
                dateButton.setDisable(true);
            }
            dateButton.setOnAction(e -> {
                showFlightsForDate(flightContainer, from, to, checkDate, isReturn);
                dateSelector.getChildren().forEach(node -> node.setStyle(""));
                dateButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
            });
            if (checkDate.equals(selectedDate)) {
                dateButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
            }
            dateSelector.getChildren().add(dateButton);
        }
        return dateSelector;
    }
    
    /**
     * Retrieves flights for the given date and route, and populates the flight container.
     */
    private void showFlightsForDate(VBox flightContainer, String from, String to, LocalDate date, boolean isReturn) {
        flightContainer.getChildren().clear();
        List<String> flights = Tengja_gogn.getFlights(from, to, date);
        for (String flight : flights) {
            HBox flightRow = new HBox(10);
            flightRow.setPadding(new Insets(5));
            Label flightLabel = new Label(flight);
            flightLabel.setMinWidth(300);
            
            Button standardBtn = new Button("Standard");
            Button economyBtn = new Button("Economy");
            Button firstClassBtn = new Button("First Class");
            
            standardBtn.setOnAction(e -> selectFlight(flight, "Standard", isReturn, standardBtn, economyBtn, firstClassBtn));
            economyBtn.setOnAction(e -> selectFlight(flight, "Economy", isReturn, standardBtn, economyBtn, firstClassBtn));
            firstClassBtn.setOnAction(e -> selectFlight(flight, "First Class", isReturn, standardBtn, economyBtn, firstClassBtn));
            
            flightRow.getChildren().addAll(flightLabel, standardBtn, economyBtn, firstClassBtn);
            flightContainer.getChildren().add(flightRow);
        }
    }
    
    /**
     * Handles the flight selection: unstyles all buttons in the container, stores the selected flight and class,
     * then highlights the selected button.
     */
    private void selectFlight(String flight, String flightClass, boolean isReturn, 
                              Button standardBtn, Button economyBtn, Button firstClassBtn) {
        // Unstyle all flight buttons in the container.
        VBox container = (VBox) standardBtn.getParent().getParent();
        for (javafx.scene.Node rowNode : container.getChildren()) {
            if (rowNode instanceof HBox) {
                for (javafx.scene.Node node : ((HBox) rowNode).getChildren()) {
                    if (node instanceof Button) {
                        node.setStyle("");
                    }
                }
            }
        }
        
        // Save the flight selection.
        if (isReturn) {
            selectedReturnFlight = flight;
            selectedReturnClass = flightClass;
        } else {
            selectedOutboundFlight = flight;
            selectedOutboundClass = flightClass;
        }
        
        // Highlight the selected button.
        switch (flightClass) {
            case "Standard":
                standardBtn.setStyle("-fx-background-color: green; -fx-text-fill: white;");
                break;
            case "Economy":
                economyBtn.setStyle("-fx-background-color: green; -fx-text-fill: white;");
                break;
            case "First Class":
                firstClassBtn.setStyle("-fx-background-color: green; -fx-text-fill: white;");
                break;
        }
        System.out.println("Selected flight: " + flight + " | Class: " + flightClass);
    }
    
    public Scene getScene() {
        return scene;
    }
    
    // Getters for the selected flight details.
    public String getSelectedOutboundFlight() {
        return selectedOutboundFlight;
    }
    
    public String getSelectedOutboundClass() {
        return selectedOutboundClass;
    }
    
    public String getSelectedReturnFlight() {
        return selectedReturnFlight;
    }
    
    public String getSelectedReturnClass() {
        return selectedReturnClass;
    }
}
