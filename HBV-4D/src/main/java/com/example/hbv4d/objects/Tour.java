package com.example.hbv4d.objects;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Tour {
        private final int tourId;
        private final String tourName;
        private final String description;
        private final int price;
        private final LocalDate date;
        private final String city;
        private final String schedule;
        private final int availability;
        private final List<Review> reviews;


        public Tour(int tourId, String tourName, String description, int price, LocalDate date, String city,String schedule,int availability) {
            this.tourId = tourId;
            this.tourName = tourName;
            this.description = description;
            this.price = price;
            this.date = date;
            this.city = city;
            this.schedule = schedule;
            this.availability = availability;
            this.reviews = new ArrayList<>();
        }

        public int getId(){
            return tourId;
        }

        public LocalDate getDate(){
            return date;
        }

        public String getCity(){
            return city;
        }

        public String getTourName(){
            return tourName;
        }

        public String getDescription(){
            return description;
        }

        public int getPrice() {
            return price;
        }


        public String getSchedule(){
            return schedule;
        }

        private int getAvailability(){
            return availability;
        }


        public void addReview(Review review) {
            reviews.add(review);
        }

        public List<Review> getReviews() {
            return reviews;
        }

        @Override
        public String toString() {
            return tourName + " - " + price + "kr - " + city + " - " + date;
        }
}
