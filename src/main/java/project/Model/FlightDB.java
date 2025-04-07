package project.Model;

import vidmot.Tengja_gogn;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FlightDB {
    private final ArrayList<Flight> flightList= new ArrayList<>();;

    public FlightDB() {

    }

    public void createFlight(String from, String to, int availableSeats, LocalDateTime startTime, LocalDateTime endTime, int price) {
        flightList.add(new Flight(from,to, availableSeats,startTime,endTime,price));
    }

    public ArrayList<Flight> getFlightList(String from, String to, LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        try {
            List<String> flightsStringsList = Tengja_gogn.getFlights(from, to, date);
            //Tengja_gogn sækir í töflu með
            // ID | weekday | departure | arrival | departure_time | flight_time | arrival_time
            // og hleður í String með formið
            // from + " → " + to + " | " + depTime + " → " + arrTime + " | Duration: " + duration;
            // ATH fræðilega séð geta sum gildin vera null. ættum að passa það
            for (String flightString: flightsStringsList) {
                String[] splitString = flightString.split("[→|+]");
                String fromResult = splitString[0].trim();
                String toResult = splitString[1].trim();
                LocalDateTime startTime = LocalDateTime.parse(splitString[2].trim(),formatter);
                LocalDateTime endTime = LocalDateTime.parse(splitString[3].trim(),formatter);

                //TODO er ekki að implementa Seats alveg strax, set 100 á alla. 4F er ekki með price, setjum eins 20000 á allt
                createFlight(fromResult,toResult,100,startTime,endTime,20000);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return this.flightList;
    }

}
