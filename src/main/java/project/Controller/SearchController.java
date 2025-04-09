package project.Controller;

import project.Model.*;
import project.ui.SearchViewController;

import java.time.LocalDate;
import java.util.ArrayList;


public class SearchController {
    //Search parameters
    private String from;            // flights only
    private String location;        // virkar sem to fyrir flight
    private LocalDate startDate;    // ATH 4H tekur bara 3 checkIn dagsetningsar, sjá Readme þeirra
    private LocalDate endDate;      //
    private int maxPrice;           // D&H; 4F er ekki með verð
    private int people;             // Flights only; 4D notar ekki fjöldi manns
    private int rooms;              // hotels only

    private final FlightDB flightDB;
    private final HotelDB hotelDB;
    private final DayTourDB dayTourDB;
    private Status status = Status.FROMFLIGHT;
    private final Trip myTrip=new Trip();
    private final SearchViewController view;

    public SearchController(SearchViewController view) {
        this.flightDB = new FlightDB();
        this.hotelDB = new HotelDB();
        this.dayTourDB = new DayTourDB();
        this.view = view;
    }

    //setterar og getterar
    public void setFrom(String from) {
      this.from = from;
    }
    public String getFrom() {
      return this.from;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getLocation() {
        return this.location;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public LocalDate getStartDate() {
        return this.startDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    public LocalDate getEndDate() {
        return this.endDate;
    }
    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }
    public int getMaxPrice() {
        return this.maxPrice;
    }
    public void setPeople(int people) {
        this.people = people;
    }
    public int getPeople() {
        return this.people;
    }
    public void setRooms(int rooms) {
        this.rooms = rooms;
    }
    public int getRooms() {
        return this.rooms;
    }
    public Trip getMyTrip() {
        return this.myTrip;
    }
    public Status getStatus() {
        return this.status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }

    //Searching methods

    //Leitar eftir flug í flightDB
    public ArrayList<Flight> findAvailableFlights(String fromLoc, String toLoc, LocalDate date) {
        return flightDB.getFlightList(fromLoc, toLoc, date);
    }

    //Leitar eftir hotel í hotelDB
    public ArrayList<Hotel> findAvailableHotels() {
        return hotelDB.getHotelList(this.location, this.startDate, this.endDate, this.people, this.rooms);
    }

    //Leitar eftir dayTour í dayTourDB
    public ArrayList<DayTour> findAvailableDayTours() {
        ArrayList<DayTour> dayTourDBList = dayTourDB.getDayTourList();
        ArrayList<DayTour> dayTourReturnList= new ArrayList<>();

        for (DayTour dayTour : dayTourDBList) {
            // Filter eftir staðsetningu: Athuga hvort dayTour location sé sú sama (óháð stórum/lágu stöfum)
            if (!dayTour.getLocation().equalsIgnoreCase(location)) {
                continue;
            }

            // Filtera eftir dagsetningu: dayTour verður að vera á eða eftir startDate og á eða fyrir endDate
            if (!(dayTour.getDate().isAfter(startDate) || dayTour.getDate().isEqual(startDate))) {
                continue;
            }
            if (!(dayTour.getDate().isBefore(endDate) || dayTour.getDate().isEqual(endDate))) {
                continue;
            }

            // Filtera eftir verði: dayTour verð verður að vera minni eða jafnt og maxPrice
            if (dayTour.getPrice() <= maxPrice) {
                continue;
            }

            // Ef allar skilyrði eru uppfyllt, bæta dayTour við í niðurstöðulistann
            dayTourReturnList.add(dayTour);
        }
        return dayTourReturnList;
    }

    public enum Status {
        FROMFLIGHT, HOTEL, DAYTOUR, TOFLIGHT
    }

    //Takka virkni

    // Við search takki, ákveður hvað að search-a
    public void onSearch() {
        switch(status) {
            case Status.FROMFLIGHT:
                if (from != null && location != null && startDate != null) {
                    ArrayList<Flight> flightsArrayList = findAvailableFlights(from,location,startDate);
                    view.loadFlightsToList(view.getResultsListView(), flightsArrayList);
                    flightsArrayList = null;
                }
                break;
            case Status.TOFLIGHT:
                if (from != null && location != null && endDate != null && endDate.isAfter(startDate)) {
                    ArrayList<Flight> flightsArrayList = findAvailableFlights(location,from,endDate);
                    view.loadFlightsToList(view.getResultsListView(),flightsArrayList);
                    flightsArrayList = null;
                }
                break;
            case Status.HOTEL:
                // tjekka að leit sé ekki alveg tóm. people&price hafa default gildi.
                if (location != null && endDate != null && startDate != null && endDate.isAfter(startDate)) {
                    ArrayList<Hotel> hotelsArrayList = findAvailableHotels();
                    view.loadHotelsToList(view.getResultsListView(),hotelsArrayList);
                    hotelsArrayList = null;
                }
                break;
            case Status.DAYTOUR:
                if (location != null && endDate != null && startDate != null && endDate.isAfter(startDate)) {
                    ArrayList<DayTour> dayToursArrayList = findAvailableDayTours();
                    view.loadDayToursToList(view.getResultsListView(),dayToursArrayList);
                    dayToursArrayList = null;
                }
                break;
        }
    }

    //við Add takkinn. Finnur hvað er valið í ResultsListView og setur það inn í Trip item
    public void addToMyTrip(Flight selectedFItem, Hotel selectedHItem, DayTour selectedDTItem) {
        switch (status) {
            case FROMFLIGHT, TOFLIGHT:
                myTrip.addFlightItem(selectedFItem);
                break;
            case HOTEL:
                myTrip.addHotelItem(selectedHItem);
                break;
            case DAYTOUR:
                myTrip.addDayTourItem(selectedDTItem);
                break;
        }
    }

}
