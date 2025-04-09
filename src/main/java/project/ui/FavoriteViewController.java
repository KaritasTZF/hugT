package project.ui;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;
import project.Controller.FavoriteController;
import project.Model.Trip;

public class FavoriteViewController {

    @FXML
    private Label favoriteLabel;

    @FXML
    private ListView<String> favoriteTripsList;
    // Hér gæti líka verið: ListView<Trip> ef þú vilt sýna Trip-hluti beint
    // en þá þarftu að skilgreina CellFactory eða toString() til að birta texta

    private FavoriteController favoriteController;

    /**
     * Kallar á þetta þegar þú ert búinn að búa til FavoriteViewController gegnum FXMLLoader
     */
    public void setFavoriteController(FavoriteController favoriteController) {
        this.favoriteController = favoriteController;
        updateFavoriteTrips();
    }

    /**
     * Uppfærir ListView til að birta núverandi uppáhaldsferðir
     */
    public void updateFavoriteTrips() {
        if (favoriteController == null) {
            return;
        }
        favoriteTripsList.getItems().clear();
        for (Trip trip : favoriteController.getAllFavoriteTrips()) {
            // Birta til dæmis tripID eða einhverjar upplýsingar um ferðina
            favoriteTripsList.getItems().add("Ferð nr. " + trip.getTripID());
        }
    }
}
