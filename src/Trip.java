package hugT.src;

import java.util.ArrayList; //Til þess að geyma lista af object okkar

public class Trip {
    private String tripID;
    private int people;
    private int days;
    private String startDate;
    private String endDate;
    private double price;
    private ArrayList<Flight> flightItems; //Listi af Flight, listinn heitir flightItems (gamalt frá TripItems)
    private ArrayList<Hotel> hotelItems; //Listi af Hotel, listinn heitir hotelItems (gamalt frá TripItems)
    private ArrayList<DayTour> dayTourItems; //Listi af DayTour, listinn heitir dayTourItems (gamalt frá TripItems)

    //Constructor to initialize Trip object
    public Trip() {

    }

    //Methods: getterar ------------------------------------

    //skilar fjöldi manns
    public int getPeople() {
        return this.people;
    }

    //skilar fjöldi dags.
    public int getDays() {
        return this.days;
    }

    //skilar verð
    public double getPrice() {
        return this.price;
    }

    //Kannski fleiri gettera ef þarf
    //Eru fleiri methods hér sem eru ekki getterar? held ekki
}
