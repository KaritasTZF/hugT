package project.Controller;
import java.util.ArrayList;
//import static org.junit.jupiter.api.Assertions.assertEquals;
import project.Model.*;

public class ControllerTests {
    //Prófum að búa til Flight object, setta og svo getta
    
    //@Test
    void testFlightSetter() {
        SearchController sc = new SearchController();
        sc.setFrom("Reykjavík");
        ArrayList<Flight> f = new ArrayList<Flight>();
        f = sc.findAvailableFlights();
        for (int i = 0; i< f.size();i++) {
            if (f.get(i).getFrom()=="Reykjavík") {
                System.out.println("yes");
            } else {
                System.out.println("no");
            }
          }
    }
}
