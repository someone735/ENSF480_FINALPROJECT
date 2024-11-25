import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TicketController {

    BillingSystem billingSystem;
    private myJDBC jdbc;

    public TicketController(myJDBC myJDBC){
        this.jdbc = myJDBC;
    }


    TicketController() {
        this.billingSystem = new BillingSystem();
    }

    public Ticket purchaseTicket(Showtime showtime, User user, int seat) {
        double ticketPrice = 13.50;

        Ticket ticket = null;

        int ticketID = produceTicketID();

        // if user is RU, they may attempt to reserve an RU seat, but if unavailable, they may try to reserve an OU seat.
        if (user.getRegistered()) {
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

        //billingSystem.processTicketPayment(user, ticket);

        // TO ADD:
        // add ticket to DB
        if (!addTicketToDB(ticket)) {
            System.out.println("Unable to save ticket to DB.");
        }

        // save remaining OUSeats for this showtime to DB
        if (!updateShowtimeSeats(showtime)) {
            System.out.println("Unable to update seat count in DB.");
        }
        return ticket;
    }

    public void cancelTicket(Ticket ticket, User user) {

        boolean cancellationEligibility = fetchCancellationEligibility(ticket); // default


        // query DB if ticket is still eligible for cancellation; reassign cancellationEligibility

        if (cancellationEligibility) {
            //billingSystem.processTicketRefund(ticket, user);
            ticket.cancelTicket();
            if (!updateTicketStatus(ticket)){
                System.out.println("Unable to update ticket status in DB. Ticket may already be cancelled.");
            }
            boolean isAnRUSeat = ticket.getIsAnRUSeat();

            Showtime showtime = ticket.getShowtime();
            showtime.updateSeats(isAnRUSeat, 1, false);
            if (!updateShowtimeSeats(showtime)){
                System.out.println("Unable to update seat count in DB.");

            }


        } else {
            System.out.println("Ticket is not eligible for cancellation.");
        }
    }

    // Can probably remove this method if it has no other functionality
    // can just call ticket method directly?
    public void sendTicketReceipt(Ticket ticket) {
        ticket.sendTicketReceipt(ticket);
    }

    // TicketDatabaseManager functionality directly inside TicketController

    public boolean addTicketToDB(Ticket ticket) {
        // takes ticket and saves it on DB
        // if successful, return TRUE
        String query = "INSERT INTO tickets (ticketID, userID, showtimeID, seat, ticketPrice, paymentMethod, isRUSeat) VALUES (?, ?, ?, ?, ?, ?, ?)";


        try{
            PreparedStatement statement = jdbc.dbConnect.prepareStatement(query);
            statement.setInt(1, ticket.getTicketID());
            statement.setInt(3, ticket.getShowtime().getShowtimeID());
            statement.setInt(4, ticket.getSeat());
            statement.setDouble(5, ticket.getTicketPrice());
            statement.setString(6, ticket.getPaymentMethod());
            statement.setBoolean(7, ticket.getIsAnRUSeat());

            if (!ticket.getUser().getRegistered()){
                statement.setNull(2, java.sql.Types.INTEGER);
            } else{
                statement.setInt(2, ((RegisteredUser)ticket.getUser()).getID());

            }
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e){
            System.out.println("Error adding ticket to DB: " + e.getMessage());


        }


        return false;
    }

    public boolean updateTicketStatus(Ticket ticket) {
        // uses the ticketStatus attribute of ticket to update the ticketStatus on DB
        // if successful, return TRUE
        String query = "UPDATE tickets SET status = ? WHERE ticketID = ?";

        try {PreparedStatement statement = jdbc.dbConnect.prepareStatement(query);

            statement.setBoolean(1, ticket.getTicketStatus());
            statement.setInt(2, ticket.getTicketID());

            int rowsAffected = statement.executeUpdate();
            return (rowsAffected > 0);

        } catch (SQLException e) {
            System.out.println("Error updating ticket status: " + e.getMessage());
            return false;
        }
    }

    public int produceTicketID() {
        // takes max ticket ID from the ticket table from DB and increments it by 1
        // returns this int
        // wait i think this could also be done in the databse but idk which way we wanna implememnt this
        // -->  we can but i was jus thinking for addTicketToDB the ticket object should alr have an id before insertion ahhh
        return 0;
    }

    public boolean fetchCancellationEligibility(Ticket ticket) {
        String query = "SELECT cancellationEligibility FROM tickets WHERE ticketID = ?";
        try{
            PreparedStatement statement = jdbc.dbConnect.prepareStatement(query);
            statement.setInt(1, ticket.getTicketID());
            ResultSet result = statement.executeQuery();
            while (result.next()){
                return result.getBoolean("cancellationEligibility"); // assuming this is the column name
            }
        }catch(SQLException e) {
            System.out.println("Error fetching cancellation eligibility: " + e.getMessage());
        }
        return false;

    }

    public Ticket fetchTicket(int ticketID) {
        // queries DB for existing ticket info, based on ticketID
        // using this info, make and return a Ticket object
        // may have to call fetchShowtime to make the showtime object and call fetchUser if registered

        String query = "SELECT * FROM tickets WHERE ticketID = ?";
        int userID = -1;
        User user = null;
        try{
            PreparedStatement statement = jdbc.dbConnect.prepareStatement(query);
            statement.setInt(1, ticketID);
            ResultSet result = statement.executeQuery();
            if (result.next()){
                int id = result.getInt("ticketID");
                int showtimeID = result.getInt("showtimeID");
                userID = result.getInt("userID"); // could be null for OUs, need an if-else statement
                double price = result.getDouble("price");
                // !!! still need to query the seat !!
                String paymentMethod = result.getString("paymentMethod");
                boolean isAnRUSeat = result.getBoolean("isRUSeat");
                if (userID == -1){
                    // an OU booked this ticket
                     user = new User(); // default ctor for now, not sure if we're gonna save basic OU info for transactions (like email and name)
                                        // like maybe have a transaction/payment table in db?
                                        // if we do, can query BillingSystem to get user email even if theyre OU
                } else{
                    UserDatabaseManager userDBM = new UserDatabaseManager();
                    user = userDBM.fetchUser(userID);
                }
                MovieTheatreController movieTC = new MovieTheatreController(jdbc);
                //Showtime showtime = MovieTheatreController.fetchShowtime(showtimeID);
                //return new Ticket(id, showtime, user, -1, price, paymentMethod, isAnRUSeat);

            }

        }catch(SQLException e){
            System.out.println("Error fetching ticket: " + e.getMessage());

        }
        return null;


    }

    public boolean updateShowtimeSeats(Showtime showtime) {
        // saves showtime AvailableRUSeats and AvailableOUSeats in DB
        // returns TRUE if successful
        String query = "UPDATE showtimes SET availableSeatsRU = ?, availableSeatsOU = ? WHERE showtimeID = ?"; // can we plz add available OU and RU seat columns in showtimes , thx
        try{
            PreparedStatement statement = jdbc.dbConnect.prepareStatement(query);
            statement.setInt(1, showtime.getAvailableRUSeats());
            statement.setInt(2, showtime.getAvailableOUSeats());
            statement.setInt(3, showtime.getShowtimeID());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;

        }catch(SQLException e) {
            System.out.println("Error updating showtime seats:  " + e.getMessage());
        }
        return false;
    }
}
