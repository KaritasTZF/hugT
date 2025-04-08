package project.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class Trip {
    private String tripID=null;
    private int people=0;
    private int days=0;
    private LocalDate startDate=null;
    private LocalDate endDate=null;
    private int price=0;
    private ArrayList<Flight> flightItems=new ArrayList<>(); //Listi af Flight,listinn heitir flightItems (gamalt frá TripItems)
    private ArrayList<Hotel> hotelItems=new ArrayList<>(); //Listi af Hotel, listinn heitir hotelItems (gamalt frá TripItems)
    private ArrayList<DayTour> dayTourItems=new ArrayList<>(); //Listi af DayTour, listinn heitir dayTourItems (gamalt frá TripItems)

    //Constructor to initialize Trip object
    public Trip() {
        this.tripID = UUID.randomUUID().toString();
    }

    //Methods: getterar ------------------------------------
    // skilar tripID
    public String getTripID() {
        return this.tripID;
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
        Integer totalPrice = 0;
        for (Flight f: flightItems) {
            totalPrice += f.getPrice();
        }
        for (Hotel h: hotelItems) {
            totalPrice +=h.getPrice();
        }
        for (DayTour dt: dayTourItems) {
            totalPrice += dt.getPrice();
        }
        return totalPrice;
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

    public void addFlightItem(Flight flight) {this.flightItems.add(flight);}
    public void addHotelItem(Hotel hotel) {this.hotelItems.add(hotel);}
    public void addDayTourItem(DayTour dayTour) {this.dayTourItems.add(dayTour);}
}
