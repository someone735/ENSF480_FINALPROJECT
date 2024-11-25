import java.util.ArrayList;

public class MovieTheatreController {
    // sorry hehe just putting thes etemporarily here for now so i can go back to them when i need them
//    public static void searchMovie(myJDBC db, Scanner scanner) {
//        String search = "";
//        System.out.println("Search Movie: ");
//        search = scanner.nextLine().trim();
//        db.searchMovie(search);
//
//    }
//    public static void displayMovies(myJDBC db, Scanner scanner) {
//        db.displayMovies();
//    }

    public void addMovieToLocation(Movie movie, Location location) {
        location.addMovie(movie);
    }

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
