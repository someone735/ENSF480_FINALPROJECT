public class Ticket {
    private int ticketID;
    private Showtime showtime;
    private User user;
    private int Seat;
    private boolean cancellationEligibilty;
    private double ticketPrice;
    private String paymentMethod;
    private boolean ticketStatus;

    public Ticket(){
        //fill in
    }

    //setters
    public void setCancellationEligibilty(boolean cancellationEligibilty) {
        this.cancellationEligibilty = cancellationEligibilty;
    }public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }public void setSeat(int seat) {
        Seat = seat;
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
        return Seat;
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
