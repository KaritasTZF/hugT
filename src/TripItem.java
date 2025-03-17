package hugT.src; //Þarf að skilgreina package

public class TripItem {
    private int days;
    private int people;
    private int price;
    //svo tengist þetta DFH klasa

    //Constructor to initialize TripItem object
    public TripItem(int days, int people, int price) {
        this.days = days;
        this.people = people;
        this.price = price;
    }

    public int getPrice(){
        return this.price;
    }
    public int getDays(){
        return this.days;
    }
    public int getPeople(){
        return this.people;
    }

}
