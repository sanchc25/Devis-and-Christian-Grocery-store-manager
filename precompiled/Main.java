import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    private static String padString(String inputString, int length) {
        if (inputString.length() >= length) {
            return inputString;
        }
        StringBuilder sb = new StringBuilder();
        while (sb.length() < length - inputString.length()) {
            sb.append(' ');
        }
        sb.append(inputString);

        return sb.toString();
    }

    public static void adminDashboard(Admin user) throws IOException {
        ArrayList<Seller> sellers=user.getSellers();
        ArrayList<Buyer> buyers=user.getBuyers();
        ArrayList<Product> products=Store.retrieve();
        Scanner sc=new Scanner(System.in);

        while (true){
            System.out.println("1: View sellers\n2: View buyers\n3 :View products\n4: Delete user\n5: Remove product\n0: Exit");
            int choice= sc.nextInt();
            if (choice==1){
                System.out.println("List of sellers");
                if (sellers.size()==0){
                    System.out.println("No sellers saved \n");
                }else {
                    for (Seller seller :
                            sellers) {
                        System.out.println(seller);
                    }
                }
            }
            else if (choice==2){
                System.out.println("List of buyers");
                if (buyers.size()==0){
                    System.out.println("No buyers saved\n");
                }else {
                    for (Buyer buyer :
                            buyers) {
                        System.out.println(buyer);
                    }
                }
            }
            else if (choice==3){
                System.out.println("Products in store");
                if (products.size()==0){
                    System.out.println("No products in store\n");
                }else {
                    for (Product product :
                            products) {
                        System.out.println(product);
                    }
                }
            }
            else if (choice==4){
                String email;
                System.out.println("Enter user email");
                email=sc.next();
                User.delete(email);
            }
            else if (choice==5){
                int id;
                System.out.println("Enter product ID");
                id=sc.nextInt();
                Store.delete(id);
            }
            else if (choice==0){
                break;
            }
            else {
                System.out.println("Invalid choice");
            }
        }
    }

    public static void sellerDashboard(Seller user) throws IOException {
        ArrayList<Product> products= Store.retrieveBySeller(user.getEmail());

        Scanner sc=new Scanner(System.in);
        while (true){
            System.out.println("1: View my products\n2: Add new product\n3: Delete product\n4: Update product\n5: Add coins\n0:exit\n>>");
            int choice=sc.nextInt();
            if (choice==1){
                if (products!=null && products.size()==0){
                    System.out.println("You do not have any products in store");
                }
                else {
                    System.out.println("\nYour products\n");
                    System.out.println("ID" + padString("Name",25) + padString("Price",23) + padString("Seller",20) );
                    for (Product product :
                            products) {
                        System.out.println(product);
                    }
                }
            }
            else if (choice==2){
                String name;
                System.out.println("Product name: ");
                name= sc.next();
                double price;
                System.out.println("Price: ");
                price= sc.nextDouble();
                Product product=new Product(name,price,user.getEmail());
                Store.save(product);
                System.out.println("Product saved");
            }
            else if (choice==3){
                int id;
                System.out.println("Enter product ID");
                id=sc.nextInt();
                Store.delete(id);
                System.out.println("Product deleted");
            }
            else if (choice==4){
                int id;
                System.out.println("Enter product ID");
                id=sc.nextInt();
                Store.update(id);
                System.out.println("Product updated");
            }
            else if (choice==5){
                Scanner reader=new Scanner(System.in);
                System.out.println("Enter amount of coins you'd wish to add.");
                double coins=reader.nextDouble();
                user.setWallet(coins);
            }
            else if (choice==0){
                break;
            }
            else {
                System.out.println("Invalid input");
            }
        }
    }
    public static void buyerDashboard(Buyer user) throws IOException {
        ArrayList<Product> products= Store.retrieve();
        Cart cart=new Cart(user);
        Scanner sc=new Scanner(System.in);

        while (true){
            System.out.println("1: View products\n2: Add to cart\n3: Checkout\n4: Add coins\n0:exit\n>>>");
            int choice= sc.nextInt();

            if (choice==1){
                if (products.size()==0){
                    System.out.println("There are no products in the Store");
                }
                else {
                    System.out.println("Product list");
                    for (Product product :
                            products) {
                        System.out.println(product);
                    }
                }
            }
            if (choice==2){
                Scanner reader=new Scanner(System.in);
                System.out.println("Enter product ids and and press type OK when done");

                while (true){
                    try{
                        System.out.print("Product ID:");
                        int id=reader.nextInt();
                        for (Product product :
                                products) {
                            if (product.getProductID() == id) {
                                cart.addProduct(product);
                            }
                        }
                    }catch (Exception e){
                        break;
                    }
                }


                }
            else if (choice==3){
                if (cart.canCheckout(cart.calculateTotal())){
                    System.out.println("\nThank you for shopping with us. Your order will be delivered\n");
                }
                else {
                    System.out.println("\nDear customer, You have insufficient coins in your wallet. Kindly recharge then try again\n");
                }
            }
            else if (choice==4){
                Scanner reader=new Scanner(System.in);
                System.out.println("Enter amount of coins you'd wish to add.");
                double coins=reader.nextDouble();
                user.setWallet(coins);
            }
            else if (choice==0){
                break;
            }
            else {
                System.out.println("Invalid choice");
            }
            }

        }


    public static String findClass(User o){
        ArrayList<String> list = new ArrayList<>();
        Class<? extends Object> cl = o.getClass();

        list.add(cl.getSimpleName());
        while (cl.getEnclosingClass() != null) {
            cl = cl.getEnclosingClass();
            list.add(cl.getSimpleName());
        }

        StringBuilder builder = new StringBuilder();
        for (int i=list.size()-1; i>=0; i--){
            builder.append(list.get(i));
            builder.append(".");
        }
        builder.delete(builder.length()-1, builder.length());
        return builder.toString();
    }


    public static void register(){
        String firstName;
        String lastName;
        String email;
        String password;
        String phone;

        Scanner sc=new Scanner(System.in);
        User user = null;

        System.out.println("Enter your details below");

        System.out.println("First Name:");
        firstName=sc.next();
        System.out.println("Last name:");
        lastName=sc.next();
        System.out.println("Email: ");
        email= sc.next();
        System.out.println("Phone: ");
        phone= sc.next();
        System.out.println("Password: ");
        password= sc.next();

        System.out.println("User type: ");
        System.out.println("Select \n1: Admin\n2: Seller\n3: Buyer");
        int userType= sc.nextInt();

        if (userType == 1) {
            user=new Admin(firstName,lastName,email,phone,password);
        } else if (userType==2){
            user=new Seller(firstName,lastName,email,phone,password);
        }
        else if (userType==3){
            user=new Buyer(firstName,lastName,email,phone,password);
        }

    // Save user
        try {
            StringBuilder userInfo = new StringBuilder();
            userInfo.append(user.getFirstName()).append(",").append(user.getLastName()).append(",").append(user.getEmail()).append(",").append(user.getPhone()).append(",").append(user.getPassword()).append(",").append(user.getWallet()).append(",").append(findClass(user)).append("\n");

            FileWriter file = new FileWriter("usersDB.txt",true);
            file.write(userInfo.toString());
            file.close();
            System.out.println("Successfully registered.");
        } catch (IOException e) {
            System.out.println("Registration not successful");
            e.printStackTrace();
        }

    }

    public static User login(){
        String email;
        String password;
        Scanner sc=new Scanner(System.in);

        System.out.println("Enter your email and password to login:");
        System.out.println("Email: ");
        email= sc.next();
        System.out.println("Password: ");
        password= sc.next();

        User user=null;

        try {
            String currentLine;

            BufferedReader in = new BufferedReader(new FileReader("usersDB.txt"));

            while ((currentLine = in.readLine()) != null) {
                String[] details=currentLine.strip().split(",");

                String firstName=details[0];
                String lastName=details[1];
                String emailAddress=details[2];
                String phone=details[3];
                String userPassword=details[4];
                double wallet=Double.parseDouble(details[5]);
                String type=details[6].replace("\n","").strip();

                if (Objects.equals(email, emailAddress) && Objects.equals(password, userPassword)){
                    System.out.println(emailAddress + ":" + userPassword);
//                    System.out.println(type);

                    // Create user object
                    if (Objects.equals(type, "Admin")){
                        user=new Admin(firstName,lastName,email,phone,password);
                    }
                    else if(Objects.equals(type, "Seller")){
                        user=new Seller(firstName,lastName,email,phone,password);
                    }
                    else if(Objects.equals(type, "Buyer")){
                        user=new Buyer(firstName,lastName,email,phone,password);
                    }

                    if (user != null){
                        user.setWallet(wallet);
                    }
//                    System.out.println(user);
                    break;
                }

            }
        }
        catch (IOException e) {
            System.out.println("exception occurred"+ e);
        }
        return user;
    }


    public static void main(String[] args) throws IOException {
        Scanner reader=new Scanner(System.in);
        System.out.println("Welcome to the Grocery store");

        while(true){
            String MENU="1: Register \n" +
                    "2: Login \n" +
                    "0: Exit \n" +
                    ">>>";
            System.out.println(MENU);
            int option=reader.nextInt();
            if (option==1){
                register();
            }
            else if (option==2){
                User user=login();
                if (user==null){
                    System.out.println("Invalid credentials");
                }else {
                    if (user instanceof Admin){
                        adminDashboard((Admin)user);
                    }
                    else if (user instanceof Seller){
                        sellerDashboard((Seller)user);
                    }
                    else if (user instanceof Buyer){
                        buyerDashboard((Buyer)user);
                    }
                }
            }
            else if (option==0){
                System.exit(0);
            }
            else {
                System.out.println("Invalid option. Please pick numbers 0-2");
            }
        }
    }
}
