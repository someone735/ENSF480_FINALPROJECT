import java.util.ArrayList;

public class MovieTheatreController {

    public void addMovieToLocation(Movie movie, Location location) {}

    public void removeMovieFromLocation(Movie movie, Location location) {}

    public void displayMovies(Location location) {}

    public void displayShowtimes(Location location) {}

    public void addShowtime(int id, Movie movie, String time, Location location, int numRUSeats, int numOUSeats) {}

    public void removeShowtime(Showtime showtime) {}

    public ArrayList<Movie> searchMovies(Location location, String search) {
        return null;
    }

    public void selectMovie(Movie movie) {}

    public void displayMovieNews(boolean isRegistered) {}

    public int produceMovieID() {
        return 0;
    }

    public void addMovie(int id, String title, String genre) {}

    public void removeMovie(Movie movie) {}

    public int produceShowtimeID() {
        return 0;
    }
}
