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
                //TODO 4D hefur ekki fjöldi manns, og tími er geymdur sem String fyrir details
                createDayTour(0, tour.getDate(), null, null, tour.getPrice(), tour.getCity());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void createDayTour(int people, LocalDate date, LocalDateTime timeStart, LocalDateTime timeEnd, int price, String location) {
        this.dayTourList.add(new DayTour(people,date,timeStart,timeEnd,price,location));
    }

    public ArrayList<DayTour> getDayTourList(){
        return this.dayTourList;
    }

}
