package project.Model;

import com.example.hbv4d.objects.Tour;
import com.example.hbv4d.objects.TourDAO;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class DayTourDB {
    private final ArrayList<DayTour> dayTourList= new ArrayList<>();

    public DayTourDB() {
        //Fá öll 4D tours (þau eru ekki með filter)
        try {
            ObservableList<Tour> tours = TourDAO.listTours();
            //Breytum 4D "Tour" objects í okkar DayTour objects. TourDAO tengist SQL.
            for (Tour tour : tours) {
                createDayTour(tour.getTourName(), tour.getDate(), tour.getSchedule(), tour.getPrice(), tour.getCity());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void createDayTour(String name, LocalDate date, String schedule, int price, String location) {
        this.dayTourList.add(new DayTour(name,0,date,schedule,price,location));
    }

    public ArrayList<DayTour> getDayTourList(){
        return this.dayTourList;
    }

}
