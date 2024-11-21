import java.util.ArrayList;

public class Location {
    private String address;
    private String locationName;
    private String locationID;
    private ArrayList<Movie> movies;
    
    public Location(){
        //fill in
    }

    //setters
    public void setAddress(String address) {
        this.address = address;
    }
    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
    public void setLocationID(String locationID) {
        this.locationID = locationID;
    }
    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    //getters
    public String getAddress() {
        return address;
    }
    public String getLocationName() {
        return locationName;
    }
    public String getLocationID() {
        return locationID;
    }
    public ArrayList<Movie> getMovies() {
        return movies;
    }
    
    //more functions
    public void addMovie(Movie movie){}
    
    public void removeMovie(Movie movie){}
    
    public void hasMovie(Movie movie){}


}
