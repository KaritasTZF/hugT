package project.util;

import project.Model.User;
import project.Model.Booking;
import java.util.ArrayList;
import java.util.List;


import java.sql.*;

public class DBHelper {

    private static final String modPath = "/Users/brynjarari/Desktop/project/hugT/src/main/resources/data/";

    // DB_URL bendir á SQLite gagnagrunnskrónuna.
    // Hér er hunum smíðuð með því að sameina modPath og skráarnafninn.
    private static final String DB_URL = "jdbc:sqlite:" + modPath + "user.db";

    // SQLite þarf ekki notendaheiti og lykilorð að því leyti sem hefðbundnir gagnagrunnar.
    // Notaðu getConnection() til að opna tengingu við gagnagrunninn.
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    /**
     * Sækir notanda með tilteknu notendanafni úr gagnagrunninum.
     */
    public static User getUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String userId = rs.getString("user_id");
                String uname = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                return new User(userId, uname, password, email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Vistar nýjan notanda í gagnagrunninum.
     */
    public static boolean insertUser(User user) {
        String sql = "INSERT INTO users (user_id, username, password, email) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getUserID());
            ps.setString(2, user.getName());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getEmail());
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean insertBooking(Booking booking) {
        // Ef booking_id er auto-inkrement í gagnagrunninum, má sleppa því í INSERT-skipuninni.
        String sql = "INSERT INTO bookings (booking_id, user_id, trip_id, status, confirmation_nr) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, booking.getBookingID()); // Ef þú notar bookingCounter, annars getur þú sleppt booking_id.
            ps.setString(2, booking.getUserID());
            ps.setString(3, booking.getTrip().getTripID());
            ps.setString(4, booking.getStatus());
            ps.setInt(5, booking.getConfirmationNr());

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<Booking> getBookingsByUser(String userID) {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM bookings WHERE user_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, userID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int bookingId = rs.getInt("booking_id");
                String tripId = rs.getString("trip_id");
                String status = rs.getString("status");
                int confirmationNr = rs.getInt("confirmation_nr");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }
}
