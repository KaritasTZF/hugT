package project.Controller;

import project.Model.FavoritedTrips;
import project.Model.Trip;

import java.util.ArrayList;

public class FavoriteController {

    private final UserController userController;
    private FavoritedTrips favoritedTrips;  // Ein uppáhalds-skrá (lista) fyrir einn user
    private final ArrayList<Trip> favoriteTrips = new ArrayList<>();

    public FavoriteController(UserController userController) {
        this.userController = userController;
        this.favoritedTrips = null;
    }

    // Bæta ferð í uppáhalds
    public void addTripToFavorites(Trip trip) {
        if (this.favoritedTrips != null) {
            this.favoritedTrips.getTrips().add(trip);
            System.out.println("Trip added to favorites: " + trip.getTripID());
        }
    }

    // Fjarlægja ferð úr uppáhalds
    public void removeTripFromFavorites(Trip trip) {
        if (this.favoritedTrips != null && this.favoritedTrips.getTrips().contains(trip)) {
            this.favoritedTrips.getTrips().remove(trip);
            System.out.println("Trip removed from favorites: " + trip.getTripID());
        } else {
            System.out.println("Trip not found in favorites.");
        }
    }

    // Sækir lista af uppáhaldsferðum fyrir current user
    public ArrayList<Trip> getAllFavoriteTrips() {
        if (this.favoritedTrips != null) {
            return this.favoritedTrips.getTrips();
        }
        return new ArrayList<>(); // skila tómu ef ekkert er til
    }
}
