import java.util.ArrayList;

public class Movie {
    private String title;
    private String genre;
    private int movieID;
    private ArrayList<Showtime> showtimes;

    public Movie(){
        //fill in
    }

    //setters
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }
    public void setShowtimes(ArrayList<Showtime> showtimes) {
        this.showtimes = showtimes;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    //getters
    public String getGenre() {
        return genre;
    }
    public int getMovieID() {
        return movieID;
    }
    public ArrayList<Showtime> getShowtimes() {
        return showtimes;
    }
    public String getTitle() {
        return title;
    }
    
}
