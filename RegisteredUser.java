import java.util.Date;

public class RegisteredUser extends User{
    private int ID;
    private String address;
    private boolean paymentMethodSaved;
    private Date membershipExpiry;

    public RegisteredUser(){}

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
    public void savePaymentMethod(){}
}
