package project.Controller;
import project.Model.User;

public class UserController {

    private User user;
    private final BookingController bookingController; //Stofna BC fyrir samskipti við BC

    //Smíða Bookingcontroller f. samskipti á milli
    public UserController(BookingController bookingController) {
        this.bookingController = bookingController;
    }

    // Býr til notanda og vistar
    public void createUser(User newUser) {
        if (this.user == null) {
            this.user = newUser;
            System.out.println("New user created: " + newUser.getName());
            System.out.println("User ID: " + newUser.getUserID());
            System.out.println("Email: " + newUser.getEmail());
        } else {
            System.out.println("User already exists. Doing nothing.");
        }
    }

    // Sækir núverandi notanda. Skilar null ef enginn user hefur verið stofnaður.
    public User getUser() {
        return this.user;
    }
}
