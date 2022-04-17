import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class Admin extends User{

    ArrayList<Seller> sellers;
    ArrayList<Buyer> buyers;

    public Admin(String firstName, String lastName, String email, String phone, String password) {
        super(firstName, lastName, email, phone, password);
    }

    //methods
    public void manageAccount(){
        sellers=getSellers();
        buyers=getBuyers();

        String[] sellersList = new String[sellers.size()];
        String[] buyersList = new String[buyers.size()];

        String[] selectSellerComboItems = new String[sellers.size()];

        for (int i = 0; i < sellers.size(); i++) {
            sellersList[i]= sellers.get(i).toString();
            selectSellerComboItems[i]= sellers.get(i).toString();
        }

        for (int i = 0; i < buyers.size(); i++) {
            buyersList[i]= buyers.get(i).toString();
        }

    }

    // get buyers
    public ArrayList<Buyer> getBuyers(){
        ArrayList<Buyer> buyers=new ArrayList<>();

        try {
            String currentLine;

            BufferedReader in = new BufferedReader(new FileReader("usersDB.txt"));

            while ((currentLine = in.readLine()) != null) {
                String[] details=currentLine.split(",");
                String firstName=details[0];
                String lastName=details[1];
                String email=details[2];
                String phone=details[3];
                String password=details[4];
                double wallet=Double.parseDouble(details[5]);
                String type=details[6].replace("\n","").strip();

                if (Objects.equals(type, "Buyer")){
                    User buyer=new Buyer(firstName,lastName,email,phone,password);
                    buyer.setWallet(wallet);
                    buyers.add((Buyer) buyer);
                }

            }

        }
        catch (IOException e) {
            System.out.println("exception occurred"+ e);
        }
        return buyers;
    }

    // get sellers
    public ArrayList<Seller> getSellers(){
        ArrayList<Seller> sellers=new ArrayList<>();

        try {
            String currentLine;

            BufferedReader in = new BufferedReader(new FileReader("usersDB.txt"));

            while ((currentLine = in.readLine()) != null) {
                String[] details=currentLine.split(",");
                String firstName=details[0];
                String lastName=details[1];
                String email=details[2];
                String phone=details[3];
                String password=details[4];
                double wallet=Double.parseDouble(details[5]);
                String type=details[6].replace("\n","").strip();

                if (Objects.equals(type, "Seller")){
                    User seller=new Seller(firstName,lastName,email,phone,password);
                    seller.setWallet(wallet);
                    sellers.add((Seller) seller);
                }

            }

        }
        catch (IOException e) {
            System.out.println("exception occurred"+ e);
        }
        return sellers;
    }
}
