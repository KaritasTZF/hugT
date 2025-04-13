package com.example.hbv4d.objects;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class WishlistTest {
    private Tour tour1;
    private Tour tour2;

    @BeforeEach
    void setUp() {
        Wishlist.getWishlist().clear();

        tour1 = new Tour(1, "Test Tour 1", "Description 1",10000, LocalDate.parse("2025-10-12"),"Test City 1","Test Schedule 1", 10);
        tour2 = new Tour(2, "Test Tour 2", "Description 2", 20000, LocalDate.parse("2025-11-12"), "Test City 2", "Test Schedule 2", 20);
    }

    @Test
    void testAddTour() {
        Wishlist.addTour(tour1);
        ObservableList<Tour> wishlist = Wishlist.getWishlist();
        assertTrue(wishlist.contains(tour1));
    }

    @Test
    void testAddDuplicateTour() {
        Wishlist.addTour(tour1);
        Wishlist.addTour(tour1);
        assertEquals(1, Wishlist.getWishlist().size());
    }

    @Test
    void testRemoveTour() {
        Wishlist.addTour(tour1);
        Wishlist.removeTour(tour1);
        assertFalse(Wishlist.getWishlist().contains(tour1));
    }

    @Test
    void testRemoveNonExistentTour() {
        Wishlist.removeTour(tour1);
        assertEquals(0, Wishlist.getWishlist().size());
    }

    @Test
    void testGetWishlist() {
        Wishlist.addTour(tour1);
        Wishlist.addTour(tour2);
        ObservableList<Tour> wishlist = Wishlist.getWishlist();
        assertEquals(2, wishlist.size());
        assertTrue(wishlist.contains(tour1));
        assertTrue(wishlist.contains(tour2));
    }
}
