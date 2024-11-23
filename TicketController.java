public class TicketController {
    TicketController(){
    }
    public Ticket purchaseTicket(Showtime showtime, User user, int seat){
        double ticketPrice = 13.50;

        // PLS READ
        // if time permits, add if-else code to check if RU or OU.
        // 10% of the seats are reserved for RU (calculation of allocated seats already in Showtime ctor).
        // if no more RU seats for this showtime, they can still buy OU seats .
        // the following code assumes the user is Ordinary and can only book Ordinary seats,
        // which should be changed if this feature is implemented


        if (showtime.updateSeats(false, 1,true)){
            int ticketID = produceTicketID();
            Ticket ticket = new Ticket( ticketID, showtime, user, seat,  ticketPrice, user.getPaymentMethod(), false);
            BillingSystem billingSystem = new BillingSystem();
            billingSystem.processTicketPayment(user, ticket);
            // add ticket to DB
            // save remaining OUSeats for this showtime to DB
            return ticket;
        } else {
            return null;
            // for caller:
            // if purchaseTicket() returns null, request User to pick another showtime.
        }


    }

    public void cancelTicket(Ticket ticket, User user){

        boolean cancellationEligibility = true; // default

        // query DB if ticket is still eligible for cancellation; reassign cancellationEligibility

        if (cancellationEligibility){
            BillingSystem billingSystem = new BillingSystem();
            billingSystem.processTicketRefund(ticket, user);
            ticket.setTicketStatus(false);
            if (ticket.getIsReservedByRU()){
                Showtime showtime = ticket.getShowtime();

                // i'll finish this later lol
            }
        }





    }

    public void sendTicketReciept(Ticket ticket, User user){

    }

    public int produceTicketID(){
        // queries DB for next available ticketID
        return 1; // just a temp  val

    }
}
