import java.util.Date;

public class RegisteredUser extends User{
    private int ID;
    private String address;
    private boolean paymentMethodSaved;
    private Date membershipExpiry;

    public RegisteredUser(int id, String name, String email, Location theatreLocation, Date membershipExpiry, String Address, boolean paymentMethodSaved, String paymentMethod ){

        super(name, email, theatreLocation);
        this.membershipExpiry = membershipExpiry;
        this.address = Address;

        if (paymentMethodSaved && paymentMethod != null){
            this.paymentMethod = paymentMethod;
        }
        // else if paymentMethod is not saved yet for this RU, they will be requested
        // to input their payment details when purchasing tickets/renewing Membership.
        // their payment details will be saved to the DB after and paymentMethodSaved will == true.

        registered = true;

    }

    //getters
    public int getID() {
        return ID;
    }public String getAddress() {
        return address;
    }public Date getMembershipExpiry() {
        return membershipExpiry;
    }public boolean getPaymentMethodSaved(){
        return paymentMethodSaved;
    }

    //setters
    public void setID(int iD) {
        ID = iD;
    }public void setAddress(String address) {
        this.address = address;
    }public void setPaymentMethodSaved(boolean paymentMethodSaved) {
        this.paymentMethodSaved = paymentMethodSaved;
    }public void setMembershipExpiry(Date membershipExpiry) {
        this.membershipExpiry = membershipExpiry;
    }

    // more functions
    public void savePaymentMethod(){
        //send paymentMethod to database
    }
}
