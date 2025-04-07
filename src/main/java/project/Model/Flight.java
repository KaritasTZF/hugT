package project.Model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Flight {
    private String from;
    private String to;
    private int availableSeats;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate date;
    private int price;

    // Constructor
    public Flight(String from, String to, LocalDate date, int availableSeats, LocalTime startTime, LocalTime endTime, int price) {
        this.from = from;
        this.to = to;
        this.date = date;
        this.availableSeats = availableSeats;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public int getavailableSeats() {
        return this.availableSeats;
    }

    public void setavailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public LocalTime getStartTime() {
        return this.startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime= startTime;
    }

    public LocalTime getEndTime() {
        return this.endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public LocalDate getDate() {return this.date;}

    public void setDate(LocalDate date) {this.date =date;}

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
