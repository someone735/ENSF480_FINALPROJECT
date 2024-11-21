public class User {
    private String name;
    private String email;
    private boolean registered;
    private Location theatreLocation;
    private String paymentMethod;
    
    public User(String name, String email, Location theatreLocation, String paymentMethod){
        this.name = name;
        this.email = email;
        this.theatreLocation = theatreLocation;
        this.paymentMethod = paymentMethod;
        //fill in 
        //checking for registered
    }
    //setters
    public void setEmail(String email) {
        this.email = email;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    public void setRegistered(boolean registered) {
        this.registered = registered;
    }
    public void setTheatreLocation(Location theatreLocation) {
        this.theatreLocation = theatreLocation;
    }

    //getters
    public String getEmail() {
        return email;
    }
    public String getName() {
        return name;
    }
    public String getPaymentMethod() {
        return paymentMethod;
    }
    public boolean getRegistered(){
        return registered;
    }
    public Location getTheatreLocation() {
        return theatreLocation;
    }
    
    
    // public void purchaseTicket(){}
}