package com.example.hbv4d.objects;


public class Review {
    private final String firstName;
    private final String comment;
    private final int rating;

    public Review(String firstName, String comment, int rating) {
        this.firstName = firstName;
        this.comment = comment;
        this.rating = rating;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getComment() {
        return comment;
    }

    public int getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "Review by " + firstName + ": " + comment + " (Rating: " + rating + ")";
    }
}
