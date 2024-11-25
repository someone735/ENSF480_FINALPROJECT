import java.util.ArrayList;

public class MovieTheatreController {
    public MovieDatabaseManager movieDBM;
    MovieTheatreController(myJDBC myJDBC){
        movieDBM = new MovieDatabaseManager(myJDBC);
    }

    public void addMovieToLocation(Movie movie, Location location) {
        location.addMovie(movie);
        while (!movieDBM.addMovieToLocation(movie.getMovieID(), location.getLocationID())){
        } // may be turned into a try-catch
        // ngl idk if we need this, since theres no table in the db for movie&location relationship without having to add a showtime
        // like i think this can just be, addShowtime()


    }

    public void removeMovieFromLocation(Movie movie, Location location) {
        location.removeMovie(movie);
        while (!movieDBM.removeMovieFromLocation(movie.getMovieID(), location.getLocationID())){
        }// may be turned into a try-catch
        // may be deleted and replaced with removeShowtime() so its like the db
    }

    public void displayMovies(Location location) {
        ArrayList<Movie> allMovies = movieDBM.fetchMovies(location.getLocationID());
        if (allMovies.isEmpty()) {
            System.out.println("No movies available at this location.");
            return;
        }

        System.out.println("Movies at location: " + location.getName());
        for (Movie movie : allMovies) {
            System.out.println("Movie ID: " + movie.getMovieID());
            System.out.println("Title: " + movie.getTitle());
            System.out.println("Genre: " + movie.getGenre());


        }

    }
    public void displayMovies() {
        ArrayList<Movie> allMovies = movieDBM.fetchMovies(-1); // -1 argument to fetchMovies means no location specified
        if (allMovies.isEmpty()) {
            System.out.println("No movies available. ");
            return;
        }

        System.out.println("All movies:");
        for (Movie movie : allMovies) {
            System.out.println("Movie ID: " + movie.getMovieID());
            System.out.println("Title: " + movie.getTitle());
            System.out.println("Genre: " + movie.getGenre());


        }

    }
    public void displayShowtimes(Location location) {}

    public void addShowtime(int id, Movie movie, String time, Location location, int numRUSeats, int numOUSeats) {}

    public void removeShowtime(Showtime showtime) {}

    public void searchMovie( String search) {
        ArrayList<Movie> results = movieDBM.fetchMovies(search);
        if (results.isEmpty()){
            System.out.println("No movies found. ");
            return;
        }
        System.out.println("Movies that matched your search:");
        for (Movie movie : results) {
            System.out.println("Movie ID: " + movie.getMovieID());
            System.out.println("Title: " + movie.getTitle());
            System.out.println("Genre: " + movie.getGenre());


        }

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
