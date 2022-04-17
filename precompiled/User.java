import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
//    private int totalUsers=0;
    private double wallet;

    public User(String firstName,String lastName,String email,String phone,String password){
        this.email=email;
        this.firstName=firstName;
        this.lastName=lastName;
        this.phone=phone;
        this.password=password;
    }


    // Access modifiers
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public double getWallet() {
        return wallet;
    }
    public void setWallet(double newWallet){
        wallet=newWallet;
    }
    // Open files to save user as per type of the user
    public void register(){

    }

    public String padding(String input, char ch, int L)
    {
        String result = String.format("%" + L + "s", input).replace(' ', ch);

        return result;
    }
    @Override
    public String toString() {
        return "firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", email='" + email + '\'' + ", phone='" + phone;
    }

    // Delete user
    public static void delete(String email) throws IOException {
        StringBuilder data=new StringBuilder();

        // Read products database
        String databaseContent = Files.readString(Path.of("usersDB.txt"));
        String[] users=databaseContent.split("\n");

        for (String line :
                users) {
            if (line.split(",")[2].equals(email)) {
                continue;
            }
            data.append(line).append("\n");
        }

        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("usersDB.txt"));
            out.write(String.valueOf(data));
            out.close();
        }
        catch (IOException e) {
            System.out.println("exception occurred"+ e);
        }
    }



}
