import java.util.ArrayList;

public class Location {
    private String address;
    private String locationAddress;
    private String locationID;
    private ArrayList<Movie> movies;
    
    public Location(){
        //fill in
    }

    //setters
    public void setAddress(String address) {
        this.address = address;
    }
    public void setLocationAddress(String locationAddress) {
        this.locationAddress = locationAddress;
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
    public String getLocationAddress() {
        return locationAddress;
    }
    public String getLocationID() {
        return locationID;
    }
    public ArrayList<Movie> getMovies() {
        return movies;
    }
    
}
