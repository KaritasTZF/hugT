package project.Controller;

import project.Model.Booking;
import project.Model.Trip;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BookingController {

    // Listi til að geyma bókanir
    private final List<Booking> bookings = new ArrayList<>();
    // Gagnateljari til að útbúa einstakt bookingID
    private int bookingCounter = 1;

    // Lista af Trips; notað til að "leita" að réttu Trip hlutnum.
    private final List<Trip> trips = new ArrayList<>();

    public Booking createBooking(int userID, String tripID) {
        // Leitum að Trip með því að nota getTripById(String)
        Trip chosenTrip = getTripById(tripID);
        if (chosenTrip == null) {
            System.out.println("Trip not found for ID: " + tripID);
            return null;
        }
        // Smíðum nýja Booking
        Booking newBooking = new Booking(
                userID,               // notendauðkenni
                chosenTrip,           // Trip hlutinn
                bookingCounter++,     // auto-inkrement bookingID
                "Pending",            // upphafsstöðu (Pending)
                0                     // confirmationNr byrjar sem 0
        );
        // Bætum bókuninni í listann
        bookings.add(newBooking);
        // Skilum nýju bókuninni
        return newBooking;
    }


    public Booking confirmBooking(int bookingID) {
        // Leitum að bókuninni með getBookingByID
        Booking booking = getBookingByID(bookingID);
        if (booking == null) {
            System.out.println("No booking found with ID: " + bookingID);
            return null;
        }
        // Uppfærum stöðu og setjum staðfestingarnúmer
        booking.setStatus("Confirmed");
        booking.setConfirmationNr(generateConfirmationNumber());
        return booking;
    }

    private Booking getBookingByID(int bookingID) {
        for (Booking b : bookings) {
            if (b.getBookingID() == bookingID) {
                return b;
            }
        }
        return null;
    }


    private int generateConfirmationNumber() {
        Random rand = new Random();
        return 100000 + rand.nextInt(900000);  // Númer á bilinu 100000 til 999999
    }

    private Trip getTripById(String tripID) {
        for (Trip t : trips) {
            if (t.getTripID().equals(tripID)) {
                return t;
            }
        }
        return null;
    }
}
