package project.Model;

import software.Database;
import software.objects.Search;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class HotelDB {
    private final ArrayList<Hotel> hotelList = new ArrayList<>();
    private Search search; //frá 4H

    public HotelDB() {
        try {
            Database db = new Database();
            search = new Search(db.getHotels());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void createHotel(String name, int rooms, LocalDate start, LocalDate end, int price, String location) {
        hotelList.add(new Hotel(name, rooms,start,end,price,location));
    }

    public ArrayList<Hotel> getHotelList(
            String location,
            LocalDate checkInDate,
            LocalDate checkOutDate,
            int people,
            int maxPrice
            ){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        int pplCount = people > 0 ? people : 1;
        try {
            ArrayList<software.objects.Hotel> availableHotels = search.initialSearch(location, checkInDate.format(formatter), checkOutDate.format(formatter), pplCount);

            for (software.objects.Hotel hotel: availableHotels) {
                createHotel(hotel.getName(),1,checkInDate,checkOutDate,hotel.getCheapestRoom(), hotel.getLocation());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return this.hotelList;
    }

}
