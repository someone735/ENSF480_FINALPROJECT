import java.util.ArrayList;
import java.util.Scanner;

public class MovieTheatreApp {

    public static void searchMovie(MovieTheatreController movieTC, Scanner scanner) {
        String search = "";
        System.out.println("Search Movie: ");
        search = scanner.nextLine().trim();
        movieTC.fetchMovies(search);

        ArrayList<Movie> results = movieTC.fetchMovies(search);
        if (results.isEmpty()) {
            System.out.println("No movies found.");
            return;
        }

        System.out.println("Movies that matched your search:");
        for (Movie movie : results) {
            System.out.println("Movie ID: " + movie.getMovieID());
            System.out.println("Title: " + movie.getTitle());
            System.out.println("Genre: " + movie.getGenre());
        }



    }
    public static void displayMovies(MovieTheatreController movieTC, Scanner scanner) {

        // see all movies across ALL locations
        // add code to ask user if they want to see movies based on location or not
        ArrayList<Movie> allMovies = movieTC.fetchMovies(-1);
        if (allMovies.isEmpty()) {
            System.out.println("No movies available.");
            return;
        }

        System.out.println("All movies:");
        for (Movie movie : allMovies) {
            System.out.println("Movie ID: " + movie.getMovieID());
            System.out.println("Title: " + movie.getTitle());
            System.out.println("Genre: " + movie.getGenre());
        }


    }

    public static void displayShowtimes(MovieTheatreController movieTC, Scanner scanner){
        String search = "";
        System.out.println("Which Movie would you like to see showtimes for? ");

        search = scanner.nextLine().trim();
        movieTC.displayShowtimes();

    }
    public static void main(String args[]) {

        String url = "";
        String user = "";
        String pw = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your database url (e.g. jdbc:mysql://localhost:3306/MOVIE_THEATRE): ");
        url = scanner.nextLine();
        System.out.println("Enter your database username (e.g. root): ");
        user = scanner.nextLine();
        System.out.println("Enter your database password: ");
        pw = scanner.nextLine();
        //myJDBC db = new myJDBC(url, user, pw);
        myJDBC db = new myJDBC("jdbc:mysql://localhost:3306/MOVIE_THEATRE", "root", "password");
        db.initializeConnection();

        MovieTheatreController movieTC = new MovieTheatreController(db);
        displayMovies(movieTC, scanner);
        searchMovie(movieTC, scanner);
        displayShowtimes(movieTC, scanner);
    }

}

