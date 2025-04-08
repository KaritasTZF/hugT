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
        ArrayList<software.objects.Hotel> availableHotels = search.initialSearch(location, checkInDate.format(formatter), checkOutDate.format(formatter),people);
        //TODO laga rooms (4H gerir ráð fyrir að bóka eitt room í einu) ,
        // checkIn/OutDate (4H leitar eftir að checkIn er nákvæmlega sama dagsetning, og gerir ekkert við checkOut)
        // þ.a. þessi rooms, checkIn/OutDate eru placeholders
        //ATH 4H og við erum með klasa sem heita bæði Hotel. þá er software.objects alltaf notað til að kalla á 4H Hotel.
        for (software.objects.Hotel hotel: availableHotels) {
            createHotel(hotel.getName(),1,checkInDate,checkOutDate,hotel.getCheapestRoom(), hotel.getLocation());
        }
        return hotelList;
    }

}
