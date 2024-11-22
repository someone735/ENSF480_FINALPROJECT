public class TicketController {
    TicketController(){
    }
    public Ticket purchaseTicket(Showtime showtime, User user, int seat){
        double ticketPrice = 13.50; // is a default price ticket ok ?
        // if time permits, add if-else code to check if RU or OU. 10% of the seats
        // reserved for RU. if no more RU seats for this showtime, they can still buy OU seats .
        // the following code assumes the user is Ordinary, which should be changed if this feature is implemented

        if (showtime.getAvailableOUSeats()== 0){
            System.out.println("No more available seats for this Showtime.");
            return null;
            // for caller:
            // if purchaseTicket() returns null, request User to pick another showtime.
        }

        int OUSeatsRemaining = showtime.getAvailableOUSeats() - 1;
        showtime.setAvailableOUSeats(OUSeatsRemaining);

        // must update DB as well

        int ticketID = produceTicketID();
        Ticket ticket = new Ticket( ticketID, showtime, user, seat,  ticketPrice, user.getPaymentMethod());

        BillingSystem billingSystem = new BillingSystem();
        billingSystem.processTicketPayment(user, ticket);
        // add ticket to DB
        return ticket;
    }

    public void cancelTicket(Ticket ticket, User user){

        boolean cancellationEligibility = true; // default

        // query DB if ticket is still eligible for cancellation; reassign cancellationEligibility

        if (cancellationEligibility){
            BillingSystem billingSystem = new BillingSystem();
            billingSystem.processTicketRefund(ticket, user);
        }





    }

    public void sendTicketReciept(Ticket ticket, User user){

    }

    public int produceTicketID(){
        // queries DB for next available ticketID
        return 1; // just a temp  val

    }
}
