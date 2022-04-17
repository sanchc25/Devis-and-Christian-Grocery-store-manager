public class Buyer extends User{
    private double wallet;

    public Buyer(String firstName, String lastName, String email, String phone, String password) {
        super(firstName, lastName, email, phone, password);
        this.wallet=0;
    }


    public double getWallet() {
        return wallet;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }

}
