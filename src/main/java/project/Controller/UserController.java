package project.Controller;

import project.Model.User;

public class UserController {

    private User user;
    private BookingController bookingController = new BookingController(); //Stofna BC fyrir samskipti við BC

    //Smíða Bookingcontroller f. samskipti á milli
    public UserController() {
    }

    // Sækir núverandi notanda. Skilar null ef enginn user hefur verið stofnaður.
    public User getUser() {
        return this.user;
    }

    public void deleteUser() {
        if (this.user != null) {
            this.user = null;
            System.out.println("User deleted.");
        } else {
            System.out.println("No user to delete.");
        }
    }

    public void updateUser(User updatedUser) {
        if (this.user != null) {
            // Setja inn ný gildi ef vill
            this.user.setName(updatedUser.getName());
            this.user.setEmail(updatedUser.getEmail());
            // o.s.frv.
            System.out.println("User updated.");
        } else {
            System.out.println("No user to update.");
        }
    }
}