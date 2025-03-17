package hugT.src;

import java.util.ArrayList; //Til þess að geyma lista af object okkar

public class Trip {
    private String tripID;
    private int people;
    private int date;
    private ArrayList<TripItem> items; //Listi af TripItems, listinn heitir items

    //Constructor to initialize Trip object
    public Trip() {

    }

    //skilar fjöldi manns
    public int getPeople() {
        return this.people;
    }

    //Bætir við TripItem object
    public void addTripItem(TripItem t) {
        items.add(t);
    }

    //les verð öll TripItems, leggur saman og skilar heildarverð
    public int getPrice() {
        int verd = 0;
        for (TripItem t: items) {
            verd += t.getPrice();
        }
        return verd;
    }
}
