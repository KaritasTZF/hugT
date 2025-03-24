package main.Model;

import java.util.ArrayList; //Til þess að geyma lista af object okkar

public class FavoritedTrips {
    private int userID;
    private ArrayList<Trip> trips;

    // Constructor
    public FavoritedTrips(int userID,ArrayList<Trip> trips) {
        this.userID = userID;
        this.trips = trips;

    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public ArrayList<Trip> getTrips() {
        return this.trips;
    }

    public void setTrips(ArrayList<Trip> trips) {
        this.trips = trips;
    }

    // methods: add og remove trip.
}
