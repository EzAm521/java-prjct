import java.sql.*;
import java.util.Random;
import java.util.Scanner;


public class App {
    public static void main(String[] args) throws Exception {

        Random rand = new Random();
        Scanner myObj = new Scanner(System.in);

        int isl = 0;
        int attempts = 0;

        System.out.println("______________________________________");
        System.out.println("|  Welcome to authorization part!..  |");
        System.out.println("______________________________________");
        Password Password_pass1 = new Password();

        while (attempts < 3) {
            isl = 0;
            System.out.print("Please enter your password: ");
            String s = myObj.nextLine();
            Password_pass1 = new Password(s);
            if (!Password_pass1.checkForCapital(s)) {
                System.out.println("There is no capital letters!.. ");
            } else {
                isl++;
            }
            if (!Password_pass1.checkForNumber(s)) {
                System.out.println("There is no numbers!.. ");
            } else {
                isl++;
            }
            if (!Password_pass1.checkForSixLetters(s)) {
                System.out.println("Password is fewer than 6 letters!.. ");
            } else {
                isl++;
            }
            if (isl == 3) {
                break;
            }
            attempts++;
        }
        if (attempts == 3) {
            System.out.println("You have exceeded the number of allowed attempts. Please try again later.");
            return;
        }

        System.out.println("_Login_");
        System.out.println("Enter login: ");
        String log = myObj.nextLine();
        System.out.println("Enter password: ");
        String pass = myObj.nextLine();

        CAPTCHA c = new CAPTCHA();
        String realCaptcha = c.getCaptcha();
        if (c.jframe(realCaptcha)) {
            System.out.println("Successful");
        } else {
            System.out.println("Failed");
        }

        String encryptedPassword = Password_pass1.cipher(Password_pass1.getPassword(), 10);
        String decryptedPassword = Password_pass1.decipher(encryptedPassword, 10);
        //проверка по sql есть ли логин в таблице
        //если есть, то сверка логина и пароля
        //если нет, написать "Invalid login or password"

        try{
            String url = "jdbc:postgresql://localhost:5432/user";
            String username = "root";
            String password = "password";
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

            try (Connection conn = DriverManager.getConnection(url, username, password)){

                Statement statement = conn.createStatement();
                int rows = statement.executeUpdate("INSERT user(login, password) VALUES (log , pass),");
                System.out.printf("Added %d rows", rows);
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");

            System.out.println(ex);
        }
        String connectionUrl = "jdbc:postgresql://localhost:5432/user";
        Connection con = null;
        ResultSet rs = null;
        Statement statement = null;
        try{
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(connectionUrl, "postgres", "Aa1234");
            statement = con.createStatement();
            rs = statement.executeQuery("select * from user");
            while(rs.next()){
                System.out.println(rs.getchar("login") + " " + rs.getchar("password"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            try{
                rs.close();
                statement.close();
                con.close();
            } catch (SQLException throwables){
                throwables.printStackTrace();
            }
        }
    }


}


    

