package project.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class Trip {
    private final String tripID;
    private int people;
    private int days;
    private LocalDate startDate;
    private LocalDate endDate;
    private int price=0;
    private final ArrayList<Flight> flightItems=new ArrayList<>(); //Listi af Flight,listinn heitir flightItems (gamalt frá TripItems)
    private final ArrayList<Hotel> hotelItems=new ArrayList<>(); //Listi af Hotel, listinn heitir hotelItems (gamalt frá TripItems)
    private final ArrayList<DayTour> dayTourItems=new ArrayList<>(); //Listi af DayTour, listinn heitir dayTourItems (gamalt frá TripItems)

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
        return days;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    //skilar verð
    public int getPrice() {
        return price;
    }

    public ArrayList<Flight> getFlightItems() {
        return this.flightItems;
    }

    public ArrayList<Hotel> getHotelItems() {
        return this.hotelItems;
    }

    public ArrayList<DayTour> getDayTourItems() {
        return this.dayTourItems;
    }

    public void addFlightItem(Flight flight) {
        if (flight != null) {
            this.flightItems.add(flight);

            //update other info
            if (startDate == null || flight.getDate().isBefore(startDate)) {
                startDate = flight.getDate();
            }
            if (endDate == null || flight.getDate().isAfter(endDate)) {
                endDate = flight.getDate();
            }
            price += flight.getPrice();
        }
    }
    public void addHotelItem(Hotel hotel) {
        if (hotel != null) {
            this.hotelItems.add(hotel);

            //update other info
            if (startDate == null ||  hotel.getStartDate().isBefore(startDate)) {
                startDate = hotel.getStartDate();
            }
            if (endDate == null || hotel.getEndDate().isAfter(endDate)) {
                endDate = hotel.getEndDate();
            }
            price += hotel.getPrice();
        }
    }
    public void addDayTourItem(DayTour dayTour) {
        if (dayTour != null) {
            this.dayTourItems.add(dayTour);

            //update other info
            if (startDate == null || dayTour.getDate().isBefore(startDate)) {
                startDate = dayTour.getDate();
            }
            if (endDate == null || dayTour.getDate().isAfter(endDate)) {
                endDate = dayTour.getDate();
            }
            price += dayTour.getPrice();
            if (dayTour.getPeople() > people) {people = dayTour.getPeople();}
        }
    }
}
