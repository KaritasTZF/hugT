package project.Model;

import software.Database;
import software.objects.Search;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class HotelDB {
    private final ArrayList<Hotel> hotelList = new ArrayList<>();
    private final Search search; //frá 4H

    public HotelDB() {
        //Tengjum 4H database og leitarvél 4H
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
            int rooms
            ){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        int pplCount = people > 0 ? people : 1;

        try {
            //Leitum í 4H fyrir location, dags., manns
            ArrayList<software.objects.Hotel> availableHotels = search.initialSearch(location, checkInDate.format(formatter), checkOutDate.format(formatter), pplCount);

            // Check how rooms and select the cheapest by default
            for (software.objects.Hotel hotel: availableHotels) {
                ArrayList<software.objects.HotelRoom> roomsFrom4H = hotel.getRooms();
                int roomCount = roomsFrom4H.size();
                if (roomCount >= rooms) {
                    createHotel(hotel.getName(),roomCount,checkInDate,checkOutDate,hotel.getCheapestRoom(), hotel.getLocation());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return this.hotelList;
    }

}
