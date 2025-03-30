package project.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Flight {
    private String from;
    private String to;
    private int availableSeats;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDate date;
    private int price;

    // Constructor
    public Flight(String from, String to, int availableSeats, LocalDateTime startTime, LocalDateTime endTime, LocalDate date, int price) {
        this.from = from;
        this.to = to;
        this.availableSeats = availableSeats;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
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

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime= startTime;
    }

    public LocalDateTime getEndTime() {
        return this.endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
