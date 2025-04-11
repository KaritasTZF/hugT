package project.Controller;

import project.Model.*;
import project.ui.SearchViewController;

import java.time.LocalDate;
import java.util.ArrayList;


public class SearchController {
    //Search parameters
    private String from;            // flights only (from fyrir fyrsta flight, to fyrir seinna)
    private String location;        // virkar sem to/from fyrir flight
    private LocalDate startDate;    // ATH 4H tekur bara 3 checkIn dagsetningsar, sjá Readme þeirra
    private LocalDate endDate;      //
    private int maxPrice;           // D&H; 4F er ekki með verð. Notum 20 000 kr fyrir öll flight
    private int people;             // Hotels only; D&F hugsa bara um eina manneskju
    private int rooms;              // hotels only

    // Öll database hinna hópanna
    private final FlightDB flightDB;
    private final HotelDB hotelDB;
    private final DayTourDB dayTourDB;

    private Status status = Status.FROMFLIGHT;  //Enum til að fylgjast með skref leitarvélsins
    private final Trip myTrip=new Trip();       // Constructum trip object

    // view handlar allt varðandi javafx; SearchController handlar leitina og logic
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

    // Searching methods ----------------------------------------------------------------------------------------------
    //Þessi föll sækja í D/F/H DB klasanna sem sækja í 4D/4F/4H gagnagrunna

    //Leitar eftir flug í flightDB
    public ArrayList<Flight> findAvailableFlights(String fromLoc, String toLoc, LocalDate date) {
        return flightDB.getFlightList(fromLoc, toLoc, date);
    }

    //Leitar eftir hotel í hotelDB
    public ArrayList<Hotel> findAvailableHotels() {
        return hotelDB.getHotelList(this.location, this.startDate, this.endDate, this.people, this.rooms);
    }

    //Leitar eftir dayTour í dayTourDB - við getum ekki notað leitarfall hóps D
    public ArrayList<DayTour> findAvailableDayTours() {
        ArrayList<DayTour> dayTourDBList = dayTourDB.getDayTourList();
        ArrayList<DayTour> dayTourReturnList= new ArrayList<>();

        // Passa íslensku stafi; 4D tekur bara við "Vik" og ekki "Vík". Stafir sem koma til sögu í gagnagrunn 4D eru: á,í,ó,ö,Þ
        String engLocation = location.replace("á","a").replace("í","i").replaceAll("[öó]","o").replace("Þ","Th");

        for (DayTour dayTour : dayTourDBList) {
            // Filter eftir staðsetningu: Athuga hvort dayTour location sé sú sama
            if (dayTour.getLocation().trim().equals(engLocation.trim())) {
                // Filtera eftir dagsetningu: dayTour verður að vera á eða eftir startDate og á eða fyrir endDate
                if ((dayTour.getDate().isAfter(startDate) || dayTour.getDate().isEqual(startDate))) {
                    if ((dayTour.getDate().isBefore(endDate) || dayTour.getDate().isEqual(endDate))) {
                        //Filtera eftir verði: dayTour verð verður að vera minni eða jafnt og maxPrice
                        if (dayTour.getPrice() <= maxPrice) {
                            // Ef skilyrðin eru uppfyllt, bætum við dayTour við í niðurstöðulistann
                            dayTourReturnList.add(dayTour);
                        }
                    }
                }
            }
        }
        return dayTourReturnList;
    }

    //-----------------------------------------------------------------------------------------------------------------
    // Skilgreinum hjálpar enum okkar sem fylgist með hvað er verið að leita af - Flight (Til/Frá), Hotel eða Day Tour

    public enum Status {
        FROMFLIGHT, HOTEL, DAYTOUR, TOFLIGHT
    }

    //Við takka á view -------------------------------------------------------------------------------------------------

    // Við search takki, ákveður hvað að search-a og hvort inputtið sé gilt
    public void onSearch() {
        switch(status) {
            case Status.FROMFLIGHT:
                if (from != null && location != null && startDate != null) {
                    ArrayList<Flight> flightsArrayList = findAvailableFlights(from,location,startDate);
                    //Kallar á SearchViewController til að sýna listann
                    view.loadFlightsToList(view.getResultsListView(), flightsArrayList);
                } else {
                    System.out.println("ATH from, to, dags.");
                }
                break;
            case Status.TOFLIGHT:
                if (from != null && location != null && endDate != null && endDate.isAfter(startDate)) {
                    ArrayList<Flight> flightsArrayList = findAvailableFlights(location,from,endDate);
                    view.loadFlightsToList(view.getResultsListView(),flightsArrayList);
                }
                break;
            case Status.HOTEL:
                // tjekka að leit sé ekki alveg tóm. people&price hafa default gildi.
                if (location != null && endDate != null && startDate != null && endDate.isAfter(startDate)) {
                    ArrayList<Hotel> hotelsArrayList = findAvailableHotels();
                    view.loadHotelsToList(view.getResultsListView(),hotelsArrayList);
                }
                break;
            case Status.DAYTOUR:
                if (location != null && endDate != null && startDate != null && endDate.isAfter(startDate)) {
                    ArrayList<DayTour> dayToursArrayList = findAvailableDayTours();
                    view.loadDayToursToList(view.getResultsListView(),dayToursArrayList);
                }
                break;
        }
    }

    //við Add takkinn, kallað frá view. Finnur hvað er valið í ResultsListView og setur það inn í Trip item
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
