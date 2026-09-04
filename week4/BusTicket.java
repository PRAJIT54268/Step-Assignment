import java.util.*;

public class BusTicket {
    private final String passengerName, destination;
    private boolean checkedIn;

    public BusTicket(String passengerName, String destination) {
        if (passengerName == null || passengerName.trim().isEmpty()
                || destination == null || destination.trim().isEmpty()
                || !passengerName.matches("[A-Za-z ]+"))
            throw new IllegalArgumentException();

        this.passengerName = passengerName;
        this.destination = destination;
    }

    public void markCheckedIn() {
        if (checkedIn) throw new IllegalStateException();
        checkedIn = true;
    }

    public static void processBatch(String[][] rawBookings) {
        Set<String> set = new HashSet<>();
        int valid = 0, rejected = 0, duplicate = 0;

        for (String[] b : rawBookings) {
            try {
                BusTicket t = new BusTicket(b[0], b[1]);
                String key = t.passengerName + "|" + t.destination;

                if (set.add(key)) valid++;
                else duplicate++;
            } catch (Exception e) {
                rejected++;
            }
        }

        System.out.println("Valid: " + valid +
                " | Rejected: " + rejected +
                " | Duplicates skipped: " + duplicate);
    }

    public static void main(String[] args) {
        String[][] b = {
            {"Divya","Chennai"},
            {"","Bangalore"},
            {"Ravi123","Pune"},
            {"Divya","Chennai"},
            {" "," "}
        };

        processBatch(b);
    }
}