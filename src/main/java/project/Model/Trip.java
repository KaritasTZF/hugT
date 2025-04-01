package project.Model;

import java.time.LocalDate;
import java.util.ArrayList; //Til þess að geyma lista af object okkar

public class Trip {
    private String tripID=null;
    private int people=0;
    private int days=0;
    private LocalDate startDate=null;
    private LocalDate endDate=null;
    private int price=0;
    private ArrayList<Flight> flightItems=null; //Listi af Flight,listinn heitir flightItems (gamalt frá TripItems)
    private ArrayList<Hotel> hotelItems=null; //Listi af Hotel, listinn heitir hotelItems (gamalt frá TripItems)
    private ArrayList<DayTour> dayTourItems=null; //Listi af DayTour, listinn heitir dayTourItems (gamalt frá TripItems)

    //Constructor to initialize Trip object
    public Trip(
      String tripID,
      int people,
      int days,
      LocalDate startDate,
      LocalDate endDate,
      int price,
      ArrayList<Flight> flightItems,
      ArrayList<Hotel> hotelItems,
      ArrayList<DayTour> dayTourItems
    ) {
        this.tripID = tripID;
        this.people = people;
        this.days = days;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.flightItems = flightItems;
        this.hotelItems = hotelItems;
        this.dayTourItems = dayTourItems;
    }

    //Methods: getterar ------------------------------------
    // skilar tripID
    public String getTripID() {
        return this.tripID;
    }

    public void setTripID(String tripID) {
        this.tripID = tripID;
    }

    //skilar fjöldi manns
    public int getPeople() {
        return this.people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    //skilar fjöldi dags.
    public int getDays() {
        return this.days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    //skilar verð
    public double getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public ArrayList<Flight> getFlightItems() {
        return this.flightItems;
    }

    public void setFlightItems(ArrayList<Flight> flightItems) {
        this.flightItems = flightItems;
    }

    public ArrayList<Hotel> getHotelItems() {
        return this.hotelItems;
    }

    public void setHotelItems(ArrayList<Hotel> hotelItems) {
        this.hotelItems = hotelItems;
    }

    public ArrayList<DayTour> getDayTourItems() {
        return this.dayTourItems;
    }

    public void setDayTourItems(ArrayList<DayTour> dayTourItems) {
        this.dayTourItems = dayTourItems;
    }
}
