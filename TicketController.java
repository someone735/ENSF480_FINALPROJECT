public class TicketController {
    BillingSystem billingSystem;
    TicketDatabaseManager ticketDBM;
    TicketController(TicketDatabaseManager ticketDBM) {
        this.billingSystem = new BillingSystem();
        this.ticketDBM = ticketDBM;
    }

    public Ticket purchaseTicket(Showtime showtime, User user, int seat) {
        double ticketPrice = 13.50;

        Ticket ticket = null;

        int ticketID = ticketDBM.produceTicketID();

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

        billingSystem.processTicketPayment(user, ticket);

        // TO ADD:
        // add ticket to DB
        while (!ticketDBM.addTicketToDB(ticket)){
            System.out.println("Unable to save ticket to DB.");
        }

        // save remaining OUSeats for this showtime to DB
        while (!ticketDBM.updateShowtimeSeats(showtime)){
            System.out.println("Unable to update seat count in DB.");
        }
        return ticket;


    }



    public void cancelTicket(Ticket ticket, User user){

        boolean cancellationEligibility = true; // default

        // query DB if ticket is still eligible for cancellation; reassign cancellationEligibility

        if (cancellationEligibility){
            billingSystem.processTicketRefund(ticket, user);
            ticket.cancelTicket();

            // TO ADD:
            // update DB that the ticket is cancelled

            Showtime showtime = ticket.getShowtime();

            // TO ADD:
            // update DB with number of remaining seats

            } else {
            System.out.println("Ticket is not eligible for cancellation.");
        }



    }

    // Can probably remove this method if it has no other functionality
    // can just call ticket method directly?
    public void sendTicketReceipt(Ticket ticket){
        ticket.sendTicketReceipt(ticket);




    }


}
