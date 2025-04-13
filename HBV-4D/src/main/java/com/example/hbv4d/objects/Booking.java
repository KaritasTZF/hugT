package com.example.hbv4d.objects;

public class Booking {
    private final int tourId;
    private final int userId;
    private final String firstName;
    private final String email;
    private Tour tour;

    public Booking( int tourId, int userId, String firstName, String email){
        this.tourId = tourId;
        this.userId = userId;
        this.firstName = firstName;
        this.email = email;
    }

    public Tour getTour(){
        return tour;
    }

    public void setTour(Tour tour){
        this.tour = tour;
    }


    public int getTourId() {
        return tourId;
    }

    public int getUserId(){
        return userId;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getEmail(){
        return email;
    }

    @Override
    public String toString() {
        return " " + tourId;
    }
}
