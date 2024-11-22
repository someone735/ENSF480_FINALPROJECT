public class TicketController {
    TicketController(){
    }
    public Ticket purchaseTicket(Showtime showtime, User user, int seat){
        int ticketID = produceTicketID();
        double ticketPrice = 13.50; // is a default price ticket ok ?

        // query DB if seat is still available
        // if not, print "Seat unavailable" and return null

        Ticket ticket = new Ticket( ticketID, showtime, user, seat,  ticketPrice, user.getPaymentMethod());
        BillingSystem billingSystem = new BillingSystem();
        billingSystem.processTicketPayment(user, ticket);
        
        return ticket;
    }

    public String cancelTicket(Ticket ticket, User user){

    }

    public void sendTicketReciept(Ticket ticket, User user){

    }

    public int produceTicketID(){
        // queries DB for next available ticketID
        return 1; // just a temp  val

    }
}
