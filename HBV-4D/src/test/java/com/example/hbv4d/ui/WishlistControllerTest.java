package com.example.hbv4d.ui;

import com.example.hbv4d.objects.Tour;
import com.example.hbv4d.objects.Wishlist;
import javafx.collections.FXCollections;
import javafx.scene.control.ListView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class WishlistControllerTest {
    private WishlistController controller;
    private Tour testTour;

    @BeforeEach
    void setUp() {
        controller = new WishlistController();
        controller.wishlistListView = new ListView<>(FXCollections.observableArrayList());
        testTour = new Tour(1, "Test Tour 1", "Description 1",10000, LocalDate.parse("2025-10-12"),"Test City 1","Test Schedule 1", 10);
        Wishlist.getWishlist().clear();
    }

    @Test
    void testInitialize() {
        Wishlist.addTour(testTour);
        controller.initialize();
        assertEquals(1, controller.wishlistListView.getItems().size());
    }

    @Test
    void testOnRemoveFromWishlist() {
        Wishlist.addTour(testTour);
        controller.initialize();
        controller.wishlistListView.getSelectionModel().select(testTour);
        controller.onRemoveFromWishlist();
        assertEquals(0, Wishlist.getWishlist().size());
    }
}
