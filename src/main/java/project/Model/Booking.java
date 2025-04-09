package project.Model;

public class Booking {
    private String userID;
    private Trip trip;
    private int bookingID;
    private String status;
    private int confirmationNr;

    //Constructor
    public Booking(
      String userID,
      Trip trip,
      int bookingID,
      String status,
      int confirmationNr
    ) {
      this.userID = userID;
      this.trip = trip;
      this.bookingID = bookingID;
      this.status = status;
      this.confirmationNr = confirmationNr;

    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Trip getTrip() {
        return this.trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public int getBookingID() {
        return this.bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getConfirmationNr() {
        return this.confirmationNr;
    }

    public void setConfirmationNr(int confirmationNr) {
        this.confirmationNr = confirmationNr;
    }

    @Override
    public String toString() {
        // Þú ræður textanum
        return "Booking #" + bookingID + " (Trip: " + trip.getTripID() + ")";
    }


    //methods: confirmBooking(): void
    
    //methods: getBookingPrice(): double
}
