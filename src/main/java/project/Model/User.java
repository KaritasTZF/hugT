package project.Model;

import java.util.UUID;

public class User {
    private String userID;
    private String name;
    private String email;
    // private ArrayList<Trip> favoriteTrips;
    private String password;

    // Constructor to initialize User object
    public User(String userID, String name, String password,String email) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        // this.favoriteTrips = favoriteTrips;
        this.password = password;
    }

    public User(String name) {
        this.name = name;
        // Notaðu annaðhvort fast userId eða annað
        this.userID = UUID.randomUUID().toString();;  // eða búa til userId annars staðar
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //public ArrayList<Trip> getFavoriteTrips() {return this.favoriteTrips;}

    //public void setFavoriteTrips(ArrayList<Trip> favoriteTrips) {this.favoriteTrips = favoriteTrips;}
}
