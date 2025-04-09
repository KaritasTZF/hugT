package project.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DayTour {
    // attributes hér
    private String name;
    private int people;
    private LocalDate date;
    private LocalDateTime timeStart;
    private LocalDateTime timeEnd;
    private String schedule;
    private int price;
    private String location;

    // Constructor
    public DayTour(
            String name,
      int people, 
      LocalDate date,
      String schedule,
      int price,
      String location
      ) {
        this.name = name;
        this.people = people;
        this.date = date;
        this.schedule=schedule;
        //lesa úr schedule með substring
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

    public String getSchedule() { return schedule;}
    public void setSchedule(String schedule) {this.schedule = schedule;}
    public String getName() {return name;}
    public void setName(String name) {this.name =name;}
}