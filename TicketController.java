public class TicketController {
    TicketController(){
    }
    public Ticket purchaseTicket(Showtime showtime, User user, int seat){
        int ticketID = produceTicketID();
        double ticketPrice = 13.50; // is a default price ticket ok ?

        // query DB if seat is still available
        // if not, print "Seat unavailable" and return null


        // if time permits, add if-else code to check if RU or OU. 10% of the seats
        // reserved for RU. if no more RU seats for this showtime, they can buy OU seats .
        // the following code assumes the user is Ordinary, which should be changed if this feature is implemented

        int OUSeatsRemaining = showtime.getAvailableOUSeats() - 1;
        showtime.setAvailableOUSeats(OUSeatsRemaining);
        Ticket ticket = new Ticket( ticketID, showtime, user, seat,  ticketPrice, user.getPaymentMethod());
        BillingSystem billingSystem = new BillingSystem();
        billingSystem.processTicketPayment(user, ticket);

        return ticket;
    }

    public void cancelTicket(Ticket ticket, User user){

        // query DB if ticket is still eligible for cancellation

    }

    public void sendTicketReciept(Ticket ticket, User user){

    }

    public int produceTicketID(){
        // queries DB for next available ticketID
        return 1; // just a temp  val

    }
}
