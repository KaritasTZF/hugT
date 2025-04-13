package com.example.hbv4d.objects;

import com.example.hbv4d.utils.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for Reviews
 */
public class ReviewDAO {
    private static final String TableName = "Reviews";

    public static List<Review> getReviewsForTour(int tourId) {
        try (Connection conn = Database.connect()) {
            String sql = "SELECT * FROM " + TableName + " WHERE tourId = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, tourId);
            ResultSet rs = pstmt.executeQuery();
            List<Review> reviews = new ArrayList<>();

            while (rs.next()) {
                reviews.add(new Review(
                        rs.getString("firstName"),
                        rs.getString("comment"),
                        rs.getInt("rating")
                ));
            }
            return reviews;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
