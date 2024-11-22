public class User {
    protected String name;
    protected String email;
    protected boolean registered;
    protected Location theatreLocation;
    protected String paymentMethod;
    
    public User(String name, String email, Location theatreLocation){
        this.name = name;
        this.email = email;
        this.paymentMethod = null; //only to be set if the OU decides to purchase tickets
        this.theatreLocation = theatreLocation;
        registered = false;

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