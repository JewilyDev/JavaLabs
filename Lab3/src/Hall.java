import java.util.ArrayList;
import java.util.List;

class Hall {
    private String name;
    private int[][] seatLayout;
    private List<Session> sessions;

    public Hall(String name, int rows, int seatsPerRow) {
        this.name = name;
        this.seatLayout = new int[rows][seatsPerRow];
        this.sessions = new ArrayList<>();
    }

    public void addSession(Session session) {
        sessions.add(session);
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public String getName() {
        return name;
    }

    public int[][] getSeatLayout() {
        return seatLayout;
    }
}