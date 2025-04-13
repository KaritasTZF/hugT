package com.example.hbv4d.objects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class Wishlist {
    private final int wishlistId;
    private final int userId;
    private final List<Tour> wishlistTour;

    public Wishlist(int wishlistId, int userId){
        this.wishlistId = wishlistId;
        this.userId = userId;
        this.wishlistTour = new ArrayList<>();
    }

    private static final ObservableList<Tour> wishlist = FXCollections.observableArrayList();

    public static ObservableList<Tour> getWishlist() {
        return wishlist;
    }

    public static void addTour(Tour tour) {
        if (!wishlist.contains(tour)) {
            wishlist.add(tour);
        }
    }

    public static void removeTour(Tour tour) {
        wishlist.remove(tour);
    }
}
