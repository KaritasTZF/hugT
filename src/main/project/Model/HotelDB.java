package project.Model;

import java.time.LocalDate;
import java.util.ArrayList;

public class HotelDB {
    private ArrayList<Hotel> hotelList;

    public HotelDB() {
    }

    public void createHotel(int rooms, int days, LocalDate start, LocalDate end, int price, String location) {
        hotelList.add(new Hotel(rooms,days,start,end,price,location));
    }

    public ArrayList<Hotel> getHotelList(){
        return this.hotelList;
    }

}
