package project.Model;

import software.Database;
import software.objects.HotelRoom;
import software.objects.Search;

import java.util.ArrayList;
import java.util.Objects;

public class HotelBookInDB {
    Database db = new Database();
    software.objects.Search search;
    User user;
    public HotelBookInDB(User user) {
        this.user = user;
        search = new Search(db.getHotels());
    }

    public void bookHotel(Hotel ourHotel) {
        // Search their (4H) database for
        ArrayList<software.objects.Hotel> searchedList = search.initialSearch(
                ourHotel.getLocation(),
                ourHotel.getStartDate().toString(),
                ourHotel.getEndDate().toString(),
                1
        );
        software.objects.Hotel theirHotel;
        for (software.objects.Hotel h: searchedList) {
            if (Objects.equals(h.getName(), ourHotel.getName())) {
                theirHotel = h;

                //find cheapest room
                HotelRoom theirRoom = theirHotel.getRooms().getFirst();
                for (HotelRoom room: theirHotel.getRooms()){
                    if(theirRoom.getPricePerNight() > room.getPricePerNight()){
                        theirRoom = room;
                    }
                }

                // make (their) booking
                software.objects.Booking theirBooking = new software.objects.Booking(
                        theirHotel.getName(),
                        user.getName(),
                        theirRoom.getPricePerNight(),
                        theirRoom.getRoomNumber(),
                        ourHotel.getStartDate().toString(),
                        ourHotel.getEndDate().toString(),
                        ourHotel.getRooms(),
                        ourHotel.getLocation(),
                        theirHotel.isRefundable()
                );
                //and to database
                db.makeBooking(theirBooking);
                System.out.println("Sucessfully booked hotel.");
                break;
            }
        }
    }
}
