public class Showtime {
    private int showtimeID;
    private Movie movie;
    private String time;
    private int availableRUSeats;
    private int availableOUSeats;
    private Location location;

    //how do i make movie a pointer??
    public Showtime(int showtimeID, Movie movie, String time, int availableRUSeats, int availableOUSeats, Location location){
        this.showtimeID = showtimeID;
        this.movie = movie; //??
        this.time = time;
        this.availableRUSeats = availableRUSeats;
        this.availableOUSeats = availableOUSeats;
        this.location = location;
    }

    //getters
    public int getShowtimeID() {
        return showtimeID;
    }
    public Movie getMovie() {
        return movie;
    }
    public String getTime() {
        return time;
    }
    public int getAvailableRUSeats() {
        return availableRUSeats;
    }
    public int getAvailableOUSeats() {
        return availableOUSeats;
    }
    public Location getLocation() {
        return location;
    }

    //setters
    public void setAvailableOUSeats(int availableOUSeats) {
        this.availableOUSeats = availableOUSeats;
    }
    public void setAvailableRUSeats(int availableRUSeats) {
        this.availableRUSeats = availableRUSeats;
    }
    public void setLocation(Location location) {
        this.location = location;
    }
    public void setMovie(Movie movie) {
        this.movie = movie;
    }
    public void setShowtimeID(int showtimeID) {
        this.showtimeID = showtimeID;
    }
    public void setTime(String time) {
        this.time = time;
    }

    //more functions 
    public void updateSeats(boolean isRegisteredUser, int numSeatsBooked){}
}