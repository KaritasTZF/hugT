package project.Model;

import java.time.LocalDate;

public class Flight {
    // attributes hér
    private String from;
    private String to;
    private int people;
    private int days;
    private LocalDate startDate;
    private LocalDate endDate;
    private double price;
    private String location;

    // Constructor
    public Flight(String from, String to, int people, int days, LocalDate startDate, LocalDate endDate, double price, String location) {
        this.from = from;
        this.to = to;
        this.people = people;
        this.days = days;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.location = location;
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

    public int getPeople() {
        return this.people;
    }

    public void setPeople(int people) {
        this.people = people;
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

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    // methods hér. Aðallega getterar fyrir hvert attribute.
}