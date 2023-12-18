import java.util.Date;

class Session {
    private String movieName;
    private int durationMinutes;
    private Date startTime;
    private int[][] bookedSeats;

    public Session(String movieName, int durationMinutes, Date startTime, int[][] bookedSeats) {
        this.movieName = movieName;
        this.durationMinutes = durationMinutes;
        this.startTime = startTime;
        this.bookedSeats = bookedSeats;
    }

    public String getMovieName() {
        return movieName;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public Date getStartTime() {
        return startTime;
    }

    public int[][] getBookedSeats() {
        return bookedSeats;
    }

    public void bookSeat(int row, int seat) {
        this.bookedSeats[row][seat] = 1;
    }
}
