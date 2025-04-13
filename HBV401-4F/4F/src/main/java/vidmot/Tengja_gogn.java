package vidmot;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Tengja_gogn {

    public static Connection connect() {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            // Locate the database file in the classpath (in the /db folder)
            URL dbUrl = Tengja_gogn.class.getResource("/db/flights.db");
            if (dbUrl == null) {
                throw new RuntimeException("Database file not found on classpath!");
            }
            
            File dbFile;
            // Check if the resource is inside a jar
            if ("jar".equals(dbUrl.getProtocol())) {
                // Extract the DB to a temporary file
                try (InputStream is = Tengja_gogn.class.getResourceAsStream("/db/flights.db")) {
                    if (is == null) {
                        throw new RuntimeException("Database file not found as stream!");
                    }
                    dbFile = File.createTempFile("flights", ".db");
                    dbFile.deleteOnExit();
                    try (FileOutputStream fos = new FileOutputStream(dbFile)) {
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = is.read(buffer)) != -1) {
                            fos.write(buffer, 0, bytesRead);
                        }
                    }
                }
            } else {
                // Not in jar; can use the file directly.
                dbFile = new File(dbUrl.toURI());
            }
            
            conn = DriverManager.getConnection("jdbc:sqlite:" + dbFile.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
    
    public static List<String> loadAirports() {
        List<String> airports = new ArrayList<>();
        String sql = "SELECT DISTINCT departure FROM Schedule UNION SELECT DISTINCT arrival FROM Schedule";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                airports.add(rs.getString(1));
            }
        } catch (Exception e) {
            System.out.println("Error loading airports: " + e.getMessage());
        }
        return airports;
    }
    
    /**
     * Retrieves flights for a given route and date.
     * Assumes the Schedule table has columns:
     * ID | weekday | departure | arrival | departure_time | flight_time | arrival_time
     */
    public static List<String> getFlights(String from, String to, LocalDate date) {
        List<String> flights = new ArrayList<>();
        String sql = "SELECT departure_time, flight_time, arrival_time " +
                     "FROM Schedule " +
                     "WHERE departure = ? AND arrival = ? AND weekday = ? " +
                     "ORDER BY departure_time";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            String weekday = date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
            pstmt.setString(1, from);
            pstmt.setString(2, to);
            pstmt.setString(3, weekday);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String depTime = rs.getString("departure_time");
                String duration = rs.getString("flight_time");
                String arrTime = rs.getString("arrival_time");
                String flightInfo = from + " → " + to + " | " + depTime + " → " + arrTime + " | Duration: " + duration;
                flights.add(flightInfo);
            }
        } catch (Exception e) {
            System.out.println("Error fetching flights: " + e.getMessage());
        }
        if (flights.isEmpty()) {
            flights.add("No flights available.");
        }
        return flights;
    }
    
    public static int countFlights(String from, String to, LocalDate date) {
        int count = 0;
        String sql = "SELECT COUNT(*) AS flight_count FROM Schedule WHERE departure = ? AND arrival = ? AND weekday = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            String weekday = date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
            pstmt.setString(1, from);
            pstmt.setString(2, to);
            pstmt.setString(3, weekday);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt("flight_count");
            }
        } catch (Exception e) {
            System.out.println("Error counting flights: " + e.getMessage());
        }
        return count;
    }
}
