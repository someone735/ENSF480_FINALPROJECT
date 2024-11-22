public class Ticket {
    private int ticketID;
    private Showtime showtime;
    private User user;
    private int seat;
    private double ticketPrice;
    private String paymentMethod;
    private boolean ticketStatus;
    private boolean isReservedByRU; //boolean to check if the ticket saves a seat that was reserved for an RU


    public Ticket(int ticketID, Showtime showtime, User user, int seat, double ticketPrice, String paymentMethod, boolean isReservedByRU ){
        this.ticketID = ticketID;
        this.showtime = showtime;
        this.user = user;
        this.seat = seat;
        this.ticketPrice = ticketPrice;
        this.paymentMethod = paymentMethod;
        this.isReservedByRU = isReservedByRU;
        //fill in below
        ticketStatus = true; // ticketStatus is TRUE when it is still a valid ticket
    }

    //setters

    public boolean getIsReservedByRU(){
        return isReservedByRU;
    }
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }public void setSeat(int seat) {
        this.seat = seat;
    }public void setShowtime(Showtime showtime) {
        this.showtime = showtime;
    }public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }public void setTicketStatus(boolean ticketStatus) {
        this.ticketStatus = ticketStatus;
    }public void setUser(User user) {
        this.user = user;
    }

    // getters
    public String getPaymentMethod() {
        return paymentMethod;
    }public int getSeat() {
        return seat;
    }public Showtime getShowtime() {
        return showtime;
    }public int getTicketID() {
        return ticketID;
    }public double getTicketPrice() {
        return ticketPrice;
    }public User getUser() {
        return user;
    }public boolean getCancellationEligibilty() {
        return cancellationEligibilty;
    }public boolean getTicketStatus() {
        return ticketStatus;
    }

    // more functions
    public void cancelTicket(){}
    public void sendTicketReciept(){}

}
