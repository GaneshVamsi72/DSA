import java.util.HashMap;

public class ItineraryForTickets_Hashing {
    static String getStart(HashMap<String, String> tickets) {
        // to, from
        HashMap<String, String> rev_tickets = new HashMap<>();

        for (String key : tickets.keySet()) {
            rev_tickets.put(tickets.get(key), key);
        }

        for (String key : tickets.keySet()) {
            if (!rev_tickets.containsKey(key)) {
                return key;
            }
        }

        return null;
    }

    public static void main(String[] args) {
        // from, to
        HashMap<String, String> tickets = new HashMap<>();

        tickets.put("Chennai", "Bengaluru");
        tickets.put("Mumbai", "Delhi");
        tickets.put("Goa", "Chennai");
        tickets.put("Delhi", "Goa");

        String start = getStart(tickets);
        System.out.print(start);
        for (int i = 0; i < tickets.size(); i++) {
            start = tickets.get(start);
            System.out.print(" -> " + start);
        }
    }
}
