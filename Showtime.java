public class Showtime {
    private int showtimeID;
    private Movie movie;
    private String time;
    private int availableRUSeats;
    private int availableOUSeats;
    private int totalRUSeats;
    private int totalOUSeats;
    private Location location;


//    private int createID() {
//        int id = count;
//        count++;
//        return id;
//    } ---> will be implemented in MovieTheatreController so it can query the database for the next available ID num.

    public Showtime(Movie movie, String time, int totalSeats, Location location, int showtimeID){
        this.showtimeID = showtimeID;
        this.movie = movie;
        this.time = time;

        this.availableRUSeats = (int)(totalSeats*0.10); // 10% of seats reserved for RU
        this.availableOUSeats = totalSeats - availableRUSeats;

        this.totalOUSeats = availableOUSeats;
        this.totalRUSeats = availableRUSeats;

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
