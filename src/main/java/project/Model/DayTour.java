package project.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DayTour {
    // attributes hér
    private int people;
    private LocalDate date;
    private LocalDateTime timeStart;
    private LocalDateTime timeEnd;
    private int price;
    private String location;

    // Constructor
    public DayTour(
      int people, 
      LocalDate date, 
      LocalDateTime timeStart, 
      LocalDateTime timeEnd, 
      int price,
      String location
      ) {
        this.people = people;
        this.date = date;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.price = price;
        this.location = location;
    }

    public int getPeople() {
        return this.people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDateTime getTimeStart() {
        return this.timeStart;
    }

    public void setTimeStart(LocalDateTime timeStart) {
        this.timeStart = timeStart;
    }

    public LocalDateTime getTimeEnd() {
        return this.timeEnd;
    }

    public void setTimeEnd(LocalDateTime timeEnd) {
        this.timeEnd = timeEnd;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}