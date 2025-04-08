package project.Model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.UUID;

public class Trip {
    private final String tripID;
    private int people;
    private int days;
    private LocalDate startDate;
    private LocalDate endDate;
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
        return days;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    //skilar verð
    public double getPrice() {
        return price;
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

    //Helper; all attributes are defined by the F/D/H lists
    private void updateInfo() {
        startDate = flightItems.getFirst().getDate();
        endDate = startDate;
        price = 0;
        for (Flight f: flightItems) {
            price += f.getPrice();
            if (f.getDate().isBefore(startDate)) {
                startDate = f.getDate();
            } else if (f.getDate().isAfter(endDate)) {
                endDate = f.getDate();
            }
        }
        for (Hotel h: hotelItems) {
            price +=h.getPrice();
            if (h.getStartDate().isBefore(startDate)) {
                startDate = h.getStartDate();
            } else if (h.getEndDate().isAfter(endDate)) {
                endDate = h.getEndDate();
            }
        }
        for (DayTour dt: dayTourItems) {
            price += dt.getPrice();
            if (dt.getDate().isBefore(startDate)) {
                startDate = dt.getDate();
            } else if (dt.getDate().isAfter(endDate)) {
                endDate = dt.getDate();
            }
        }
        this.days = (int) ChronoUnit.DAYS.between(startDate,endDate);
    }

    public void addFlightItem(Flight flight) {
        this.flightItems.add(flight);
        updateInfo();
    }
    public void addHotelItem(Hotel hotel) {
        this.hotelItems.add(hotel);
        updateInfo();
    }
    public void addDayTourItem(DayTour dayTour) {
        this.dayTourItems.add(dayTour);
        updateInfo();
    }
}
