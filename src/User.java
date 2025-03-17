package hugT.src;

public class User {
    private String userID;
    private String name;
    private String email;

    //Constructor to initialize User object
    public User(String userID, String name, String email) {
        this.userID = userID;
        this.name = name;
        this.email = email;
    }

    //method: getBooked(): List<Booking>
    //method: getFavorite(): FavoritedTrips
}
