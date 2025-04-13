package vidmot;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.List;

public class P1Controller {
    private Scene scene;
    
    // UI Components (public for access by main application)
    public ComboBox<String> departBox, arriveBox, tripTypeBox;
    public DatePicker departDatePicker, returnDatePicker;
    public ComboBox<Integer> passengerBox;
    public Button findFlightButton, plusButton, minusButton;
    
    // Data field for number of passengers (default is 1)
    public int passengerCount = 1;
    
    public P1Controller() {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
        
        Label titleLabel = createTitleLabel();
        initAirports();  // initializes departBox and arriveBox
        initTripType();
        initDatePickers();
        HBox passengerSelector = initPassengerSelector();
        
        findFlightButton = new Button("Find Flight");
        
        layout.getChildren().addAll(
            titleLabel,
            tripTypeBox,
            departBox,
            arriveBox,
            departDatePicker,
            returnDatePicker,
            new Label("Number of Passengers"),
            passengerSelector,
            findFlightButton
        );
        
        scene = new Scene(layout, 650, 500);
    }
    
    private Label createTitleLabel() {
        Label title = new Label("Welcome to Flight INC");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        return title;
    }
    
    private void initAirports() {
        // Load airports from the database using Tengja_gogn (assumed utility class)
        List<String> airports = Tengja_gogn.loadAirports();
        ObservableList<String> airportList = FXCollections.observableArrayList(airports);
        
        departBox = new ComboBox<>(airportList);
        departBox.setPromptText("Depart from");
        
        arriveBox = new ComboBox<>(airportList);
        arriveBox.setPromptText("Arrive to");
    }
    
    private void initTripType() {
        tripTypeBox = new ComboBox<>(FXCollections.observableArrayList("One-way", "Two-way"));
        tripTypeBox.setPromptText("Select trip type");
        tripTypeBox.getSelectionModel().select(0);
    }
    
    private void initDatePickers() {
        departDatePicker = new DatePicker();
        departDatePicker.setPromptText("Select departure date");
        
        returnDatePicker = new DatePicker();
        returnDatePicker.setPromptText("Select return date");
        returnDatePicker.setVisible(false);
        
        tripTypeBox.setOnAction(e -> {
            boolean isTwoWay = "Two-way".equals(tripTypeBox.getValue());
            returnDatePicker.setVisible(isTwoWay);
        });
    }
    
    private HBox initPassengerSelector() {
        passengerBox = new ComboBox<>(FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        passengerBox.setPromptText("Passengers");
        passengerBox.getSelectionModel().select(0);
        passengerBox.setOnAction(e -> passengerCount = passengerBox.getValue());
        
        plusButton = new Button("+");
        plusButton.setOnAction(e -> {
            if (passengerCount < 9) {
                passengerCount++;
                passengerBox.getSelectionModel().select(passengerCount - 1);
            }
        });
        
        minusButton = new Button("-");
        minusButton.setOnAction(e -> {
            if (passengerCount > 1) {
                passengerCount--;
                passengerBox.getSelectionModel().select(passengerCount - 1);
            }
        });
        
        HBox selector = new HBox(5, minusButton, passengerBox, plusButton);
        selector.setAlignment(Pos.CENTER);
        return selector;
    }
    
    public Scene getScene() {
        return scene;
    }
}
