package project.Controller;

import project.Model.Trip;
import project.Model.User;

import java.util.ArrayList;

public class FavoriteController {

    private User user;
    private ArrayList<Trip> favoritedTrips;

    public FavoriteController(User user) {
        this.user = user;
        this.favoritedTrips = user.getFavoriteTrips();
    }

    // Bæta ferð í uppáhalds
    public void addTripToFavorites(Trip trip) {
        if (this.user.getFavoriteTrips() != null) {
            this.user.getFavoriteTrips().add(trip);
            System.out.println("Trip added to favorites: " + trip.getTripID());
        }
    }

    // Fjarlægja ferð úr uppáhalds
    public void removeTripFromFavorites(Trip trip) {
        if (this.user.getFavoriteTrips() != null && this.user.getFavoriteTrips().contains(trip)) {
            this.user.getFavoriteTrips().remove(trip);
            System.out.println("Trip removed from favorites: " + trip.getTripID());
        } else {
            System.out.println("Trip not found in favorites.");
        }
    }

}
