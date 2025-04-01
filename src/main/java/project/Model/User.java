package project.Model;

import java.util.ArrayList;

public class User {
    private String userID;
    private String name;
    private String email;
    private ArrayList<Trip> favoriteTrips;

    // Constructor to initialize User object
    public User(String userID, String name, String email, ArrayList<Trip> favoriteTrips) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.favoriteTrips = favoriteTrips;
    }

    // method: getBooked(): List<Booking>
    // method: getFavorite(): FavoritedTrips

    public String getUserID() {
        return this.userID;

    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Trip> getFavoriteTrips() {
        return this.favoriteTrips;
    }

    public void setFavoriteTrips(ArrayList<Trip> favoriteTrips) {
        this.favoriteTrips = favoriteTrips;
    }
}
