package project.Model;

import java.time.LocalDate;

public class Hotel {
    // attributes hér
    private int rooms;
    private int days;
    private LocalDate startDate;
    private LocalDate endDate;
    private int price;
    private String location;

    // Constructor
    public Hotel(int rooms, int days, LocalDate startDate, LocalDate endDate, int price, String location) {
        this.rooms = rooms;
        this.days = days;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.location = location;
    }

    public int getRooms() {
        return this.rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public int getDays() {
        return this.days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
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
