public class TicketController {
    TicketController() {
    }

    public Ticket purchaseTicket(Showtime showtime, User user, int seat) {
        double ticketPrice = 13.50;

        Ticket ticket = null;

        int ticketID = produceTicketID();

        // if user is RU, they may attempt to reserve an RU seat, but if unavailable, they may try to reserve an OU seat.
        if (user.getRegistered()){
            if (showtime.updateSeats(true, 1, true)) {
                ticket = new Ticket(ticketID, showtime, user, seat, ticketPrice, user.getPaymentMethod(), true);

            } else if (showtime.updateSeats(false, 1, true)) {
                ticket = new Ticket(ticketID, showtime, user, seat, ticketPrice, user.getPaymentMethod(), false);
            }
        }

        // if user is OU, they can only try to purchase an OU seat.
        else if ((!user.getRegistered()) && showtime.updateSeats(false, 1, true)) {
            ticket = new Ticket(ticketID, showtime, user, seat, ticketPrice, user.getPaymentMethod(), false);
        }

        if (ticket == null) {
            System.out.println("No more seats available for this showtime. Please select another.");
            return ticket; // for caller: if purchaseTicket() returns null, request User to pick another showtime.
        }

        BillingSystem billingSystem = new BillingSystem();
        billingSystem.processTicketPayment(user, ticket);

        // TO ADD:
        // add ticket to DB
        // save remaining OUSeats for this showtime to DB


    }

}

    public void cancelTicket(Ticket ticket, User user){

        boolean cancellationEligibility = true; // default

        // query DB if ticket is still eligible for cancellation; reassign cancellationEligibility

        if (cancellationEligibility){
            BillingSystem billingSystem = new BillingSystem();
            billingSystem.processTicketRefund(ticket, user);
            ticket.cancelTicket();
            // update DB that the ticket is cancelled
            Showtime showtime = ticket.getShowtime();
            // update DB with number of remaining seats
            } else {
            System.out.println("Ticket is not eligible for cancellation.");
        }





    }

    public void sendTicketReceipt(Ticket ticket, User user){

    }

    public int produceTicketID(){
        // queries DB for next available ticketID
        return 1; // just a temp  val

    }
}
