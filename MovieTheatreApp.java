import java.util.Scanner;

public class MovieTheatreApp {

    public static void searchMovie(myJDBC db, Scanner scanner) {
        String search = "";
        System.out.println("Search Movie: ");
        search = scanner.nextLine().trim();
        db.searchMovie(search);

    }
    public static void displayMovies(myJDBC db, Scanner scanner) {
        MovieTheatreController movieTheatreController = new MovieTheatreController(db);
        movieTheatreController.displayMovies(); // see all movies across ALL locations
        // add code to ask user if they want to see movies based on location or not

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



        searchMovie(db, scanner);
    }

}

