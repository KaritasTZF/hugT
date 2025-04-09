package project.Model;

import com.example.hbv4d.objects.BookingDAO;
import com.example.hbv4d.objects.Tour;
import com.example.hbv4d.objects.TourDAO;

import java.util.Objects;

public class BookD {
    private User user;
    public BookD(User user) {
        this.user = user;
    }
    public void bookDayTour(DayTour dayTour) {
        //Find their Tour
        for (Tour tour: TourDAO.listTours()) {
            if (Objects.equals(tour.getTourName(), dayTour.getName()) || tour.getDate() == dayTour.getDate()) {
                BookingDAO.addBooking(
                        tour.getId(),
                        0,
                        user.getName(),
                        user.getEmail()
                        );
                System.out.println("Sucessfully booked Day Tour");
                break;
            }
        }
    }
}
