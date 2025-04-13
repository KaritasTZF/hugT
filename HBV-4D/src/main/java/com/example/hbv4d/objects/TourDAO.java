package com.example.hbv4d.objects;

import com.example.hbv4d.utils.Database;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TourDAO {
    private static final String ToursTable = "Tours";
    private static final String ReviewsTable = "Reviews";

    public static Tour resultMapper(ResultSet rs, Connection conn) throws SQLException {
        Tour tour = new Tour(
                rs.getInt("id"),
                rs.getString("tourName"),
                rs.getString("description"),
                rs.getInt("price"),
                LocalDate.parse(rs.getString("date")),
                rs.getString("city"),
                rs.getString("schedule"),
                rs.getInt("availability")
        );

        List<Review> reviews = getReviewsForTour(rs.getInt("id"), conn);

        for (Review review : reviews) {
            tour.addReview(review);
        }
        return tour;
    }

    public static ObservableList<Tour> listTours() {
        try (Connection conn = Database.connect()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + ToursTable );
            ObservableList<Tour> tours = javafx.collections.FXCollections.observableArrayList();

            while (rs.next()) {
                tours.add(resultMapper(rs, conn));
            }
            return tours;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public static ObservableList<Tour> listToursByPrice(int price){
        try (Connection conn = Database.connect()){
            String sql = "SELECT * FROM " + ToursTable + " WHERE price <= ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, price);
            ResultSet rs = pstmt.executeQuery();
            ObservableList<Tour> tours = javafx.collections.FXCollections.observableArrayList();

            while (rs.next()) {
                tours.add(resultMapper(rs, conn));
            }
            return tours;

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return null;
    }

    public static ObservableList<Tour> getToursByFilter(String search){
        try (Connection conn = Database.connect()){
            String sql = "SELECT * FROM " + ToursTable + " WHERE tourName LIKE ? OR city LIKE ? OR date LIKE ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + search + "%");
            pstmt.setString(2, "%" + search + "%");
            pstmt.setString(3, "%" + search + "%");
            ResultSet rs = pstmt.executeQuery();
            ObservableList<Tour> tours = javafx.collections.FXCollections.observableArrayList();

            while (rs.next()) {
                tours.add(resultMapper(rs, conn));
            }
            return tours;

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return null;
    }

    private static List<Review> getReviewsForTour(int tourId, Connection conn) throws SQLException {
        List<Review> reviews = new ArrayList<>();
        String sql = "SELECT * FROM " + ReviewsTable + " WHERE tourId = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, tourId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                reviews.add(new Review(
                        rs.getString("firstName"),
                        rs.getString("comment"),
                        rs.getInt("rating")
                ));
            }
        }
        return reviews;
    }
}
