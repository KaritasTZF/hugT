package project.Model;

import project.Controller.BookingController;

import java.util.ArrayList;

public class User {
    private String userID;
    private String name;
    private String email;
    private ArrayList<Booking> bookedTrips = new ArrayList<>();
    private String password;
    private BookingController bookingController;

    // Constructor to initialize User object
    public User(String userID, String name, String password,String email) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public void setBookingController(BookingController bookingController) {
        this.bookingController = bookingController;
    }

    public ArrayList<Booking> getBookedTrips() {
        return bookedTrips;
    }

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

}
