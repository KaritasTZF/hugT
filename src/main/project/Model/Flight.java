package project.Model;

import java.time.LocalDateTime;

public class Flight {
    private String from;
    private String to;
    private int availableSeats;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private int price;

    // Constructor
    public Flight(String from, String to, int availableSeats, LocalDateTime startDateTime, LocalDateTime endTime, int price) {
        this.from = from;
        this.to = to;
        this.availableSeats = availableSeats;
        this.startDateTime = startDateTime;
        this.endDateTime = endTime;
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

    public LocalDateTime getStartDateTime() {
        return this.startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime= startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return this.endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
