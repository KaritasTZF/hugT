package project.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class DayTourDB {
    private ArrayList<DayTour> dayTourList;

    public DayTourDB() {
    }

    public void createDayTour(int people, LocalDate date, LocalDateTime timeStart, LocalDateTime timeEnd, int price, String location) {
        dayTourList.add(new DayTour(people,date,timeStart,timeEnd,price,location));
    }

    public ArrayList<DayTour> getDayTourList(){
        return this.dayTourList;
    }

}
